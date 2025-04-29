import java.util.*;
interface Category {
    String getName();
}
class BookCategory implements Category {
    public String getName() {
        return "Book";
    }
}
class ClothingCategory implements Category {
    public String getName() {
        return "Clothing";
    }
}
class GadgetCategory implements Category {
    public String getName() {
        return "Gadget";
    }
}
class Product<T extends Category> {
    private String name;
    private double price;
    private T category;
    public Product(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public T getCategory() { return category; }
    @Override
    public String toString() {
        return category.getName() + " - " + name + " ($" + price + ")";
    }
}
public class DynamicMarketplace {
    public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double discount = product.getPrice() * percentage / 100;
        product.setPrice(product.getPrice() - discount);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product<? extends Category>> catalog = new ArrayList<>();
        System.out.print("Enter number of books: ");
        int bookCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < bookCount; i++) {
            System.out.print("Enter name of book " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            catalog.add(new Product<>(name, price, new BookCategory()));
        }
        System.out.print("Enter number of clothing items: ");
        int clothingCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < clothingCount; i++) {
            System.out.print("Enter name of clothing item " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            catalog.add(new Product<>(name, price, new ClothingCategory()));
        }
        System.out.print("Enter number of gadgets: ");
        int gadgetCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < gadgetCount; i++) {
            System.out.print("Enter name of gadget " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            catalog.add(new Product<>(name, price, new GadgetCategory()));
        }
        System.out.print("Enter discount percentage to apply: ");
        double discountPercentage = scanner.nextDouble();
        System.out.println("\nApplying " + discountPercentage + "% discount to all products...\n");
        for (Product<? extends Category> product : catalog) {
            applyDiscount(product, discountPercentage);
        }
        System.out.println("--- Product Catalog After Discount ---");
        for (Product<? extends Category> product : catalog) {
            System.out.println(product);
        }
    }
}