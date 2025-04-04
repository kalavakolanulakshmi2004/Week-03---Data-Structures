import java.util.Scanner;
class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;
    public Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}
public class TaskScheduler {
    static Task head = null;
    static Task tail = null;
    static Task current = null;
    static void addAtBeginning(Task task) {
        if (head == null) {
            head = tail = task;
            task.next = task;
        } else {
            task.next = head;
            tail.next = task;
            head = task;
        }
    }
    static void addAtEnd(Task task) {
        if (head == null) {
            head = tail = task;
            task.next = task;
        } else {
            tail.next = task;
            task.next = head;
            tail = task;
        }
    }
    static void addAtPosition(Task task, int position) {
        if (position <= 1 || head == null) {
            addAtBeginning(task);
            return;
        }
        Task temp = head;
        for (int i = 1; temp.next != head && i < position - 1; i++) {
            temp = temp.next;
        }
        task.next = temp.next;
        temp.next = task;
        if (temp == tail) {
            tail = task;
        }
    }
    static void deleteById(int id) {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }
        Task temp = head, prev = tail;
        boolean found = false;
        do {
            if (temp.id == id) {
                found = true;
                if (temp == head) {
                    if (head == tail) {
                        head = tail = null;
                    } else {
                        head = head.next;
                        tail.next = head;
                    }
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                if (current == temp) {
                    current = current.next;
                }
                System.out.println("Task deleted successfully.");
                break;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("Task not found.");
        }
    }
    static void viewCurrentTask() {
        if (current == null) {
            if (head == null) {
                System.out.println("No tasks available.");
                return;
            }
            current = head;
        }
        System.out.println("Current Task: ID: " + current.id + ", " + current.name + ", Priority: " + current.priority + ", Due: " + current.dueDate);
        current = current.next;
    }
    static void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }
    static void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No task found with priority: " + priority);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n Task Scheduler ");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Delete Task by ID");
            System.out.println("5. View Current Task and Move to Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1, 2, 3 -> {
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Priority (1-5): ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Due Date (dd-mm-yyyy): ");
                    String dueDate = sc.nextLine();
                    Task task = new Task(id, name, priority, dueDate);
                    if (choice == 1) {
                        addAtBeginning(task);
                    }
                    else if (choice == 2) {
                        addAtEnd(task);
                    }
                    else {
                        System.out.print("Enter position to insert: ");
                        int pos = sc.nextInt();
                        addAtPosition(task, pos);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Task ID to delete: ");
                    int id = sc.nextInt();
                    deleteById(id);
                }
                case 5 -> viewCurrentTask();
                case 6 -> displayTasks();
                case 7 -> {
                    System.out.print("Enter Priority to search: ");
                    int p = sc.nextInt();
                    searchByPriority(p);
                }
                case 8 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 8);
        sc.close();
    }
}