import java.util.Scanner;
class TextState {
    String content;
    TextState prev, next;
    TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}
class TextEditor {
    TextState head = null, tail = null, current = null;
    int size = 0;
    final int MAX_HISTORY = 10;
    void addState(String content) {
        TextState newState = new TextState(content);
        if (current != null && current.next != null) {
            current.next.prev = null;
        }
        if (current != null) {
            current.next = null;
        }
        if (size == MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }
        if (current == null) {
            head = newState;
            tail = newState;
        } else {
            current.next = newState;
            newState.prev = current;
            tail = newState;
        }
        current = newState;
        size++;
    }
    void undo() {
        if (current != null && current.prev != null) current = current.prev;
    }
    void redo() {
        if (current != null && current.next != null) current = current.next;
    }
    void display() {
        if (current != null) System.out.println("Current Text: " + current.content);
        else System.out.println("No text available.");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        int choice;
        do {
            System.out.println("\n1. Type Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Show Current Text");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    String text = sc.nextLine();
                    editor.addState(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);
        sc.close();
    }
}