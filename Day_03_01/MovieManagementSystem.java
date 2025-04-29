import java.util.Scanner;
class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;
    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }
}
public class MovieManagementSystem {
    static Movie head = null;
    static Movie tail = null;
    static void addAtBeginning(Movie movie) {
        if (head == null) {
            head = tail = movie;
        } else {
            movie.next = head;
            movie.prev = null;
            head.prev = movie;
            head = movie;
        }
    }
    static void addAtEnd(Movie movie) {
        if (tail == null) {
            head = tail = movie;
        } else {
            tail.next = movie;
            movie.prev = tail;
            tail = movie;
        }
    }
    static void addAtPosition(Movie movie, int position) {
        if (position <= 1 || head == null) {
            addAtBeginning(movie);
            return;
        }
        Movie temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(movie);
            return;
        }
        movie.next = temp.next;
        movie.prev = temp;
        temp.next.prev = movie;
        temp.next = movie;
    }
    static void deleteByTitle(String title) {
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Movie not found.");
            return;
        }
        if (temp == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            else {
                tail = null;
            }
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null) {
                {
                    tail.next = null;
                }
            }
            else {
                head = null;
            }
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        System.out.println("Movie deleted successfully.");
    }
    static void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println(temp.title + " (" + temp.year + "), Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movie found for director: " + director);
        }
    }
    static void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println(temp.title + " by " + temp.director + " (" + temp.year + ")");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No movie found with rating: " + rating);
        }
    }
    static void displayForward() {
        Movie temp = head;
        if (temp == null) {
            System.out.println("No movies in the list.");
            return;
        }
        while (temp != null) {
            System.out.println(temp.title + " by " + temp.director + " (" + temp.year + ") - Rating: " + temp.rating);
            temp = temp.next;
        }
    }
    static void displayReverse() {
        Movie temp = tail;
        if (temp == null) {
            System.out.println("No movies in the list.");
            return;
        }
        while (temp != null) {
            System.out.println(temp.title + " by " + temp.director + " (" + temp.year + ") - Rating: " + temp.rating);
            temp = temp.prev;
        }
    }
    static void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMovie Management System");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Delete by title");
            System.out.println("5. Search by director");
            System.out.println("6. Search by rating");
            System.out.println("7. Display forward");
            System.out.println("8. Display reverse");
            System.out.println("9. Update rating");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1, 2, 3 -> {
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter director: ");
                    String director = sc.nextLine();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    System.out.print("Enter rating: ");
                    double rating = sc.nextDouble();
                    Movie movie = new Movie(title, director, year, rating);
                    if (choice == 1) {
                        addAtBeginning(movie);
                    }
                    else if (choice == 2) {
                        addAtEnd(movie);
                    }
                    else {
                        System.out.print("Enter position: ");
                        int pos = sc.nextInt();
                        addAtPosition(movie, pos);
                    }
                }
                case 4 -> {
                    System.out.print("Enter title to delete: ");
                    String title = sc.nextLine();
                    deleteByTitle(title);
                }
                case 5 -> {
                    System.out.print("Enter director to search: ");
                    String dir = sc.nextLine();
                    searchByDirector(dir);
                }
                case 6 -> {
                    System.out.print("Enter rating to search: ");
                    double r = sc.nextDouble();
                    searchByRating(r);
                }
                case 7 -> displayForward();
                case 8 -> displayReverse();
                case 9 -> {
                    System.out.print("Enter title to update: ");
                    String title = sc.nextLine();
                    System.out.print("Enter new rating: ");
                    double newRating = sc.nextDouble();
                    updateRating(title, newRating);
                }
                case 10 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 10);
        sc.close();
    }
}

