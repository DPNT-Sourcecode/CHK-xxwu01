package befaster.solutions.CHK;

public class Offer {

	private final int count;
	private final int price;
	private final Character freeItem;
	private final int freeItemCount;

	public Offer(final int count, final int price, final Character freeItem, final int freeItemCount) {
		this.count = count;
		this.price = price;
		this.freeItem = freeItem;
		this.freeItemCount = freeItemCount;
	}

	public int getFreeItemCount() {
		return freeItemCount;
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
