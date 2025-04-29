import java.util.*;
class User {
    int userID;
    String name;
    int age;
    ArrayList<Integer> friendIDs;
    User next;
    User(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friendIDs = new ArrayList<>();
        this.next = null;
    }
}
class SocialMedia {
    User head = null;
    void addUser(int userID, String name, int age) {
        User newUser = new User(userID, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
    }
    User getUserByID(int userID) {
        User temp = head;
        while (temp != null) {
            if (temp.userID == userID) return temp;
            temp = temp.next;
        }
        return null;
    }
    void addFriendConnection(int userID1, int userID2) {
        User user1 = getUserByID(userID1);
        User user2 = getUserByID(userID2);
        if (user1 != null && user2 != null && userID1 != userID2) {
            if (!user1.friendIDs.contains(userID2)) user1.friendIDs.add(userID2);
            if (!user2.friendIDs.contains(userID1)) user2.friendIDs.add(userID1);
        }
    }
    void removeFriendConnection(int userID1, int userID2) {
        User user1 = getUserByID(userID1);
        User user2 = getUserByID(userID2);
        if (user1 != null && user2 != null) {
            user1.friendIDs.remove(Integer.valueOf(userID2));
            user2.friendIDs.remove(Integer.valueOf(userID1));
        }
    }
    void findMutualFriends(int userID1, int userID2) {
        User user1 = getUserByID(userID1);
        User user2 = getUserByID(userID2);
        if (user1 != null && user2 != null) {
            System.out.println("Mutual Friends of " + userID1 + " and " + userID2 + ":");
            for (int id1 : user1.friendIDs) {
                if (user2.friendIDs.contains(id1)) {
                    System.out.println("User ID: " + id1);
                }
            }
        }
    }
    void displayFriends(int userID) {
        User user = getUserByID(userID);
        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            for (int id : user.friendIDs) {
                User friend = getUserByID(id);
                if (friend != null) {
                    System.out.println(friend.name + " (ID: " + friend.userID + ")");
                }
            }
        }
    }
    void searchUser(String keyword) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(keyword) || String.valueOf(temp.userID).equals(keyword)) {
                System.out.println("Found: " + temp.name + " (ID: " + temp.userID + ", Age: " + temp.age + ")");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("User not found.");
    }
    void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIDs.size() + " friend(s).");
            temp = temp.next;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialMedia sm = new SocialMedia();
        int choice;
        do {
            System.out.println("\nSocial Media Friend Management System");
            System.out.println("1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Find Mutual Friends");
            System.out.println("5. Display Friends of a User");
            System.out.println("6. Search User by Name or ID");
            System.out.println("7. Count Friends for Each User");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int userID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sm.addUser(userID, name, age);
                    break;
                case 2:
                    System.out.print("Enter User ID 1: ");
                    int u1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int u2 = sc.nextInt();
                    sm.addFriendConnection(u1, u2);
                    break;
                case 3:
                    System.out.print("Enter User ID 1: ");
                    int r1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int r2 = sc.nextInt();
                    sm.removeFriendConnection(r1, r2);
                    break;
                case 4:
                    System.out.print("Enter User ID 1: ");
                    int m1 = sc.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int m2 = sc.nextInt();
                    sm.findMutualFriends(m1, m2);
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    int dID = sc.nextInt();
                    sm.displayFriends(dID);
                    break;
                case 6:
                    System.out.print("Enter Name or User ID to search: ");
                    String keyword = sc.nextLine();
                    sm.searchUser(keyword);
                    break;
                case 7:
                    sm.countFriends();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 8);
        sc.close();
    }
}