package Week08;

public class BankTransferPayment extends Payment {
    private String bankAccount;

    public BankTransferPayment(double amount, String bankAccount) {
        super(amount);
        this.bankAccount = bankAccount;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing bank transfer payment of $" + amount + " for bank account " + bankAccount);
    }
}

