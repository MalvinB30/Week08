package Week08;

public abstract class Payment2 {

    protected boolean isPaidOff = false;
    protected final Item Item;

    public abstract int pay();

    public Payment2(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        this.Item = item;
    }

    public boolean isPaidOff() {
        return isPaidOff;
    }

    public Item getItem() {
        return Item;
    }

    public String getItemName() {
        return Item.getName();
    }

    public String getStatus() {
        if (isPaidOff) {
            return "FINISHED";
        }
        return "IN PROGRESS";
    }

    public int getRemainingAmount() {
        if (isPaidOff) {
            return 0;
        }
        return Item.getPrice();
    }
}
