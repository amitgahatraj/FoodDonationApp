package Impl;

import java.util.ArrayList;
import java.util.List;
import Interface.DonationInterface;
import Model.*;

public class DonationService implements DonationInterface {
    private List<Donation> donations = new ArrayList<>();

    @Override
    public void registerDonation(User user, String type, double quantity, String unit, String expirationDate) {
        if (user.isDonor()) {
            donations.add(new Donation(type, quantity, unit, expirationDate));
            System.out.println("Donation registered successfully.");
        } else {
            System.out.println("Only donors can register donations.");
        }
    }

    @Override
    public void viewDonations() {
        if (donations.isEmpty()) {
            System.out.println("No donations available.");
        } else {
            System.out.println("Available Donations:");
            for (int i = 0; i < donations.size(); i++) {
                System.out.println(i + ". " + donations.get(i));
            }
        }
    }

    @Override
    public void claimDonation(User recipient, int donationIndex) {
        if (!recipient.isDonor() && donationIndex >= 0 && donationIndex < donations.size()) {
            Donation donation = donations.get(donationIndex);
            if (!donation.isClaimed()) {
                donation.setClaimed(true);
                System.out.println("Donation claimed successfully.");
            } else {
                System.out.println("This donation has already been claimed.");
            }
        } else {
            System.out.println("Invalid donation index or action.");
        }
    }

    @Override
    public void trackDonationStatus(int donationIndex) {
        if (donationIndex >= 0 && donationIndex < donations.size()) {
            Donation donation = donations.get(donationIndex);
            System.out.println("Donation Status: " + (donation.isClaimed() ? "Claimed" : "Available"));
        } else {
            System.out.println("Invalid donation index.");
        }
    }
}

