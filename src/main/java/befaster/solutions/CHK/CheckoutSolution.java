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
		offersA.add(new Offer(5, 200, null));
		offersA.add(new Offer(3, 130, null));
		offersMap.put('A', offersA);

		final List<Offer> offersB = new ArrayList<>();
		offersB.add(new Offer(2, 45, null));
		offersMap.put('B', offersB);

		final List<Offer> offersE = new ArrayList<>();
		offersE.add(new Offer(2, 1, 'B'));
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
		final Set<Character> keySet = checkoutBasket.keySet();
		for (final Character sku : keySet) {
			Integer count = checkoutBasket.get(sku);
			final List<Offer> offers = offersMap.get(sku);
			if (offers != null) {
				int intermediatePrice = 0;
				for (final Offer offer : offers) {

					final int offerCount = offer.getCount();
					final int i = count / offerCount;
					count = count % offerCount;

					intermediatePrice += (i * offer.getPrice());
				}
				intermediatePrice += count * individualPrice.get(sku);
				totalPrice += intermediatePrice;
			} else {
				totalPrice += count * individualPrice.get(sku);
			}

		}
		checkoutBasket.clear();
		return totalPrice;
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

