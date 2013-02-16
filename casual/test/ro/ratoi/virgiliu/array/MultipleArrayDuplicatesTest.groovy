package ro.ratoi.virgiliu.array;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

import ro.ratoi.virgiliu.array.MultipleArrayDuplicates

/**
 * 
 * @author Virgiliu
 * Sep 22, 2011
 *
 */
final class MultipleArrayDuplicatesTest extends GroovyTestCase {

	private def dup

	private static final def ARRAYS = [
		[1, 1],
		[1, 1, 3, 7, 7],
		[1, 2, 2, 3, 3, 3, 4, 4, 5],
		[3, 3, 3, 3, 3, 3, 3],
		[1, 6, 3, 6, 1, 3, 6]
	]

	private static final def DUPS = [
		[1],
		[1, 7],
		[2, 3, 4],
		[3],
		[1, 3, 6]
	]

	@Before
	public final void setUp() throws Exception {
		dup = new MultipleArrayDuplicates()
	}

	@After
	public final void tearDown() throws Exception {
		dup = null
	}

	@Test
	public final void testMultipleDuplicate() {
		def dups = ARRAYS.collect { dup.multipleDuplicate(it) }
		assertToString(dups, DUPS.toString())
	}
}
