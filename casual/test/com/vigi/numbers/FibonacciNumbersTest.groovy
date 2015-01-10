package com.vigi.numbers;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Sep 27, 2011
 *
 */
final class FibonacciNumbersTest {

	private static final def NTH_FIB = [
		1,
		1,
		2,
		3,
		5,
		8,
		13,
		21,
		34,
		55,
		89
	]
	private def fib

	@Before
	public final void setUp() throws Exception {
		fib = new FibonacciNumbers()
	}

	@After
	public final void tearDown() throws Exception {
		fib = null
	}

	@Test
	public final void testNthFibonacciNumberIterative() {
		for (i in 1..11) {
			assertEquals(NTH_FIB[i - 1], fib.nthFibonacciNumberIterative(i))
		}
	}

	@Test
	public final void testNthFibonacciNumberRecursive() {
		for (i in 1..11) {
			assertEquals(NTH_FIB[i - 1], fib.nthFibonacciNumberRecursive(i))
		}
	}
}
