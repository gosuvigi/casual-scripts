package ro.ratoi.virgiliu.string;

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
final class StringFirstOccurenceTest {

	def str
	private static final def HAYSTACKS = [
		'a':'a',
		'b':'a',
		'ab':'a',
		'abc':'ab',
		'abcd':'c',
		'abcdef':'b',
		'abcdefg':'abc',
		'abcdefgh':'def',
		'abcdefghi':'abcdefghi',
		'abcdfe':'abdcef',
		'c' : 'abc']

	private static final def NEEDLES = [
		0,
		-1,
		0,
		0,
		2,
		1,
		0,
		3,
		0,
		-1,
		-1
	]

	@Before
	public final void setUp() throws Exception {
		str = new StringFirstOccurence()
	}

	@After
	public final void tearDown() throws Exception {
		str = null
	}

	@Test
	public final void testStrStr() {
		def lst = HAYSTACKS.collect { key, value ->
			str.strStr(key, value)
		}
		assertEquals(NEEDLES, lst)
	}
}
