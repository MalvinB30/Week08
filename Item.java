package Week08;

public class Item {
	private String Name;
	private String Type;
	private int Price;
	
	public Item(String Name, String Type, int Price) {
		this.Name = Name;
		this.Type = Type;
		this.Price = Price;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}
	
	
}
