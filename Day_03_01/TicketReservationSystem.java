import java.util.Scanner;
class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;
    Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}
class TicketReservationSystem {
    Ticket head = null;
    Ticket tail = null;
    int size = 0;
    void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
        size++;
    }
    void removeTicket(int ticketID) {
        if (head == null) return;
        Ticket current = head;
        Ticket previous = null;
        do {
            if (current.ticketID == ticketID) {
                if (previous == null) {
                    if (head == tail) {
                        head = null;
                        tail = null;
                    } else {
                        head = current.next;
                        tail.next = head;
                    }
                } else {
                    previous.next = current.next;
                    if (current == tail) tail = previous;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);
    }
    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName + ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Booking Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }
    void searchTicket(String searchTerm) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket current = head;
        boolean found = false;
        do {
            if (current.customerName.contains(searchTerm) || current.movieName.contains(searchTerm)) {
                System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName + ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Booking Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);
        if (!found) {
            System.out.println("No tickets found for the search term: " + searchTerm);
        }
    }
    int totalBookedTickets() {
        return size;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();
        int choice;
        do {
            System.out.println("\n1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Show All Tickets");
            System.out.println("4. Search Ticket");
            System.out.println("5. Total Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movie = sc.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seat = sc.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String time = sc.nextLine();
                    system.addTicket(id, name, movie, seat, time);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to cancel: ");
                    int cancelID = sc.nextInt();
                    sc.nextLine();
                    system.removeTicket(cancelID);
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String search = sc.nextLine();
                    system.searchTicket(search);
                    break;
                case 5:
                    System.out.println("Total Tickets: " + system.totalBookedTickets());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 6);
        sc.close();
    }
}