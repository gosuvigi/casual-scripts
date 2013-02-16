package ro.ratoi.virgiliu.array

/**
 * In an array many numbers are duplicates. How do you find them?
 * 
 * @author Virgiliu
 * Sep 22, 2011
 *
 */
final class MultipleArrayDuplicates {

	/**
	 * In an array many numbers are duplicates. How do you find them?
	 * 
	 * @param numbers
	 * @return
	 */
	final multipleDuplicate(def numbers) {
		def counts = [:]
		for (i in numbers) {
			counts[i] = counts.get(i, 0) + 1
		}
		return counts.keySet().findAll { counts[it] > 1 }
	}
}
