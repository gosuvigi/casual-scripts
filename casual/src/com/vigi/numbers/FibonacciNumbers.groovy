package com.vigi.numbers

/**
 * 
 * @author Virgiliu
 * Sep 27, 2011
 *
 */
final class FibonacciNumbers {

	/**
	 * Computes the nth Fibonacci number iterative.
	 * 
	 * @param n
	 * @return
	 */
	final int nthFibonacciNumberIterative(final int n) {
		if (n < 1) {
			throw new IllegalArgumentException('Number must be greater than 1.')
		}
		if (n == 1 || n == 2) {
			return 1
		}
		def nth1 = 1
		def nth2 = 1
		def nth = 0
		for (i in 3..n) {
			nth = nth1 + nth2
			nth2 = nth1
			nth1 = nth
		}
		return nth
	}

	/**
	 * Computes the nth Fibonacci number recursive.
	 * @param n
	 * @return
	 */
	final int nthFibonacciNumberRecursive(final int n) {
		if (n < 1) {
			throw new IllegalArgumentException('Number must be greater than 1.')
		}
		if (n == 1 || n == 2) {
			return 1
		}
		return nthFibonacciNumberRecursive(n - 1) + nthFibonacciNumberRecursive(n - 2)
	}
}
