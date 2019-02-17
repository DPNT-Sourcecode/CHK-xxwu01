package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckoutSolution {

	private final Map<Character, Integer> individualPrice = new HashMap<>();
	private final Map<Character, List<Offer>> offersMap = new HashMap<>();
	private final Map<Character, Integer> checkoutBasket = new HashMap<>();

	private final char[] any3 = new char[] { 'S', 'T', 'X', 'Y', 'Z' };

	private void any3() {

	}

	public CheckoutSolution() {
		individualPrice.put('A', 50);
		individualPrice.put('B', 30);
		individualPrice.put('C', 20);
		individualPrice.put('D', 15);
		individualPrice.put('E', 40);
		individualPrice.put('F', 10);
		individualPrice.put('G', 20);
		individualPrice.put('H', 10);
		individualPrice.put('I', 35);
		individualPrice.put('J', 60);
		individualPrice.put('K', 70);
		individualPrice.put('L', 90);
		individualPrice.put('M', 15);
		individualPrice.put('N', 40);
		individualPrice.put('O', 10);
		individualPrice.put('P', 50);
		individualPrice.put('Q', 30);
		individualPrice.put('R', 50);
		individualPrice.put('S', 20);
		individualPrice.put('T', 20);
		individualPrice.put('U', 40);
		individualPrice.put('V', 50);
		individualPrice.put('W', 20);
		individualPrice.put('X', 17);
		individualPrice.put('Y', 20);
		individualPrice.put('Z', 21);

		final List<Offer> offersA = new ArrayList<>();
		offersA.add(new Offer(5, 200, null, 0));
		offersA.add(new Offer(3, 130, null, 0));
		offersMap.put('A', offersA);

		final List<Offer> offersB = new ArrayList<>();
		offersB.add(new Offer(2, 45, null, 0));
		offersMap.put('B', offersB);

		final List<Offer> offersE = new ArrayList<>();
		offersE.add(new Offer(2, 0, 'B', 1));
		offersMap.put('E', offersE);

		final List<Offer> offersH = new ArrayList<>();
		offersH.add(new Offer(10, 80, null, 0));
		offersH.add(new Offer(5, 45, null, 0));
		offersMap.put('H', offersH);

		final List<Offer> offersK = new ArrayList<>();
		offersK.add(new Offer(2, 120, null, 0));
		offersMap.put('K', offersK);

		final List<Offer> offersN = new ArrayList<>();
		offersN.add(new Offer(3, 0, 'M', 1));
		offersMap.put('N', offersN);

		final List<Offer> offersP = new ArrayList<>();
		offersP.add(new Offer(5, 200, null, 0));
		offersMap.put('P', offersP);

		final List<Offer> offersQ = new ArrayList<>();
		offersQ.add(new Offer(3, 80, null, 0));
		offersMap.put('Q', offersQ);

		final List<Offer> offersR = new ArrayList<>();
		offersR.add(new Offer(3, 0, 'Q', 1));
		offersMap.put('R', offersR);

		final List<Offer> offersU = new ArrayList<>();
		offersU.add(new Offer(3, 0, 'U', 1));
		offersMap.put('U', offersU);

		final List<Offer> offersV = new ArrayList<>();
		offersV.add(new Offer(3, 130, null, 0));
		offersV.add(new Offer(2, 90, null, 0));
		offersMap.put('V', offersV);
	}

	public Integer checkout(final String skus) {
		try {
			populateCheckoutBasket(skus);
		} catch (final InvalidSkuException e) {
			checkoutBasket.clear();
			return -1;
		}
		int totalPrice = 0;
		if (checkoutBasket.containsKey('U')) {
			totalPrice += calculatePriceForFree('U', 4);
			checkoutBasket.remove('U');
		}
		if (checkoutBasket.containsKey('F')) {
			totalPrice += calculatePriceForFree('F', 3);
			checkoutBasket.remove('F');
		}
		if (checkoutBasket.containsKey('E')) {
			final int priceForItemsE = calculatePriceForItems('E', checkoutBasket.get('E'));
			totalPrice += priceForItemsE;
			checkoutBasket.remove('E');
		}
		if (checkoutBasket.containsKey('N')) {
			final int priceForItemsN = calculatePriceForItems('N', checkoutBasket.get('N'));
			totalPrice += priceForItemsN;
			checkoutBasket.remove('N');
		}
		if (checkoutBasket.containsKey('R')) {
			final int priceForItemsR = calculatePriceForItems('R', checkoutBasket.get('R'));
			totalPrice += priceForItemsR;
			checkoutBasket.remove('R');
		}
		final Set<Character> checkoutSkus = checkoutBasket.keySet();
		for (final Character sku : checkoutSkus) {
			final Integer count = checkoutBasket.get(sku);
			final int priceForItems = calculatePriceForItems(sku, count);
			totalPrice += priceForItems;
		}
		checkoutBasket.clear();
		return totalPrice;
	}

	private int calculatePriceForItems(final Character sku, int countInTheBasket) {
		int price = 0;
		final List<Offer> offers = offersMap.get(sku);
		if (offers != null) {
			int intermediatePrice = 0;
			for (final Offer offer : offers) {
				if (offer.getFreeItem() != null) {
					applyFreeItem(sku, offer);
					countInTheBasket = checkoutBasket.get(sku);
				} else {
					final int offerRequiredCount = offer.getRequiredCount();
					final int i = countInTheBasket / offerRequiredCount;

					countInTheBasket = countInTheBasket % offerRequiredCount;

					intermediatePrice += (i * offer.getPrice());
				}
			}
			intermediatePrice += countInTheBasket * individualPrice.get(sku);
			price += intermediatePrice;
		} else {

			price += checkoutBasket.get(sku) * individualPrice.get(sku);
		}
		return price;
	}

	private int calculatePriceForFree(final Character sku, final int free) {
		final int amountInTheBasket = checkoutBasket.get(sku);
		final Integer price = individualPrice.get(sku);
		int totalPrice = 0;
		int y = 0;
		for (int i = 1; i <= amountInTheBasket; i++) {
			y++;
			if (y == free) {
				y = 0;
				continue;
			}
			totalPrice += price;
		}
		return totalPrice;
	}

	private void applyFreeItem(final Character sku, final Offer offer) {
		final int itemsCountInTheBasket = checkoutBasket.get(sku);
		final int requiredCount = offer.getRequiredCount();
		final int amountToRemove = itemsCountInTheBasket / requiredCount;
		final Character freeItem = offer.getFreeItem();
		if (!checkoutBasket.containsKey(freeItem)) {
			return;
		}
		removeFromBasket(offer.getFreeItem(), amountToRemove);
	}

	private void removeFromBasket(final Character sku, final int amountToRemove) {
		for (int y = 0; y < amountToRemove; y++) {
			Integer i = checkoutBasket.get(sku);
			if (i >= 1) {
				checkoutBasket.put(sku, --i);
			}
		}
	}

	private void populateCheckoutBasket(final String skus) throws InvalidSkuException {
		final char[] charArray = skus.toCharArray();
		for (final char sku : charArray) {
			if (!isValid(sku)) {
				throw new InvalidSkuException();
			}
			Integer currentCount = checkoutBasket.get(sku);
			if (currentCount == null) {
				checkoutBasket.put(sku, 1);
			} else {
				checkoutBasket.put(sku, ++currentCount);
			}
		}
	}

	private boolean isValid(final Character sku) {
		return individualPrice.containsKey(sku);
	}
}
