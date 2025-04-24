
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

    tasks = FileManager.loadTasks(); // Load tasks from file

    while (true) {
      System.out.print("> ");
      String input = scanner.nextLine().trim(); // Trim leading/trailing spaces

      // Split the input into parts
      String[] parts = input.split("\\s+", 2); // Split into command and arguments
      String command = parts[0].toLowerCase(); // Extract the command (case-insensitive)
      String arguments = parts.length > 1 ? parts[1] : ""; // Extract the arguments

      if (command.equals("exit")) {
        System.out.println("Exiting Task Manager...");
        FileManager.saveTasks(tasks);
        break;
      } else if (command.equals("add")) {
        addTask(arguments); // Pass arguments to addTask
      } else if (command.equals("list")) {
        listTasks();
      } else if (command.equals("complete")) {
        completeTask(arguments); // Pass arguments to completeTask
      } else if (command.equals("delete")) {
        deleteTask(arguments); // Pass arguments to deleteTask
      } else {
        System.out.println("Unknown command. Please use 'add', 'list', 'complete', 'delete', or 'exit'.");
      }
    }
    scanner.close();
  }

  private static void addTask(String taskArgs) {
    try {
      String[] parts = taskArgs.split("\"");
      if (parts.length < 5) {
        System.out.println("Invalid format. Use: add \"title\" \"description\" yyyy-MM-dd");
        return;
      }

      String title = parts[1].trim();
      String description = parts[3].trim();
      String dateString = parts[4].trim().split("\\s+")[0].trim();

      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
      sdf.setLenient(false);
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

// This code is a simple task manager that allows users to add, list, complete,
// and delete tasks.
// It uses a command-line interface and stores tasks in an ArrayList.
// The tasks are serialized to a file for persistence.
// The Task class represents a task with an ID, title, description, due date,
// and completion status.
// The FileManager class handles saving and loading tasks from a file.
// The TaskManager class contains the main logic for managing tasks, including
// user input and command processing.
// The code includes error handling for invalid commands and date formats.
// The TaskManager class also includes a method to format the due date for
// display.
// The code is designed to be user-friendly and provides feedback for each
// action taken.
