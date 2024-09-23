import java.util.*;

class User {
    String username;
    String password;
    String contactInfo;
    boolean isDonor; // true for donor, false for recipient

    User(String username, String password, String contactInfo, boolean isDonor) {
        this.username = username;
        this.password = password;
        this.contactInfo = contactInfo;
        this.isDonor = isDonor;
    }
}

class Donation {
    String type;
    double quantity;
    String unit;
    String expirationDate;
    boolean claimed;

    Donation(String type, double quantity, String unit, String expirationDate) {
        this.type = type;
        this.quantity = quantity;
        this.unit = unit;
        this.expirationDate = expirationDate;
        this.claimed = false;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Quantity: %.2f %s, Expiration: %s, Claimed: %s",
                type, quantity, unit, expirationDate, claimed ? "Yes" : "No");
    }
}

public class FoodDonationApp {
    private static Map<String, User> users = new HashMap<>();
    private static List<Donation> donations = new ArrayList<>();
    private static User loggedInUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("1. Register User\n2. Login\n3. Exit");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    if (login(scanner)) {
                        userMenu(scanner);
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Are you a donor? (yes/no): ");
        boolean isDonor = scanner.nextLine().equalsIgnoreCase("yes");

        users.put(username, new User(username, password, contactInfo, isDonor));
        System.out.println("User registered successfully.");
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            loggedInUser = user;
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private static void userMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Register Donation\n2. View Donations\n3. Claim Donation\n4. Track Donation Status\n5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    if (loggedInUser.isDonor) {
                        registerDonation(scanner);
                    } else {
                        System.out.println("Only donors can register donations.");
                    }
                    break;
                case 2:
                    viewDonations();
                    break;
                case 3:
                    if (!loggedInUser.isDonor) {
                        claimDonation(scanner);
                    } else {
                        System.out.println("Only recipients can claim donations.");
                    }
                    break;
                case 4:
                    trackDonationStatus(scanner);
                    break;
                case 5:
                    loggedInUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerDonation(Scanner scanner) {
        System.out.print("Enter food type: ");
        String type = scanner.nextLine();
        System.out.print("Enter quantity: ");
        double quantity = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Enter unit (KG, grams, liters): ");
        String unit = scanner.nextLine();
        System.out.print("Enter expiration date (YYYY-MM-DD): ");
        String expirationDate = scanner.nextLine();

        donations.add(new Donation(type, quantity, unit, expirationDate));
        System.out.println("Donation registered successfully.");
    }

    private static void viewDonations() {
        if (donations.isEmpty()) {
            System.out.println("No donations available.");
        } else {
            System.out.println("Available Donations:");
            for (Donation donation : donations) {
                System.out.println(donation);
            }
        }
    }

    private static void claimDonation(Scanner scanner) {
        viewDonations();
        System.out.print("Enter the index of the donation to claim (0 to " + (donations.size() - 1) + "): ");
        int index = scanner.nextInt();

        if (index >= 0 && index < donations.size()) {
            Donation donation = donations.get(index);
            if (!donation.claimed) {
                donation.claimed = true;
                System.out.println("Donation claimed successfully.");
            } else {
                System.out.println("This donation has already been claimed.");
            }
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void trackDonationStatus(Scanner scanner) {
        if (donations.isEmpty()) {
            System.out.println("No donations available to track.");
            return;
        }

        System.out.print("Enter the index of the donation to track (0 to " + (donations.size() - 1) + "): ");
        int index = scanner.nextInt();

        if (index >= 0 && index < donations.size()) {
            Donation donation = donations.get(index);
            System.out.println("Donation Status: " + (donation.claimed ? "Claimed" : "Available"));
        } else {
            System.out.println("Invalid index.");
        }
    }
}
