package Model;

public class Donation {
    private String type;
    private double quantity;
    private String unit;
    private String expirationDate;
    private boolean claimed;

    public Donation(String type, double quantity, String unit, String expirationDate) {
        this.type = type;
        this.quantity = quantity;
        this.unit = unit;
        this.expirationDate = expirationDate;
        this.claimed = false;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Quantity: %.2f %s, Expiration: %s, Claimed: %s",
                type, quantity, unit, expirationDate, claimed ? "Yes" : "No");
    }
}
