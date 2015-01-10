package com.vigi.string;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Sep 22, 2011
 *
 */
final class StringLongestPalindromeTest {

	private def pal
	static final PALINDROMES = [
		'',
		'a',
		'aa',
		'aba',
		'abba',
		'radar',
		'yabadabadoo',
		'BANANA',
		'mississippi'
	]
	static final EXPECTED_PALINDROMES = [
		'',
		'a',
		'aa',
		'aba',
		'abba',
		'radar',
		'abadaba',
		'anana',
		'ississi'
	]

	@Before
	public final void setUp() throws Exception {
		pal = new StringLongestPalindrome()
	}

	@After
	public final void tearDown() throws Exception {
		pal = null
	}

	@Test
	public final void testLongestPalindrome() {
		assertEquals(EXPECTED_PALINDROMES,
				PALINDROMES.collect { pal.longestPalindrome(it) })
	}
}
