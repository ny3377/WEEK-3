class StudentNode {
    int rollNo;
    String name;
    int age;
    String grade;
    StudentNode next;

    public StudentNode(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}
class StudentLinkedList {
    private StudentNode head;
    public void addAtBeginning(int rollNo, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        newNode.next = head;
        head = newNode;
    }
    public void addAtEnd(int rollNo, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        if (head == null) {
            head = newNode;
            return;
        }
        StudentNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    public void addAtPosition(int position, int rollNo, String name, int age, String grade) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        if (position == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }
        StudentNode current = head;
        for (int i = 1; current != null && i < position - 1; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Position out of bounds.");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }
    public void deleteByRollNo(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Student with Roll No " + rollNo + " deleted.");
            return;
        }
        StudentNode current = head;
        while (current.next != null && current.next.rollNo != rollNo) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Student not found.");
            return;
        }
        current.next = current.next.next;
        System.out.println("Student with Roll No " + rollNo + " deleted.");
    }
    public void searchByRollNo(int rollNo) {
        StudentNode current = head;
        while (current != null) {
            if (current.rollNo == rollNo) {
                System.out.println("Student Found:");
                System.out.println("Roll No: " + current.rollNo);
                System.out.println("Name: " + current.name);
                System.out.println("Age: " + current.age);
                System.out.println("Grade: " + current.grade);
                return;
            }
            current = current.next;
        }
        System.out.println("Student not found.");
    }
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }
        StudentNode current = head;
        System.out.println("Student Records:");
        while (current != null) {
            System.out.println("Roll No: " + current.rollNo + ", Name: " + current.name +
                    ", Age: " + current.age + ", Grade: " + current.grade);
            current = current.next;
        }
    }
    public void updateGrade(int rollNo, String newGrade) {
        StudentNode current = head;
        while (current != null) {
            if (current.rollNo == rollNo) {
                current.grade = newGrade;
                System.out.println("Grade updated for Roll No " + rollNo);
                return;
            }
            current = current.next;
        }
        System.out.println("Student not found.");
    }
}
public class StudentRecordManager {
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();

        list.addAtBeginning(101, "Alice", 20, "A");
        list.addAtEnd(102, "Bob", 21, "B");
        list.addAtPosition(2, 103, "Charlie", 19, "C");
        list.addAtEnd(104, "Diana", 22, "B");

        list.displayAll();

        System.out.println("\nSearching for roll no 103:");
        list.searchByRollNo(103);

        System.out.println("\nUpdating grade for roll no 102 to A+:");
        list.updateGrade(102, "A+");
        list.displayAll();

        System.out.println("\nDeleting student with roll no 101:");
        list.deleteByRollNo(101);
        list.displayAll();
    }
}
