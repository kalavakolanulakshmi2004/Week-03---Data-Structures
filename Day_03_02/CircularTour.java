import java.util.*;
class PetrolPump {
    int petrol;
    int distance;

    PetrolPump(int petrol, int distance) {
        this.petrol = petrol;
        this.distance = distance;
    }
}
public class CircularTour {
    public static int findStartingPoint(List<PetrolPump> pumps) {
        int start = 0, deficit = 0, balance = 0;

        for (int i = 0; i < pumps.size(); i++) {
            balance += pumps.get(i).petrol - pumps.get(i).distance;
            if (balance < 0) {
                start = i + 1;
                deficit += balance;
                balance = 0;
            }
        }

        return (balance + deficit >= 0) ? start : -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<PetrolPump> pumps = new ArrayList<>();
        System.out.print("Enter number of petrol pumps: ");
        int n = sc.nextInt();
        System.out.println("Enter petrol and distance for each pump:");
        for (int i = 0; i < n; i++) {
            int petrol = sc.nextInt();
            int distance = sc.nextInt();
            pumps.add(new PetrolPump(petrol, distance));
        }
        int start = findStartingPoint(pumps);
        if (start != -1) {
            System.out.println("Start at petrol pump index: " + start);
        } else {
            System.out.println("No feasible starting point found.");
        }
        sc.close();
    }
}