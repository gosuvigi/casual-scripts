package com.vigi.string;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Sep 19, 2011
 *
 */
final class StringPalindromesTest extends GroovyTestCase {

	private StringPalindromes palString
	static final PALINDROMES = [
		'',
		'a',
		'bob',
		'boob',
		'civic',
		'radar',
		'level',
		'racecar',
		'Shamahs' // with ignore case
	]

	static final notPalindromes = [
		'ab',
		'abc',
		'boab',
		'radaar',
		'Shamash'
	]

	@Before
	public final void setUp() throws Exception {
		palString = new StringPalindromes()
	}

	@After
	public final void tearDown() throws Exception {
		palString = null
	}

	@Test
	public final void testIsPalindrome() {
		assertTrue(PALINDROMES.every { palString.isPalindrome(it) } )
	}

	@Test
	public final void testIsNotPalindrome() {
		assertFalse(notPalindromes.every { palString.isPalindrome(it) },  )
	}
}
