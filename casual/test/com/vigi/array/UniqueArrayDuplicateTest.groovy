package com.vigi.array;

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
final class UniqueArrayDuplicateTest {

	private def dup

	private static final def ARRAYS = [
		[1, 1],
		[1, 1, 2],
		[1, 2, 3, 4, 4, 5],
		[1, 2, 2, 3, 4, 5, 6],
		[1, 2, 3, 3, 4, 5, 6, 7, 8, 9]
	]

	private static final def DUPS = [1, 1, 4, 2, 3]

	@Before
	public final void setUp() throws Exception {
		dup = new UniqueArrayDuplicate()
	}

	@After
	public final void tearDown() throws Exception {
		dup = null
	}

	@Test
	public final void testUniqueDuplicate() {
		assertEquals(DUPS, ARRAYS.collect { dup.uniqueDuplicate(it) })
	}
}
