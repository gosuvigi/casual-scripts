package ro.ratoi.virgiliu.string

/**
 * Find the first instance of a string in another string.
 * @author Virgiliu
 * Oct 3, 2011
 *
 */
final class StringFirstOccurence {

	/**
	 * Finds the first instance of a string in another string.
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	final int strStr(final String haystack, final String needle) {
		for (h in 0..haystack.size() - 1) {
			for (n in 0..needle.size() - 1) {
				if (h + n >= haystack.size()) {
					break
				}
				if (haystack[h + n] != needle[n]) {
					break
				} else if (n == needle.size() - 1) {
					return h
				}
			}
		}
		return -1
	}
}
