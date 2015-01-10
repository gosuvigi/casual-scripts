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
final class SecondHighestNumberTest {

	private def sec

	private static final NUMBERS = [
		[1, -7],
		[7, 8, 9],
		[1, -4, -4, 2],
		[4, 6, -1, 0, 3, 1],
		[5, 7, -4, -3, 0],
		[6, 6, 6],
		[1, 2, 3, 4, 5, 6]
	]

	private static final SECOND_HIGH = [-7, 8, 1, 4, 5, 6, 5]

	@Before
	public final void setUp() throws Exception {
		sec = new SecondHighestNumber()
	}

	@After
	public final void tearDown() throws Exception {
		sec = null
	}

	@Test
	public final void testSecondHighest() {
		assertEquals(SECOND_HIGH, NUMBERS.collect { sec.secondHighest(it) })
	}
}
