package com.vigi.string

/**
 * 
 * @author Virgiliu
 * Oct 3, 2011
 *
 */
final class ReverseString {

	/**
	 * C like solution.
	 * @param s
	 * @return
	 */
	final String reverseIterative(final String s) {
		def z = s.size()
		if (z == 0 || z == 1) {
			return s
		}
		def rev = s.toCharArray()
		for (int i = 0; i < z / 2; i++) {
			char t = rev[i]
			rev[i] = rev[z - i - 1]
			rev[z - i - 1] = t
		}
		return new String(rev)
	}

	final String reverseRecursive(final String s) {
		def z = s.size()
		if (z == 0 || z == 1) {
			return s
		}
		return s[z - 1] + reverseRecursive(s[0..z - 2])
	}
}
