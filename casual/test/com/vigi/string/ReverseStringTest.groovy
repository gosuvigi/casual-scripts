package com.vigi.string;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Oct 3, 2011
 *
 */
final class ReverseStringTest {

	def rev
	private static final def STRINGS = ['', 'a', 'ab', 'abc', 'abcd']
	private static final def REVERSE = ['', 'a', 'ba', 'cba', 'dcba']

	@Before
	public final void setUp() throws Exception {
		rev = new ReverseString()
	}

	@After
	public final void tearDown() throws Exception {
		rev = null
	}

	@Test
	public final void testReverseIterative() {
		assertEquals(REVERSE, STRINGS.collect { rev.reverseIterative(it) })
	}

	@Test
	public final void testReverseRecursive() {
		assertEquals(REVERSE, STRINGS.collect { rev.reverseRecursive(it) })
	}
}
