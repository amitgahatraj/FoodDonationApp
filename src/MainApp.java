import java.util.Scanner;
import Impl.AuthService;
import Impl.DonationService;
import Model.User;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        DonationService donationService = new DonationService();
        User loggedInUser = null;

        while (true) {
            System.out.println("1. Register User\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter contact info: ");
                    String contactInfo = scanner.nextLine();
                    System.out.print("Are you a donor? (yes/no): ");
                    boolean isDonor = scanner.nextLine().equalsIgnoreCase("yes");
                    authService.registerUser(username, password, contactInfo, isDonor);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    loggedInUser = authService.loginUser(username, password);
                    if (loggedInUser != null) {
                        userMenu(scanner, loggedInUser, donationService);
                    }
                    break;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void userMenu(Scanner scanner, User loggedInUser, DonationService donationService) {
        while (true) {
            System.out.println("1. Register Donation\n2. View Donations\n3. Claim Donation\n4. Track Donation Status\n5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    if (loggedInUser.isDonor()) {
                        System.out.print("Enter food type: ");
                        String type = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        double quantity = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter unit (KG, grams, liters): ");
                        String unit = scanner.nextLine();
                        System.out.print("Enter expiration date (YYYY-MM-DD): ");
                        String expirationDate = scanner.nextLine();
                        donationService.registerDonation(loggedInUser, type, quantity, unit, expirationDate);
                    } else {
                        System.out.println("Only donors can register donations.");
                    }
                    break;

                case 2:
                    donationService.viewDonations();
                    break;

                case 3:
                    if (!loggedInUser.isDonor()) {
                        System.out.print("Enter the index of the donation to claim: ");
                        int index = scanner.nextInt();
                        donationService.claimDonation(loggedInUser, index);
                    } else {
                        System.out.println("Donors cannot claim donations.");
                    }
                    break;

                case 4:
                    System.out.print("Enter the index of the donation to track: ");
                    int index = scanner.nextInt();
                    donationService.trackDonationStatus(index);
                    break;

                case 5:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
