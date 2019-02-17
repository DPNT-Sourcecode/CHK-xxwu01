package befaster.solutions.CHK;

public class Offer {

	private int count;
	private int price;
	private Character freeItem;

	public Offer(final int count, final int price, final Character feeItem) {
		this.count = count;
		this.price = price;
	}

	public void setFreeItem(final Character freeItem) {
		this.freeItem = freeItem;
	}

	public Character getFreeItem() {
		return freeItem;
	}

	public int getCount() {
		return count;
	}

	public void setCount(final int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(final int price) {
		this.price = price;
	}
}

