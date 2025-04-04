import java.util.Scanner;
class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;
    public Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
}
public class InventorySystem {
    static Item head = null;
    static void addAtBeginning(Item item) {
        item.next = head;
        head = item;
    }
    static void addAtEnd(Item item) {
        if (head == null) {
            head = item;
        } else {
            Item temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = item;
        }
    }
    static void addAtPosition(Item item, int pos) {
        if (pos <= 1 || head == null) {
            addAtBeginning(item);
            return;
        }
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        item.next = temp.next;
        temp.next = item;
    }
    static void removeById(int id) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        if (head.id == id) {
            head = head.next;
            System.out.println("Item removed.");
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
            System.out.println("Item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }
    static void updateQuantity(int id, int newQty) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = newQty;
                System.out.println("Quantity updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }
    static void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                printItem(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }
    static void searchByName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                printItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Item not found.");
        }
    }
    static void displayAll() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        Item temp = head;
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }
    static void printItem(Item item) {
        System.out.println("ID: " + item.id + ", Name: " + item.name + ", Qty: " + item.quantity + ", Price: $" + item.price);
    }
    static void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: $" + total);
    }
    static void sortInventory(String criteria, boolean ascending) {
        head = mergeSort(head, criteria, ascending);
    }
    static Item mergeSort(Item node, String criteria, boolean ascending) {
        if (node == null || node.next == null) return node;
        Item mid = getMiddle(node);
        Item midNext = mid.next;
        mid.next = null;
        Item left = mergeSort(node, criteria, ascending);
        Item right = mergeSort(midNext, criteria, ascending);
        return merge(left, right, criteria, ascending);
    }
    static Item merge(Item a, Item b, String criteria, boolean asc) {
        if (a == null) return b;
        if (b == null) return a;
        boolean condition;
        if (criteria.equalsIgnoreCase("name")) {
            condition = asc ? a.name.compareToIgnoreCase(b.name) <= 0 : a.name.compareToIgnoreCase(b.name) > 0;
        } else {
            condition = asc ? a.price <= b.price : a.price > b.price;
        }
        if (condition) {
            a.next = merge(a.next, b, criteria, asc);
            return a;
        } else {
            b.next = merge(a, b.next, criteria, asc);
            return b;
        }
    }
    static Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nInventory Menu");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity by ID");
            System.out.println("6. Search by ID");
            System.out.println("7. Search by Name");
            System.out.println("8. Display All Items");
            System.out.println("9. Calculate Total Inventory Value");
            System.out.println("10. Sort Inventory");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1, 2, 3 -> {
                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Item Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble(); sc.nextLine();
                    Item item = new Item(name, id, qty, price);
                    if (choice == 1) addAtBeginning(item);
                    else if (choice == 2) addAtEnd(item);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        addAtPosition(item, pos);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Item ID to remove: ");
                    int id = sc.nextInt();
                    removeById(id);
                }
                case 5 -> {
                    System.out.print("Enter Item ID to update: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new Quantity: ");
                    int qty = sc.nextInt();
                    updateQuantity(id, qty);
                }
                case 6 -> {
                    System.out.print("Enter Item ID to search: ");
                    int id = sc.nextInt();
                    searchById(id);
                }
                case 7 -> {
                    System.out.print("Enter Item Name to search: ");
                    String name = sc.nextLine();
                    searchByName(name);
                }
                case 8 -> displayAll();
                case 9 -> calculateTotalValue();
                case 10 -> {
                    System.out.print("Sort by (name/price): ");
                    String criteria = sc.nextLine();
                    System.out.print("Ascending (true/false): ");
                    boolean asc = sc.nextBoolean();
                    sortInventory(criteria, asc);
                    System.out.println("Inventory sorted.");
                }
                case 11 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 11);
        sc.close();
    }
}