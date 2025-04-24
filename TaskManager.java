import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TaskManager {
  private static ArrayList<Task> tasks = new ArrayList<>();
  private static int nextId = 1; // Unique ID for each task
  private static final String DATE_FORMAT = "yyyy-MM-dd";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Task Manager!");

    while (true) {
      System.out.print("> ");
      String command = scanner.nextLine().trim();

      if (command.equalsIgnoreCase("exit")) {
        System.out.println("Exiting Task Manager. Goodbye!");
        break;
      } else if (command.equalsIgnoreCase("add")) {
        addTask(command);
      } else if (command.equalsIgnoreCase("list")) {
        listTasks();
      } else if (command.equalsIgnoreCase("complete")) {
        completeTask(command);
      } else if (command.equalsIgnoreCase("delete")) {
        deleteTask(command);
      } else {
        System.out.println("Unknown command. Please use 'add', 'list', 'complete', 'delete', or 'exit'.");
      }
    }
    scanner.close();
  }

  private static void addTask(String command) {
    try {
      String[] parts = command.split("\"");
      if (parts.length < 5) {
        System.out.println("Invalid command. Usage: add \"title\" \"description\" \"yyyy-MM-dd\"");
        return;
      }
      String title = parts[1].trim();
      String description = parts[3].trim();
      String dateString = parts[4].trim().split("\\s+")[0]; // Extract date string

      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
      sdf.setLenient(false); // Strict date parsing
      Date dueDate = sdf.parse(dateString);

      Task task = new Task(nextId++, title, description, dueDate);
      tasks.add(task);
      System.out.println("Task added: " + task);
    } catch (ParseException e) {
      System.out.println("Invalid date format. Please use " + DATE_FORMAT + ".");
    } catch (Exception e) {
      System.out.println("Error adding task: " + e.getMessage());
    }
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

  private static void completeTask(String command) {
    try {
      String[] parts = command.split("\\s+");
      if (parts.length < 2) {
        System.out.println("Invalid command. Usage: complete [id]");
        return;
      }
      int id = Integer.parseInt(parts[1]);
      boolean found = false;

      // Loop through tasks to find the one with the given ID
      for (Task task : tasks) {
        if (task.getId() == id) {
          task.setCompleted(true);
          System.out.println("Task completed: " + task);
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
      System.out.println("Error completing task: " + e.getMessage());
    }
  }

  private static void deleteTask(String command) {
    try {
      String[] parts = command.split("\\s+");
      if (parts.length < 2) {
        System.out.println("Invalid command. Usage: delete [id]");
        return;
      }
      int id = Integer.parseInt(parts[1]);
      boolean found = false;

      // Loop through tasks to find the one with the given ID
      for (int i = 0; i < tasks.size(); i++) {
        if (tasks.get(i).getId() == id) {
          tasks.remove(i);
          System.out.println("Task deleted with ID: " + id);
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
      System.out.println("Error deleting task: " + e.getMessage());
    }
  }
}
