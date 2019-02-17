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

	public CheckoutSolution() {
		individualPrice.put('A', 50);
		individualPrice.put('B', 30);
		individualPrice.put('C', 20);
		individualPrice.put('D', 15);
		individualPrice.put('E', 40);
		individualPrice.put('F', 10);

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

		final List<Offer> offersF = new ArrayList<>();
		offersF.add(new Offer(2, 0, 'F', 1));
		offersMap.put('F', offersF);
	}

	public Integer checkout(final String skus) {
		try {
			populateCheckoutBasket(skus);
		} catch (final InvalidSkuException e) {
			checkoutBasket.clear();
			return -1;
		}
		int totalPrice = 0;
		if (checkoutBasket.containsKey('F')) {
			final int priceForItemsF = calculatePriceForItems('F', checkoutBasket.get('F'));
			totalPrice += priceForItemsF;
			checkoutBasket.remove('F');
		}
		if (checkoutBasket.containsKey('E')) {
			final int priceForItemsE = calculatePriceForItems('E', checkoutBasket.get('E'));
			totalPrice += priceForItemsE;
			checkoutBasket.remove('E');
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
				} else {
					final int offerRequiredCount = offer.getRequiredCount();
					final int i = countInTheBasket / offerRequiredCount;

					countInTheBasket = countInTheBasket % offerRequiredCount;

					intermediatePrice += (i * offer.getPrice());
				}
			}
			intermediatePrice += checkoutBasket.get(sku) * individualPrice.get(sku);
			price += intermediatePrice;
		} else {

			price += checkoutBasket.get(sku) * individualPrice.get(sku);
		}
		return price;
	}

	private void applyFreeItem(final Character sku, final Offer offer) {
		final int itemsCountInTheBasket = checkoutBasket.get(sku);
		final int requiredCount = offer.getRequiredCount();
		final int timesToApplyOffer = itemsCountInTheBasket / requiredCount;
		final Character freeItem = offer.getFreeItem();
		if (!checkoutBasket.containsKey(freeItem)) {
			return;
		}
		for (int y = 0; y < timesToApplyOffer; y++) {
			Integer integer = checkoutBasket.get(offer.getFreeItem());
			if (integer >= 1) {
				checkoutBasket.put(offer.getFreeItem(), --integer);
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




