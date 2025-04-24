import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String title;
  private String description;
  private Date dueDate;
  private boolean completed;

  public Task(int id, String title, String description, Date dueDate) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.completed = false;
  }

  // Getters and Setters
  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  // Format the due date
  public String getFormattedDueDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(dueDate);
  }

  @Override
  public String toString() {
    String status = completed ? "[ X ]" : "[ ]";
    return String.format("%s %s - %s (Due: %s)", id, status, title, getFormattedDueDate());
  }
}