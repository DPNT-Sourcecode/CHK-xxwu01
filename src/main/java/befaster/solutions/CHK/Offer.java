package befaster.solutions.CHK;

public class Offer {

	private final int count;
	private final int price;
	private final Character freeItem;

	public Offer(final int count, final int price, final Character freeItem) {
		this.count = count;
		this.price = price;
		this.freeItem = freeItem;
	}

	public Character getFreeItem() {
		return freeItem;
	}

	public int getCount() {
		return count;
	}

	public int getPrice() {
		return price;
	}

}


