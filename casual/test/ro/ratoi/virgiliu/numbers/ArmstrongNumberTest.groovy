package ro.ratoi.virgiliu.numbers;

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
final class ArmstrongNumberTest {

	private static final def ARMSTRONG_NUMBERS = [153, 370, 371, 407]
	private static final def NOT_ARMSTRONG_NUMBERS = [1, 2, 500, 1000, 10000]

	private def arm

	@Before
	public final void setUp() throws Exception {
		arm = new ArmstrongNumber()
	}

	@After
	public final void tearDown() throws Exception {
		arm = null
	}

	@Test
	public final void testIsArmstrongNumber() {
		assertTrue(ARMSTRONG_NUMBERS.every { arm.isArmstrongNumber(it) })
	}

	@Test
	public final void testIsNotArmstrongNumber() {
		assertFalse(NOT_ARMSTRONG_NUMBERS.every { arm.isArmstrongNumber(it) })
	}
}
