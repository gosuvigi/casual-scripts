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
final class PrimeNumbersTest extends GroovyLogTestCase {

	private static final def PRIME_NOS = [2, 3, 5, 7, 11, 101]
	private static final def NOT_PRIME_NOS = [
		4,
		6,
		8,
		9,
		10,
		21,
		100,
		100025
	]
	private static final def UPTO_30_PRIMES = [
		2,
		3,
		5,
		7,
		11,
		13,
		17,
		19,
		23,
		29
	]

	private def prime

	@Before
	public final void setUp() throws Exception {
		prime = new PrimeNumbers()
	}

	@After
	public final void tearDown() throws Exception {
		prime = null
	}

	@Test
	public final void testIsPrime() {
		assertTrue(PRIME_NOS.every { prime.isPrime(it) })
	}

	@Test
	public final void testIsNotPrime() {
		assertTrue(NOT_PRIME_NOS.every { !prime.isPrime(it) })
	}

	@Test
	public final void testPrintPrimes() {
		assertEquals(UPTO_30_PRIMES, prime.findPrimes(30))
	}
}
