package com.vigi.numbers;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Sep 27, 2011
 *
 */
final class PowerOfTwoTest {

	private static final def POWERS_OF_TWO = [
		2,
		4,
		8,
		16,
		32,
		64,
		128
	]

	private static final def NOT_POWERS_OF_TWO = [
		0,
		1,
		3,
		5,
		6,
		7,
		9,
		10,
		12
	]

	private def pow

	@Before
	public final void setUp() throws Exception {
		pow = new PowerOfTwo()
	}

	@After
	public final void tearDown() throws Exception {
		pow = null
	}

	@Test
	public final void testIsPowerOfTwo() {
		assertTrue(POWERS_OF_TWO.every { pow.isPowerOfTwo(it) })
	}

	@Test
	public final void testIsNotPowerOfTwo() {
		assertFalse(NOT_POWERS_OF_TWO.every { pow.isPowerOfTwo(it) })
	}

	@Test
	public final void testIsPowerOfTwoRecursive() {
		assertTrue(POWERS_OF_TWO.every { pow.isPowerOfTwoRecursive(it) })
	}
}
