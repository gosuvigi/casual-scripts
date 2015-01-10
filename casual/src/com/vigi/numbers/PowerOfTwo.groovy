package com.vigi.numbers

/**
 * Checks whether a number is power of two or not.
 * 
 * @author Virgiliu
 * Sep 27, 2011
 *
 */
final class PowerOfTwo {

	/**
	 * The trick here is to know that an unsigned integer which is
	 * a power of 2 has only one of its bits as 1.
	 * So a simple solution would be to loop through the bits and 
	 * count the number of 1s.
	 * There is another solution which uses bit manipulation.
	 * x & (x-1) will always give you a 0 if x is a power of 2.
	 * 
	 * @param no
	 * @return
	 */
	final boolean isPowerOfTwo(final int no) {
		if (no < 0) {
			throw new IllegalArgumentException('Must be positive.')
		}
		return no != 0 && !(no & (no - 1))
	}

	final boolean isPowerOfTwoRecursive(final int no) {
		if (no == 2) {
			return true
		}
		if (no % 2 == 1) {
			return false
		}
		return isPowerOfTwoRecursive((int) no / 2)
	}
}
