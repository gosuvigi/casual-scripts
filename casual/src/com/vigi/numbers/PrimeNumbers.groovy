package com.vigi.numbers


/**
 * 
 * @author Virgiliu
 * Sep 27, 2011
 *
 */
final class PrimeNumbers {

	final boolean isPrime(final int n) {
		if (n < 2) {
			return false
		}
		!(2..<n).any { n % it == 0 }
	}

	/**
	 * Find all prime numbers up to a given number.
	 * 
	 * @param no
	 * @return
	 */
	final def findPrimes(final int no) {
		def primes = []
		for (i in 1..no) {
			if (isPrime(i)) {
				primes.add(i)
			}
		}

		return primes
	}
}
