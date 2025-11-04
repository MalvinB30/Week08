package Week08;

public abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void processPayment();

    public void paymentDetails() {
        System.out.println("Processing payment of $" + amount);
    }
}