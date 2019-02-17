package CHK;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import befaster.solutions.CHK.CheckoutSolution;

public class CheckoutTest {

	final CheckoutSolution solution = new CheckoutSolution();

	@Test
	public void testEmpty() {
		final int checkout = solution.checkout("");
		assertEquals(0, checkout);
	}

	@Test
	public void testInvalidInput() {
		final int checkout = solution.checkout("x");
		assertEquals(-1, checkout);
	}

	@Test
	public void testA() {
		final int checkout = solution.checkout("A");
		assertEquals(50, checkout);
	}

	@Test
	public void test5A() {
		final int checkout = solution.checkout("AAAAA");
		assertEquals(200, checkout);
	}

	@Test
	public void test8A() {
		final int checkout = solution.checkout("AAAAAAAA");
		assertEquals(330, checkout);
	}

	@Test
	public void test9A() {
		final int checkout = solution.checkout("AAAAAAAAA");
		assertEquals(380, checkout);
	}

	@Test
	public void test9AB() {
		final int checkout = solution.checkout("AAAAAAAAAB");
		assertEquals(410, checkout);
	}

	@Test
	public void test9A2B() {
		final int checkout = solution.checkout("AAAAAAAAABB");
		assertEquals(425, checkout);
	}

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

	@Test
	public void testE() {
		final int checkout = solution.checkout("E");
		assertEquals(40, checkout);
	}
}
