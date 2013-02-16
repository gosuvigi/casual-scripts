package ro.ratoi.virgiliu.string

import java.util.logging.Logger

/**
 * 
 * Print all permutations of a String both iterative and recursive.
 * 
 * @author Virgiliu
 * Sep 19, 2011
 *
 */
final class StringPermutations {

	/**
	 * Used for asserting test is true
	 */
	static final LOG = Logger.getLogger('StringPermutations')

	final void printRecursive(final String st, final String chars) {
		if (chars.size() <= 1) {
			LOG.finer(st + chars)
			return
		}
		for (i in chars) {
			def newString = chars - i
			printRecursive(st + i, newString)
		}
	}

	final void printRecursivePermutations(final String s) {
		printRecursive('', s);
	}

	final void printIterativePermutations(final String s) {
	}
}