//Alternative code
//import java.util.Scanner;
//class Movie {
//    String title;
//    String director;
//    int year;
//    double rating;
//    Movie next;
//    Movie prev;
//    public Movie(String title, String director, int year, double rating) {
//        this.title = title;
//        this.director = director;
//        this.year = year;
//        this.rating = rating;
//    }
//}
//public class MovieManagementSystem {
//    static Movie head = null;
//    static Movie tail = null;
//    static void addAtBeginning(Movie movie) {
//        if (head == null) {
//            head = tail = movie;
//        } else {
//            movie.next = head;
//            head.prev = movie;
//            head = movie;
//        }
//    }
//    static void addAtEnd(Movie movie) {
//        if (tail == null) {
//            head = tail = movie;
//        } else {
//            tail.next = movie;
//            movie.prev = tail;
//            tail = movie;
//        }
//    }
//    static void addAtPosition(Movie movie, int position) {
//        if (position <= 1 || head == null) {
//            addAtBeginning(movie);
//            return;
//        }
//        Movie temp = head;
//        for (int i = 1; temp != null && i < position - 1; i++) {
//            temp = temp.next;
//        }
//        if (temp == null || temp.next == null) {
//            addAtEnd(movie);
//            return;
//        }
//        movie.next = temp.next;
//        movie.prev = temp;
//        temp.next.prev = movie;
//        temp.next = movie;
//    }
//    static void deleteByTitle(String title) {
//        Movie temp = head;
//        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
//            temp = temp.next;
//        }
//        if (temp == null) {
//            System.out.println("Movie not found.");
//            return;
//        }
//        if (temp == head) {
//            head = head.next;
//            if (head != null) {
//                head.prev = null;
//            } else {
//                tail = null;
//            }
//        } else if (temp == tail) {
//            tail = tail.prev;
//            if (tail != null) {
//                tail.next = null;
//            } else {
//                head = null;
//            }
//        } else {
//            temp.prev.next = temp.next;
//            temp.next.prev = temp.prev;
//        }
//        System.out.println("Movie deleted successfully.");
//    }
//    static void searchByDirector(String director) {
//        Movie temp = head;
//        boolean found = false;
//        while (temp != null) {
//            if (temp.director.equalsIgnoreCase(director)) {
//                System.out.println(temp.title + " (" + temp.year + "), Rating: " + temp.rating);
//                found = true;
//            }
//            temp = temp.next;
//        }
//        if (!found) {
//            System.out.println("No movie found for director: " + director);
//        }
//    }
//    static void searchByRating(double rating) {
//        Movie temp = head;
//        boolean found = false;
//        while (temp != null) {
//            if (temp.rating == rating) {
//                System.out.println(temp.title + " by " + temp.director + " (" + temp.year + ")");
//                found = true;
//            }
//            temp = temp.next;
//        }
//        if (!found) {
//            System.out.println("No movie found with rating: " + rating);
//        }
//    }
//    static void displayForward() {
//        Movie temp = head;
//        if (temp == null) {
//            System.out.println("No movies in the list.");
//            return;
//        }
//        while (temp != null) {
//            System.out.println(temp.title + " by " + temp.director + " (" + temp.year + ") - Rating: " + temp.rating);
//            temp = temp.next;
//        }
//    }
//    static void displayReverse() {
//        Movie temp = tail;
//        if (temp == null) {
//            System.out.println("No movies in the list.");
//            return;
//        }
//        while (temp != null) {
//            System.out.println(temp.title + " by " + temp.director + " (" + temp.year + ") - Rating: " + temp.rating);
//            temp = temp.prev;
//        }
//    }
//    static void updateRating(String title, double newRating) {
//        Movie temp = head;
//        while (temp != null) {
//            if (temp.title.equalsIgnoreCase(title)) {
//                temp.rating = newRating;
//                System.out.println("Rating updated successfully.");
//                return;
//            }
//            temp = temp.next;
//        }
//        System.out.println("Movie not found.");
//    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int choice;
//        do {
//            System.out.println("\nMovie Management System");
//            System.out.println("1. Add at beginning");
//            System.out.println("2. Add at end");
//            System.out.println("3. Add at position");
//            System.out.println("4. Delete by title");
//            System.out.println("5. Search by director");
//            System.out.println("6. Search by rating");
//            System.out.println("7. Display forward");
//            System.out.println("8. Display reverse");
//            System.out.println("9. Update rating");
//            System.out.println("10. Exit");
//            System.out.print("Enter your choice: ");
//            choice = sc.nextInt();
//            sc.nextLine();
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter Title: ");
//                    String title1 = sc.nextLine();
//                    System.out.print("Enter Director: ");
//                    String director1 = sc.nextLine();
//                    System.out.print("Enter Year: ");
//                    int year1 = sc.nextInt();
//                    System.out.print("Enter Rating: ");
//                    double rating1 = sc.nextDouble();
//                    addAtBeginning(new Movie(title1, director1, year1, rating1));
//                    break;
//                case 2:
//                    System.out.print("Enter Title: ");
//                    String title2 = sc.nextLine();
//                    System.out.print("Enter Director: ");
//                    String director2 = sc.nextLine();
//                    System.out.print("Enter Year: ");
//                    int year2 = sc.nextInt();
//                    System.out.print("Enter Rating: ");
//                    double rating2 = sc.nextDouble();
//                    addAtEnd(new Movie(title2, director2, year2, rating2));
//                    break;
//                case 3:
//                    System.out.print("Enter Title: ");
//                    String title3 = sc.nextLine();
//                    System.out.print("Enter Director: ");
//                    String director3 = sc.nextLine();
//                    System.out.print("Enter Year: ");
//                    int year3 = sc.nextInt();
//                    System.out.print("Enter Rating: ");
//                    double rating3 = sc.nextDouble();
//                    System.out.print("Enter Position to Insert: ");
//                    int pos = sc.nextInt();
//                    addAtPosition(new Movie(title3, director3, year3, rating3), pos);
//                    break;
//                case 4:
//                    System.out.print("Enter Title to Delete: ");
//                    String delTitle = sc.nextLine();
//                    deleteByTitle(delTitle);
//                    break;
//                case 5:
//                    System.out.print("Enter Director to Search: ");
//                    String searchDirector = sc.nextLine();
//                    searchByDirector(searchDirector);
//                    break;
//                case 6:
//                    System.out.print("Enter Rating to Search: ");
//                    double searchRating = sc.nextDouble();
//                    searchByRating(searchRating);
//                    break;
//                case 7:
//                    displayForward();
//                    break;
//                case 8:
//                    displayReverse();
//                    break;
//                case 9:
//                    System.out.print("Enter Title to Update Rating: ");
//                    String updateTitle = sc.nextLine();
//                    System.out.print("Enter New Rating: ");
//                    double newRating = sc.nextDouble();
//                    updateRating(updateTitle, newRating);
//                    break;
//                case 10:
//                    System.out.println("Exiting...");
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        } while (choice != 10);
//        sc.close();
//    }
//}