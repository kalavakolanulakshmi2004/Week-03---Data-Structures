import java.util.*;
public class SortStack {
    public static void sort(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int temp = stack.pop();
            sort(stack);
            insertInSortedOrder(stack, temp);
        }
    }
    public static void insertInSortedOrder(Stack<Integer> stack, int value) {
        if (stack.isEmpty() || value < stack.peek()) {
            stack.push(value);
        } else {
            int temp = stack.pop();
            insertInSortedOrder(stack, value);
            stack.push(temp);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            stack.push(sc.nextInt());
        }
        sort(stack);
        System.out.println("Sorted stack (top to bottom):");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        sc.close();
    }
}