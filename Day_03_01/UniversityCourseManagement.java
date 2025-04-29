import java.util.*;
abstract class CourseType {
    private String name;
    public CourseType(String name) {
        this.name = name;
    }
    public String getName() { return name; }
    public abstract String getEvaluationMethod();
    @Override
    public String toString() {
        return name + " (" + getEvaluationMethod() + ")";
    }
}
class ExamCourse extends CourseType {
    public ExamCourse(String name) {
        super(name);
    }
    public String getEvaluationMethod() {
        return "Exam-Based";
    }
}
class AssignmentCourse extends CourseType {
    public AssignmentCourse(String name) {
        super(name);
    }
    public String getEvaluationMethod() {
        return "Assignment-Based";
    }
}
class ResearchCourse extends CourseType {
    public ResearchCourse(String name) {
        super(name);
    }
    public String getEvaluationMethod() {
        return "Research-Based";
    }
}
class Course<T extends CourseType> {
    private T courseType;
    private String department;
    public Course(String department, T courseType) {
        this.department = department;
        this.courseType = courseType;
    }
    public T getCourseType() { return courseType; }
    public String getDepartment() { return department; }
    @Override
    public String toString() {
        return department + " - " + courseType.toString();
    }
}
public class UniversityCourseManagement {
    public static void displayCourses(List<? extends CourseType> courses) {
        for (CourseType course : courses) {
            System.out.println(course);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course<? extends CourseType>> allCourses = new ArrayList<>();
        System.out.print("Enter number of Exam-Based courses: ");
        int examCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < examCount; i++) {
            System.out.print("Enter name of Exam-Based course " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter department: ");
            String dept = scanner.nextLine();
            allCourses.add(new Course<>(dept, new ExamCourse(name)));
        }
        System.out.print("Enter number of Assignment-Based courses: ");
        int assignmentCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < assignmentCount; i++) {
            System.out.print("Enter name of Assignment-Based course " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter department: ");
            String dept = scanner.nextLine();
            allCourses.add(new Course<>(dept, new AssignmentCourse(name)));
        }
        System.out.print("Enter number of Research-Based courses: ");
        int researchCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < researchCount; i++) {
            System.out.print("Enter name of Research-Based course " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter department: ");
            String dept = scanner.nextLine();
            allCourses.add(new Course<>(dept, new ResearchCourse(name)));
        }
        System.out.println("\n--- University Course Catalog ---");
        for (Course<? extends CourseType> course : allCourses) {
            System.out.println(course);
        }
    }
}