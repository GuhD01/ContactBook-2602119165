import java.util.Scanner;

public class ContactBook {

    private static class Contact {
        public String name;
        public String phone;
        public String email;
        public Contact next;

        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.next = null;
        }

        public String toString() {
            return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
        }
    }

    private static Contact head = null;

    private static void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        String phone;
        while (true) {
            System.out.print("Enter phone number (12 digits): ");
            phone = scanner.nextLine();
            if (phone.length() == 12) {
                break;
            }
            System.out.println("Phone number must be 12 digits!");
        }
        String email;
        while (true) {
            System.out.print("Enter email address: ");
            email = scanner.nextLine();
            if (email.contains("@")) {
                break;
            }
            System.out.println("Email address must contain '@'!");
        }

        Contact contact = new Contact(name, phone, email);
        if (head == null) {
            head = contact;
        } else {
            Contact current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = contact;
        }
        System.out.println("Contact added successfully!");
    }

    private static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name of contact to delete: ");
        String name = scanner.nextLine();

        if (head == null) {
            System.out.println("Contact book is empty!");
            return;
        }

        if (head.name.equals(name)) {
            head = head.next;
            System.out.println("Contact deleted successfully!");
            return;
        }

        Contact current = head;
        while (current.next != null && !current.next.name.equals(name)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Contact not found!");
            return;
        }

        current.next = current.next.next;
        System.out.println("Contact deleted successfully!");
    }

    private static void searchContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name of contact to search: ");
        String name = scanner.nextLine();

        Contact current = head;
        while (current != null && !current.name.equals(name)) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Contact not found!");
        } else {
            System.out.println(current);
        }
    }

    private static void searchEmail() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email address to search: ");
        String email = scanner.nextLine();

        Contact current = head;
        boolean found = false;
        while (current != null) {
            if (current.email.equals(email)) {
                System.out.println(current);
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No contacts found with that email address!");
        }
    }

    private static void printList() {
        Contact current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    private static void displayMenu() {

        System.out.println("******************************");
        System.out.println("(A)dd");
        System.out.println("(D)elete");
        System.out.println("(E)mail Search");
        System.out.println("(P)rint list");
        System.out.println("(S)earch");
        System.out.println("(Q)uit");
        System.out.println("******************************");
        System.out.print("Please enter a command: ");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "a":
                    addContact();
                    break;
                case "d":
                    deleteContact();
                    break;
                case "e":
                    searchEmail();
                    break;
                case "p":
                    printList();
                    break;
                case "s":
                    searchContact();
                    break;
                case "q":
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid command. Please try again.");
            }

        }
    }
}
