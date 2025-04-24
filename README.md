---

# **CLI Task Manager**

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.java.com/) [![License](https://img.shields.io/badge/License-MIT-green)](#license)

A simple yet powerful **Command-Line Interface (CLI)** Task Manager built in Java. This application allows users to manage tasks with features like adding, listing, marking as complete, deleting, and persisting tasks between sessions.

---

## **Table of Contents**

1. [Features](#features)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Commands](#commands)
5. [File Persistence](#file-persistence)
6. [Contributing](#contributing)
7. [License](#license)
8. [Future Improvements](#future-improvements)

---

## **Features**

The CLI Task Manager provides the following core functionalities:

- **Add Tasks:** Add tasks with a title, description, and due date.
- **List Tasks:** View all tasks, including their status (completed or pending).
- **Mark Tasks as Complete:** Toggle the completion status of a task using its unique ID.
- **Delete Tasks:** Remove tasks by their unique ID.
- **File Persistence:** Save tasks to a file (`tasks.dat`) and reload them when the program restarts.
- **Error Handling:** Robust error handling for invalid inputs, such as malformed dates or missing arguments.

---

## **Installation**

### **Prerequisites**

- **Java Development Kit (JDK):** Ensure you have JDK 8 or higher installed. You can verify this by running:
  ```bash
  java -version
  javac -version
  ```
- **IDE or Text Editor:** Use an IDE like IntelliJ IDEA, Eclipse, or VS Code for development.

### **Steps**

1. Clone the repository:
   ```bash
   git clone https://github.com/maia2a/TaskManagerJava.git
   cd TaskManager
   ```
2. Compile the Java files:
   ```bash
   javac TaskManager.java Task.java FileManager.java
   ```
3. Run the program:
   ```bash
   java TaskManager
   ```

---

## **Usage**

### **Adding a Task**

To add a new task, use the `add` command with the following format:

```bash
> add "title" "description" yyyy-MM-dd
```

Example:

```bash
> add "Finish Java project" "Complete the Task Manager app" 2024-01-15
Task added successfully!
```

### **Listing Tasks**

To view all tasks, use the `list` command:

```bash
> list
```

Example Output:

```
Tasks:
[1] [ ] Finish Java project (Due: 2024-01-15)
```

### **Marking a Task as Complete**

To mark a task as complete, use the `complete` command followed by the task ID:

```bash
> complete 1
```

Example Output:

```
Task completed: [1] [X] Finish Java project (Due: 2024-01-15)
```

### **Deleting a Task**

To delete a task, use the `delete` command followed by the task ID:

```bash
> delete 1
```

Example Output:

```
Task deleted with ID: 1
```

### **Exiting the Program**

To exit the program and save tasks, use the `exit` command:

```bash
> exit
```

Example Output:

```
Exiting Task Manager...
Tasks saved successfully!
```

---

## **Commands**

| Command                   | Description                        | Example                                   |
| ------------------------- | ---------------------------------- | ----------------------------------------- |
| `add "title" "desc" date` | Add a new task.                    | `add "Study Java" "Learn OOP" 2024-01-20` |
| `list`                    | List all tasks.                    | `list`                                    |
| `complete [id]`           | Mark a task as complete by its ID. | `complete 1`                              |
| `delete [id]`             | Delete a task by its ID.           | `delete 1`                                |
| `exit`                    | Save tasks and exit the program.   | `exit`                                    |

---

## **File Persistence**

The program uses **serialization** to persist tasks between sessions. Tasks are saved to a file named `tasks.dat` in the project directory. When the program starts, it loads tasks from this file if it exists.

- **Serialization:** The `FileManager` class handles saving and loading tasks using Java's `ObjectOutputStream` and `ObjectInputStream`.
- **File Location:** The `tasks.dat` file is created in the same directory as the `.java` files.

---

## **Contributing**

Contributions are welcome! If you'd like to contribute to this project, follow these steps:

1. Fork the repository.
2. Create a new branch for your feature:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add feature or fix"
   ```
4. Push to the branch:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request describing your changes.

---

## **License**

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## **Future Improvements**

Here are some ideas for future enhancements:

- **GUI Interface:** Build a graphical user interface (GUI) using JavaFX or Swing.
- **Database Integration:** Replace file persistence with a database (e.g., MySQL or SQLite).
- **Search and Filter:** Add filtering options (e.g., show overdue tasks or filter by priority).
- **Web Version:** Develop a web-based version of the Task Manager using Spring Boot.
- **Unit Tests:** Add unit tests using JUnit to ensure robustness.

---

### **Author**

- **Name:** Gabriell Maia do Amaral Duarte
- **GitHub:** [Your GitHub Profile](https://github.com/maia2a)
- **Email:** gabrielldeveloper@gmail.com

---
