import java.util.*;
public class FirstLastOccurrence {
    public static int findFirst(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int first = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                first = mid;
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return first;
    }
    public static int findLast(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int last = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                last = mid;
                left = mid + 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return last;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter sorted array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.print("Enter target element: ");
        int target = sc.nextInt();
        int first = findFirst(arr, target);
        int last = findLast(arr, target);
        if (first == -1) {
            System.out.println("Element not found.");
        } else {
            System.out.println("First occurrence at index: " + first);
            System.out.println("Last occurrence at index: " + last);
        }
        sc.close();
    }
}