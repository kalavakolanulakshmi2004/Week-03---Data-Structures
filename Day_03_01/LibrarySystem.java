import java.util.*;
class Book {
    String title, author, genre, bookID;
    boolean isAvailable;
    Book next, prev;
    Book(String title, String author, String genre, String bookID, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}
public class LibrarySystem {
    static Book head = null, tail = null;
    static void addAtBeginning(Book newBook) {
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }
    static void addAtEnd(Book newBook) {
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }
    static void addAtPosition(Book newBook, int pos) {
        if (pos <= 1) {
            addAtBeginning(newBook);
            return;
        }
        Book temp = head;
        int count = 1;
        while (temp != null && count < pos - 1) {
            temp = temp.next;
            count++;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(newBook);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            temp.next.prev = newBook;
            temp.next = newBook;
        }
    }
    static void removeByID(String bookID) {
        Book temp = head;
        while (temp != null && !temp.bookID.equals(bookID)) {
            temp = temp.next;
        }
        if (temp == null) return;
        if (temp == head) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
            else head = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }
    static void search(String keyword) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(keyword) || temp.author.equalsIgnoreCase(keyword)) {
                System.out.println(temp.title + " | " + temp.author + " | " + temp.genre + " | " + temp.bookID + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            }
            temp = temp.next;
        }
    }
    static void updateStatus(String bookID, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookID.equals(bookID)) {
                temp.isAvailable = status;
                break;
            }
            temp = temp.next;
        }
    }
    static void displayForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.author + " | " + temp.genre + " | " + temp.bookID + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.next;
        }
    }
    static void displayReverse() {
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.author + " | " + temp.genre + " | " + temp.bookID + " | " + (temp.isAvailable ? "Available" : "Not Available"));
            temp = temp.prev;
        }
    }
    static void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total books: " + count);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add at Beginning\n2.Add at End\n3.Add at Position\n4.Remove by ID\n5.Search\n6.Update Status\n7.Display Forward\n8.Display Reverse\n9.Count\n10.Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter Title, Author, Genre, ID, Availability (true/false): ");
                    addAtBeginning(new Book(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextBoolean()));
                    break;
                case 2:
                    System.out.print("Enter Title, Author, Genre, ID, Availability (true/false): ");
                    addAtEnd(new Book(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextBoolean()));
                    break;
                case 3:
                    System.out.print("Enter Title, Author, Genre, ID, Availability (true/false) and Position: ");
                    Book b = new Book(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextBoolean());
                    int pos = sc.nextInt();
                    addAtPosition(b, pos);
                    break;
                case 4:
                    System.out.print("Enter Book ID: ");
                    removeByID(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Title or Author to search: ");
                    search(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter Book ID and new availability (true/false): ");
                    updateStatus(sc.nextLine(), sc.nextBoolean());
                    break;
                case 7:
                    displayForward();
                    break;
                case 8:
                    displayReverse();
                    break;
                case 9:
                    countBooks();
                    break;
                case 10:
                    return;
            }
        }
    }
}