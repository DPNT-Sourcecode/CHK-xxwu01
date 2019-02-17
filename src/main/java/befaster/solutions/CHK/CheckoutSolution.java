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
	}

	public Integer checkout(final String skus) {
		try {
			populateCheckoutBasket(skus);
		} catch (final InvalidSkuException e) {
			checkoutBasket.clear();
			return -1;
		}
		int totalPrice = 0;
		final Set<Character> checkoutSkus = checkoutBasket.keySet();
		for (final Character sku : checkoutSkus) {
			final Integer count = checkoutBasket.get(sku);
			final int priceForItems = calculatePriceForItems(sku, count);
			totalPrice += priceForItems;
		}
		checkoutBasket.clear();
		return totalPrice;
	}

	private int calculatePriceForItems(final Character sku, int count) {
		int price = 0;
		final List<Offer> offers = offersMap.get(sku);
		if (offers != null) {
			int intermediatePrice = 0;
			for (final Offer offer : offers) {
				final int amountToBeDeducted = applyFreeItem(offer);
				if (amountToBeDeducted != 0) {
					price -= amountToBeDeducted;
				} else {
					final int offerCount = offer.getCount();
					final int i = count / offerCount;
					count = count % offerCount;

					intermediatePrice += (i * offer.getPrice());
				}
			}
			intermediatePrice += count * individualPrice.get(sku);
			price += intermediatePrice;
		} else {
			price += count * individualPrice.get(sku);
		}
		return price;
	}

	private int applyFreeItem(final Offer offer) {
		if (offer.getFreeItem() != null) {
			final Integer freeItemPrice = individualPrice.get(offer.getFreeItem());
			final int freeItemCount = offer.getFreeItemCount();
			final int amountToBeDeducted = freeItemCount * freeItemCount;
			return amountToBeDeducted;
		}
		return 0;
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
