package ro.ratoi.virgiliu.array

/**
 * How do you find the second highest number in an array of integers?
 * 
 * @author Virgiliu
 * Sep 22, 2011
 *
 */
final class SecondHighestNumber {

	/**
	 * How do you find the second highest number in an array of integers?
	 * 
	 * @param numbers
	 * @return
	 */
	final int secondHighest(final numbers) {
		if (numbers.size() < 2) {
			throw new IllegalArgumentException('There must be at least two numbers.')
		}

		def high1 = Integer.MIN_VALUE;
		def high2 = Integer.MIN_VALUE;
		for (int num in numbers) {
			if (num > high1) {
				high2 = high1 // critical
				high1 = num
			} else if (num > high2) {
				high2 = num
			}
		}

		return high2
	}
}
