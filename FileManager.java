import java.io.*;
import java.util.ArrayList;

public class FileManager {
  private static final String FILE_NAME = "tasks.dat";

  public static void saveTasks(ArrayList<Task> tasks) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
      oos.writeObject(tasks);
      System.out.println("Tasks saved successfully.");
    } catch (IOException e) {
      System.out.println("Error saving tasks: " + e.getMessage());
    }
  }

  // Load tasks from the file
  @SuppressWarnings("unchecked")
  public static ArrayList<Task> loadTasks() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
      ArrayList<Task> tasks = (ArrayList<Task>) ois.readObject();
      System.out.println("Tasks loaded successfully.");
      return tasks;
    } catch (FileNotFoundException e) {
      System.out.println("No saved tasks found. Starting with an empty list.");
      return new ArrayList<>();
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Error loading tasks: " + e.getMessage());
      return new ArrayList<>();

    } catch (Exception e) {
      System.out.println("Unexpected error: " + e.getMessage());
      return new ArrayList<>();
    }
  }
}
