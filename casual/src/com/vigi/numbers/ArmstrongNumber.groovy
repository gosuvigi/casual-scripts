package com.vigi.numbers

/**
 * An Armstrong number or narcissistic number is a number that is
 * the sum of its own digits each raised to the power
 * of the number of digits.
 * 
 * @author Virgiliu
 * Sep 27, 2011
 *
 */
final class ArmstrongNumber {

	/**
	 * An Armstrong number or narcissistic number is a number that is
	 * the sum of its own digits each raised to the power
	 * of the number of digits.
	 * <p>
	 * 153 = 1^3 + 5^3 + 3^3
	 * @param n
	 * @return
	 */
	final boolean isArmstrongNumber(int n) {
		def s = n.toString()
		def l = s.size()
		def sum = 0
		for (i in s) {
			sum += Integer.parseInt(i).power(l)
		}
		return sum == n
	}
}
