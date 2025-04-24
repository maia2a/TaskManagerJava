import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;

public class TaskManager {
  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final String COMMAND_ADD = "add";
  private static final String COMMAND_LIST = "list";
  private static final String COMMAND_COMPLETE = "complete";
  private static final String COMMAND_DELETE = "delete";
  private static final String COMMAND_EXIT = "exit";

  private static ArrayList<Task> tasks = new ArrayList<>();
  private static int nextId = 1; // Unique ID for each task

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Task Manager!");

    // Load tasks from file
    tasks = FileManager.loadTasks();

    // Main loop for user interaction
    while (true) {
      System.out.print("> ");
      String input = scanner.nextLine().trim(); // Trim leading/trailing spaces

      if (input.isEmpty()) {
        System.out.println("Unknown command. Please use 'add', 'list', 'complete', 'delete', or 'exit'.");
        continue;
      }

      // Split the input into parts
      String[] parts = input.split("\\s+", 2); // Split into command and arguments
      String command = parts[0].toLowerCase(); // Extract the command (case-insensitive)
      String arguments = parts.length > 1 ? parts[1] : ""; // Extract the arguments

      // Process the command
      switch (command) {
        case COMMAND_EXIT:
          System.out.println("Exiting Task Manager...");
          FileManager.saveTasks(tasks);
          scanner.close();
          return;

        case COMMAND_ADD:
          addTask(arguments);
          break;

        case COMMAND_LIST:
          listTasks();
          break;

        case COMMAND_COMPLETE:
          completeTask(input);
          break;

        case COMMAND_DELETE:
          deleteTask(input);
          break;

        default:
          System.out.println("Unknown command. Please use 'add', 'list', 'complete', 'delete', or 'exit'.");
      }
    }
  }

  private static void addTask(String taskArgs) {
    if (!isValidAddInput(taskArgs)) {
      System.out.println("Invalid format. Use: add \"title\" \"description\" yyyy-MM-dd");
      return;
    }

    try {
      String[] parts = taskArgs.split("\"");
      String title = parts[1].trim();
      String description = parts[3].trim();
      String dateString = parts[4].trim().split("\\s+")[0].trim();

      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
      sdf.setLenient(false); // Strict parsing
      Date dueDate = sdf.parse(dateString);

      Task task = new Task(nextId++, title, description, dueDate);
      tasks.add(task);
      System.out.println("Task added successfully!");
    } catch (ParseException e) {
      System.out.println("Invalid date format. Use yyyy-MM-dd (e.g., 2024-01-15).");
    } catch (Exception e) {
      System.out.println("Error adding task. Please check your input.");
    }
  }

  private static boolean isValidAddInput(String taskArgs) {
    return taskArgs != null && !taskArgs.trim().isEmpty() && taskArgs.split("\"").length >= 5;
  }

  private static void listTasks() {
    if (tasks.isEmpty()) {
      System.out.println("No tasks available.");
      return;
    }

    System.out.println("Tasks:");
    for (Task task : tasks) {
      System.out.println(task);
    }
  }

  private static void completeTask(String input) {
    processTaskAction(input, COMMAND_COMPLETE, "completed", (task, id) -> task.setCompleted(true));
  }

  private static void deleteTask(String input) {
    processTaskAction(input, COMMAND_DELETE, "deleted", (task, id) -> tasks.removeIf(t -> t.getId() == id));
  }

  private static void processTaskAction(String input, String action, String actionName, TaskAction actionHandler) {
    String[] parts = input.split("\\s+");
    if (parts.length < 2) {
      System.out.println("Invalid command. Usage: " + action + " [id]");
      return;
    }

    try {
      int id = Integer.parseInt(parts[1].trim());
      boolean found = false;

      for (Task task : tasks) {
        if (task.getId() == id) {
          actionHandler.performAction(task, id);
          System.out.println("Task " + actionName + " with ID: " + id);
          found = true;
          break;
        }
      }

      if (!found) {
        System.out.println("Task with ID " + id + " not found.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid ID format. Please enter a number.");
    } catch (Exception e) {
      System.out.println("Error processing task: " + e.getMessage());
    }
  }

  @FunctionalInterface
  private interface TaskAction {
    void performAction(Task task, int id);
  }
}