package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

	private final Map<Character, Integer> map = new HashMap<>();
	private final Map<Character, Offer> offersMap = new HashMap<>();

	public CheckoutSolution() {
		map.put('A', 50);
		map.put('B', 30);
		map.put('C', 20);
		map.put('D', 15);

		offersMap.put('A', new Offer(3, 130));
		offersMap.put('B', new Offer(2, 45));
	}

	public Integer checkout(final String skus) {
//		final char[] charArray = skus.toCharArray();

		return 50;
	}
}
