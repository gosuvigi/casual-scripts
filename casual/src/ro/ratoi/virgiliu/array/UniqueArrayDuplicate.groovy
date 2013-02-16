package ro.ratoi.virgiliu.array

/**
 * In an array 1-n exactly one number is duplicated. How do you find it?
 * 
 * @author Virgiliu
 * Sep 22, 2011
 *
 */
final class UniqueArrayDuplicate {

	/**
	 * In an array 1-n exactly one number is duplicated. How do you find it?
	 * 
	 * @param numbers
	 * @return
	 */
	final int uniqueDuplicate(def numbers) {
		def n = numbers[-1]
		return numbers.sum() - n * (n + 1) / 2
	}
}
