import java.util.Scanner;
class Student {
    int rollNo;
    String name;
    int age;
    char grade;
    Student next;
    Student(int rollNo, String name, int age, char grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}
class StudentLinkedList {
    Student head;
    void addAtBeginning(Student s) {
        s.next = head;
        head = s;
    }
    void addAtEnd(Student s) {
        if (head == null) {
            head = s;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = s;
    }
    void addAtPosition(Student s, int position) {
        if (position <= 1) {
            addAtBeginning(s);
            return;
        }
        Student temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            addAtEnd(s);
            return;
        }
        s.next = temp.next;
        temp.next = s;
    }
    void deleteByRollNo(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Student with Roll No " + rollNo + " deleted.");
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Student not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student with Roll No " + rollNo + " deleted.");
        }
    }
    void searchByRollNo(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Student Found:");
                printStudent(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }
    void updateGrade(int rollNo, char newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Grade updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }
    void displayAll() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }
        Student temp = head;
        while (temp != null) {
            printStudent(temp);
            temp = temp.next;
        }
    }
    void printStudent(Student s) {
        System.out.println("Roll No: " + s.rollNo);
        System.out.println("Name   : " + s.name);
        System.out.println("Age    : " + s.age);
        System.out.println("Grade  : " + s.grade);
        System.out.println(" ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentLinkedList list = new StudentLinkedList();
        while (true) {
            System.out.println("\nStudent Record Menu");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Position");
            System.out.println("4. Delete Student by Roll No");
            System.out.println("5. Search Student by Roll No");
            System.out.println("6. Update Grade by Roll No");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            int roll;
            String name;
            int age;
            char grade;
            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    grade = sc.next().charAt(0);
                    list.addAtBeginning(new Student(roll, name, age, grade));
                    break;
                case 2:
                    System.out.print("Enter Roll No: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    grade = sc.next().charAt(0);
                    list.addAtEnd(new Student(roll, name, age, grade));
                    break;
                case 3:
                    System.out.print("Enter Roll No: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    grade = sc.next().charAt(0);
                    System.out.print("Enter Position to Insert: ");
                    int pos = sc.nextInt();
                    list.addAtPosition(new Student(roll, name, age, grade), pos);
                    break;
                case 4:
                    System.out.print("Enter Roll No to Delete: ");
                    roll = sc.nextInt();
                    list.deleteByRollNo(roll);
                    break;
                case 5:
                    System.out.print("Enter Roll No to Search: ");
                    roll = sc.nextInt();
                    list.searchByRollNo(roll);
                    break;
                case 6:
                    System.out.print("Enter Roll No to Update Grade: ");
                    roll = sc.nextInt();
                    System.out.print("Enter New Grade: ");
                    grade = sc.next().charAt(0);
                    list.updateGrade(roll, grade);
                    break;
                case 7:
                    list.displayAll();
                    break;
                case 8:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}