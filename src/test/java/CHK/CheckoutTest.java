package CHK;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import befaster.solutions.CHK.CheckoutSolution;

public class CheckoutTest {

	@Test
	public void testB() {
		final CheckoutSolution solution = new CheckoutSolution();
		final int checkout = solution.checkout("B");
		assertEquals(30, checkout);
	}

	@Test
	public void testBB() {
		final CheckoutSolution solution = new CheckoutSolution();
		final int checkout = solution.checkout("BB");
		assertEquals(45, checkout);
	}

	@Test
	public void testBBB() {
		final CheckoutSolution solution = new CheckoutSolution();
		final int checkout = solution.checkout("BBB");
		assertEquals(75, checkout);
	}

	@Test
	public void testAB() {
		final CheckoutSolution solution = new CheckoutSolution();
		final int checkout = solution.checkout("AB");
		assertEquals(80, checkout);
	}
}
