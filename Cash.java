package Week08;

public class Cash extends Payment2 {

    public Cash(Item item) {
        super(item);
    }

    @Override
    public int pay() {
        if (isPaidOff()) return 0;
        isPaidOff = true;
        return getItem().getPrice();
    }

    public String getClassName() {
        return "CASH";
    }
}




