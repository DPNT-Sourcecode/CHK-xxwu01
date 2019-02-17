package CHK;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import befaster.solutions.CHK.CheckoutSolution;

public class CheckoutTest {

	final CheckoutSolution solution = new CheckoutSolution();

	@Test
	public void testB() {
		final int checkout = solution.checkout("B");
		assertEquals(30, checkout);
	}

	@Test
	public void testBB() {
		final int checkout = solution.checkout("BB");
		assertEquals(45, checkout);
	}

	@Test
	public void testBBB() {
		final int checkout = solution.checkout("BBB");
		assertEquals(75, checkout);
	}

	@Test
	public void testAB() {
		final int checkout = solution.checkout("AB");
		assertEquals(80, checkout);
	}

	@Test
	public void testABCD() {
		final int checkout = solution.checkout("ABCD");
		assertEquals(115, checkout);
	}
}


