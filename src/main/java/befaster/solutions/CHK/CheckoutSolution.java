package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

import befaster.runner.SolutionNotImplementedException;

public class CheckoutSolution {

	private final Map<Character, Integer> map = new HashMap<>();

	public CheckoutSolution() {
		map.put('A', 50);
		map.put('B', 30);
		map.put('C', 20);
		map.put('D', 15);
	}

	public Integer checkout(final String skus) {
		throw new SolutionNotImplementedException();
	}
}

