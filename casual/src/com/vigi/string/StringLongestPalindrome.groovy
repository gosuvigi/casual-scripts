package com.vigi.string



/**
 * Write a function to find out longest palindrome in a given string.
 * 
 * @author Virgiliu
 * Sep 22, 2011
 *
 */
final class StringLongestPalindrome {

	/**
	 * Write a function to find out longest palindrome in a given string.
	 * 
	 * @param s
	 * @return
	 */
	final String longestPalindrome(final String s) {
		if (s.size() <= 1) {
			return s
		}

		def maxPal = ''
		def pal = new StringPalindromes()
		for (i in 0..s.size() - 1) {
			for (j in i..s.size() - 1) {
				def curr = s[i..j]
				if (pal.isPalindrome(curr)) {
					if (curr.size() > maxPal.size()) {
						maxPal = s[i..j]
					}
				}
			}
		}

		return maxPal.toLowerCase()
	}
}
