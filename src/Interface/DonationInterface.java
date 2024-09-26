package Interface;
import Model.User;

public interface DonationInterface {
    void registerDonation(User user, String type, double quantity, String unit, String expirationDate);
    void viewDonations();
    void claimDonation(User recipient, int donationIndex);
    void trackDonationStatus(int donationIndex);
}

