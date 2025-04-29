import java.util.*;
abstract class WarehouseItem {
    private String name;
    private double price;
    public WarehouseItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public abstract String getCategory();
    @Override
    public String toString() {
        return getCategory() + " - " + name + " ($" + price + ")";
    }
}
class Electronics extends WarehouseItem {
    public Electronics(String name, double price) {
        super(name, price);
    }
    public String getCategory() {
        return "Electronics";
    }
}
class Groceries extends WarehouseItem {
    public Groceries(String name, double price) {
        super(name, price);
    }
    public String getCategory() {
        return "Groceries";
    }
}
class Furniture extends WarehouseItem {
    public Furniture(String name, double price) {
        super(name, price);
    }
    public String getCategory() {
        return "Furniture";
    }
}
class Storage<T extends WarehouseItem> {
    private List<T> items = new ArrayList<>();
    public void addItem(T item) {
        items.add(item);
    }
    public List<T> getItems() {
        return items;
    }
}
public class SmartWarehouseItem {
    public static void displayItems(List<? extends WarehouseItem> items) {
        for (WarehouseItem item : items) {
            System.out.println(item);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage<Electronics> electronicsStorage = new Storage<>();
        Storage<Groceries> groceriesStorage = new Storage<>();
        Storage<Furniture> furnitureStorage = new Storage<>();
        System.out.print("Enter number of electronics items:");
        int eCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < eCount; i++) {
            System.out.print("Enter name of electronic item "+(i+1)+": ");
            String name = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            electronicsStorage.addItem(new Electronics(name, price));
        }
        System.out.print("Enter number of groceries items:");
        int gCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < gCount; i++) {
            System.out.print("Enter name of grocery item "+(i+1)+": ");
            String name = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            groceriesStorage.addItem(new Groceries(name, price));
        }
        System.out.print("Enter number of furniture items:");
        int fCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < fCount; i++) {
            System.out.print("Enter name of furniture item "+(i+1)+": ");
            String name = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            furnitureStorage.addItem(new Furniture(name, price));
        }
        System.out.println("\n--- All Warehouse Items ---");
        displayItems(electronicsStorage.getItems());
        displayItems(groceriesStorage.getItems());
        displayItems(furnitureStorage.getItems());
    }
}