package ro.ratoi.virgiliu.string;

import static org.junit.Assert.*

import java.util.logging.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Sep 19, 2011
 *
 */
final class StringPermutationsTest extends GroovyLogTestCase {

	StringPermutations perm

	static final ABC = 'abc'
	static final ABC_PERM = [
		'abc',
		'acb',
		'bac',
		'bca',
		'cab',
		'cba'
	]

	@Before
	public final void setUp() throws Exception {
		perm = new StringPermutations()
	}

	@After
	public final void tearDown() throws Exception {
		perm = null
	}

	@Test
	public final void testRecursivePermutations() {
		def log = stringLog(Level.FINER, 'StringPermutations') { perm.printRecursivePermutations(ABC) }
		ABC_PERM.each {
			def matcher = (log =~ it)
			assertTrue log, 1 == matcher.count
		}
	}

	@Test
	public final void testIterativePermutations() {
		perm.printIterativePermutations(ABC)
	}
}
