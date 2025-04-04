import java.util.Scanner;
class Process {
    int processID, burstTime, priority;
    Process next;
    Process(int processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}
public class RoundRobinScheduling {
    Process head = null;
    void addProcess(int processID, int burstTime, int priority) {
        Process newProcess = new Process(processID, burstTime, priority);
        if (head == null) {
            head = newProcess;
            newProcess.next = head;
        } else {
            Process temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newProcess;
            newProcess.next = head;
        }
    }
    void removeProcess(int processID) {
        if (head == null) return;
        Process temp = head;
        if (temp.processID == processID) {
            if (head.next == head) {
                head = null;
            } else {
                Process last = head;
                while (last.next != head) {
                    last = last.next;
                }
                head = head.next;
                last.next = head;
            }
            return;
        }
        while (temp.next != head && temp.next.processID != processID) {
            temp = temp.next;
        }
        if (temp.next.processID == processID) {
            temp.next = temp.next.next;
        }
    }
    void roundRobinScheduling(int timeQuantum) {
        if (head == null) return;
        int totalProcesses = 0;
        Process counter = head;
        do {
            totalProcesses++;
            counter = counter.next;
        } while (counter != head);
        int[] originalBurstTimes = new int[totalProcesses];
        int[] processIDs = new int[totalProcesses];
        Process temp = head;
        int index = 0;
        do {
            originalBurstTimes[index] = temp.burstTime;
            processIDs[index] = temp.processID;
            temp = temp.next;
            index++;
        } while (temp != head);
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        int elapsedTime = 0;
        int remainingProcesses = totalProcesses;
        temp = head;
        while (remainingProcesses > 0) {
            if (temp.burstTime > timeQuantum) {
                temp.burstTime -= timeQuantum;
                elapsedTime += timeQuantum;
                temp = temp.next;
            } else {
                elapsedTime += temp.burstTime;
                int originalBurst = 0;
                for (int i = 0; i < totalProcesses; i++) {
                    if (processIDs[i] == temp.processID) {
                        originalBurst = originalBurstTimes[i];
                        break;
                    }
                }
                int turnAroundTime = elapsedTime;
                int waitingTime = turnAroundTime - originalBurst;
                totalTurnAroundTime += turnAroundTime;
                totalWaitingTime += waitingTime;
                int toRemove = temp.processID;
                temp = temp.next;
                removeProcess(toRemove);
                remainingProcesses--;
            }
        }
        System.out.println("Average Waiting Time: " + (float) totalWaitingTime / totalProcesses);
        System.out.println("Average Turnaround Time: " + (float) totalTurnAroundTime / totalProcesses);
    }
    void displayProcesses() {
        if (head == null) return;
        Process temp = head;
        do {
            System.out.println("Process ID: " + temp.processID + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobinScheduling rr = new RoundRobinScheduling();
        System.out.print("Enter the number of processes: ");
        int numProcesses = sc.nextInt();
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter Process ID for Process " + (i + 1) + ": ");
            int processID = sc.nextInt();
            System.out.print("Enter Burst Time for Process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            System.out.print("Enter Priority for Process " + (i + 1) + ": ");
            int priority = sc.nextInt();
            rr.addProcess(processID, burstTime, priority);
        }
        System.out.print("Enter Time Quantum: ");
        int timeQuantum = sc.nextInt();
        System.out.println("\nProcesses in Queue:");
        rr.displayProcesses();
        rr.roundRobinScheduling(timeQuantum);
        sc.close();
    }
}