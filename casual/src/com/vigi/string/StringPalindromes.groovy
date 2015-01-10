package com.vigi.string

/**
 * Write code to check if a String is a palindrome or not.
 * 
 * @author Virgiliu
 * Sep 19, 2011
 *
 */
class StringPalindromes {

	final boolean isPalindrome(final String s) {
		//		int len = s.size()
		//		if (len == 0 || len == 1) {
		//			return true
		//		}
		//		def i = 0
		//		while (i < len / 2) {
		//			if (s[i].toLowerCase() != s[-i - 1].toLowerCase()) {
		//				return false
		//			}
		//			i++
		//		}
		//		return true
		return s.toLowerCase() == s.reverse().toLowerCase();
	}
}
