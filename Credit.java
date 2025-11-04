package Week08;

public class Credit extends Payment2 {

    private int installment;                 
    private final int maxInstallmentAmount;  
    public Credit(Item item, int maxInstallmentAmount) {
        super(item);
        this.maxInstallmentAmount = maxInstallmentAmount;
        this.installment = 0;                
    }

    @Override
    public int pay() {
        if (isPaidOff()) return 0;

        installment++;
        if (installment >= maxInstallmentAmount) {
            isPaidOff = true;
        }
        return getItem().getPrice() / maxInstallmentAmount;
    }

    @Override
    public int getRemainingAmount() {
        if (isPaidOff()) return 0;
        int perInstallment = getItem().getPrice() / maxInstallmentAmount;
        return getItem().getPrice() - (installment * perInstallment);
    }

    public int getMaxInstallmentAmount() {
        return maxInstallmentAmount;
    }

    public int getInstallment() {
        return installment;
    }
}



