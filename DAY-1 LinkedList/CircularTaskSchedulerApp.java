class Task {
    int taskId;
    String name;
    int priority;
    String dueDate;
    Task next;
    public Task(int taskId, String name, int priority, String dueDate) {
        this.taskId = taskId;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}
class TaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task current = null;
    public void addTaskAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            tail.next = newTask;
            head = newTask;
        }
    }
    public void addTaskAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            newTask.next = head;
            tail = newTask;
        }
    }
    public void addTaskAtPosition(int position, int id, String name, int priority, String dueDate) {
        if (position <= 1 || head == null) {
            addTaskAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        int index = 1;
        while (index < position - 1 && temp.next != head) {
            temp = temp.next;
            index++;
        }
        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;
        }
    }
    public void removeTaskById(int taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        Task current = head;
        Task previous = tail;
        do {
            if (current.taskId == taskId) {
                if (current == head) {
                    if (head == tail) {
                        head = tail = null;
                    } else {
                        head = head.next;
                        tail.next = head;
                    }
                } else if (current == tail) {
                    tail = previous;
                    tail.next = head;
                } else {
                    previous.next = current.next;
                }
                System.out.println("Removed task ID: " + taskId);
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);
        System.out.println("Task ID " + taskId + " not found.");
    }
    public void viewCurrentTaskAndMoveNext() {
        if (current == null) {
            current = head;
        }
        if (current != null) {
            System.out.println("Current Task: [ID: " + current.taskId + ", Name: " + current.name +
                    ", Priority: " + current.priority + ", Due Date: " + current.dueDate + "]");
            current = current.next;
        } else {
            System.out.println("No tasks available.");
        }
    }
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        System.out.println("Task List:");
        do {
            System.out.println("ID: " + temp.taskId + ", Name: " + temp.name +
                    ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        boolean found = false;
        System.out.println("Tasks with priority " + priority + ":");
        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.taskId + ", Name: " + temp.name + ", Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No task found with priority " + priority);
        }
    }
}
public class CircularTaskSchedulerApp {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTaskAtEnd(1, "Design Module", 2, "2025-04-20");
        scheduler.addTaskAtBeginning(2, "Project Setup", 1, "2025-04-15");
        scheduler.addTaskAtEnd(3, "Database Config", 2, "2025-04-25");
        scheduler.addTaskAtPosition(2, 4, "Requirement Gathering", 3, "2025-04-18");

        scheduler.displayAllTasks();

        System.out.println("\nView tasks cyclically:");
        scheduler.viewCurrentTaskAndMoveNext(); // 2
        scheduler.viewCurrentTaskAndMoveNext(); // 4
        scheduler.viewCurrentTaskAndMoveNext(); // 1

        System.out.println("\nSearching tasks with priority 2:");
        scheduler.searchByPriority(2);

        System.out.println("\nDeleting task ID 3:");
        scheduler.removeTaskById(3);
        scheduler.displayAllTasks();
    }
}
