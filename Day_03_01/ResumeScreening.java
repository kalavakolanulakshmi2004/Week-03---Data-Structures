import java.util.*;
abstract class JobRole {
    String name;
    JobRole(String name) {
        this.name = name;
    }
    abstract void displayDetails();
}
class SoftwareEngineer extends JobRole {
    SoftwareEngineer(String name) {
        super(name);
    }
    void displayDetails() {
        System.out.println("Software Engineer: " + name);
    }
}
class DataScientist extends JobRole {
    DataScientist(String name) {
        super(name);
    }
    void displayDetails() {
        System.out.println("Data Scientist: " + name);
    }
}
class ProductManager extends JobRole {
    ProductManager(String name) {
        super(name);
    }
    void displayDetails() {
        System.out.println("Product Manager: " + name);
    }
}
class Resume<T extends JobRole> {
    T candidate;
    Resume(T candidate) {
        this.candidate = candidate;
    }
    void processResume() {
        candidate.displayDetails();
    }
}
class ScreeningSystem {
    static void screenCandidates(List<? extends JobRole> resumes) {
        for (JobRole r : resumes) {
            r.displayDetails();
        }
    }
}
public class ResumeScreening {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<JobRole> allResumes = new ArrayList<>();
        System.out.println("Enter number of resumes:");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter role (SE/DS/PM):");
            String role = sc.nextLine();
            System.out.println("Enter candidate name:");
            String name = sc.nextLine();
            switch (role.toUpperCase()) {
                case "SE":
                    allResumes.add(new SoftwareEngineer(name));
                    break;
                case "DS":
                    allResumes.add(new DataScientist(name));
                    break;
                case "PM":
                    allResumes.add(new ProductManager(name));
                    break;
                default:
                    System.out.println("Invalid role");
            }
        }
        System.out.println("Screening Resumes:");
        ScreeningSystem.screenCandidates(allResumes);
    }
}