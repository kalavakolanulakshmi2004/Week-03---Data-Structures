import java.util.*;
interface MealPlan{
    String getMealType();
    String getDetails();
}
class VegetarianMeal implements MealPlan{
    public String getMealType(){
        return "Vegetarian";
    }
    public String getDetails(){
        return "Includes vegetable, grains, dairy, and plant-based protein.";
    }
}
class VeganMeal implements MealPlan{
    public String getMealType(){
        return "Vegan";
    }
    public String getDetails(){
        return "Includes only plant-based foods. No dairy or animal products.";
    }
}
class KetoMeal implements MealPlan {
    public String getMealType() {
        return "Keto";
    }
    public String getDetails() {
        return "High-fat, low-carb diet including meats, dairy, and vegetables.";
    }
}
class HighProteinMeal implements MealPlan {
    public String getMealType() {
        return "High-Protein";
    }
    public String getDetails() {
        return "Focuses on meats, dairy, legumes, and protein-rich foods.";
    }
}
class Meal<T extends MealPlan>{
    private String userName;
    private T mealtype;
    public Meal(String username, T mealtype){
        this.userName=username;
        this.mealtype=mealtype;
    }
    public T getMealType(){
        return mealtype;
    }
    public String getUserNmae(){
        return userName;
    }
    public String toString(){
        return "User: " + userName +"\nMeal Plan: "+ mealtype.getMealType()+"\n Details: "+ mealtype.getDetails();
    }
}
public class PersonalizedMealPlanGenerator{
    public static <T extends MealPlan> Meal<T> generateMealPlan(String userName, T mealType) {
        return new Meal<>(userName, mealType);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Meal<? extends MealPlan>> plans = new ArrayList<>();
        System.out.print("Enter number of users: ");
        int userCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < userCount; i++) {
            System.out.println("\nUser " + (i + 1));
            System.out.print("Enter user name: ");
            String name = scanner.nextLine();
            System.out.println("Choose meal type:\n1. Vegetarian\n2. Vegan\n3. Keto\n4. High-Protein");
            int choice = scanner.nextInt();
            scanner.nextLine();
            Meal<? extends MealPlan> meal = null;
            switch (choice) {
                case 1:
                    meal = generateMealPlan(name, new VegetarianMeal());
                    break;
                case 2:
                    meal = generateMealPlan(name, new VeganMeal());
                    break;
                case 3:
                    meal = generateMealPlan(name, new KetoMeal());
                    break;
                case 4:
                    meal = generateMealPlan(name, new HighProteinMeal());
                    break;
                default:
                    System.out.println("Invalid choice. Skipping...");
                    continue;
            }
            plans.add(meal);
        }
        System.out.println("\n--- Personalized Meal Plans ---");
        for (Meal<? extends MealPlan> plan : plans) {
            System.out.println(plan);
            System.out.println(" \n ");
        }
    }
}