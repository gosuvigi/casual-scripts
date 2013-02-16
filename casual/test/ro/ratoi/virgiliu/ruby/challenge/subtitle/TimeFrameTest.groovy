package ro.ratoi.virgiliu.ruby.challenge.subtitle;

import static org.junit.Assert.*
import groovy.util.GroovyTestCase

import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Oct 6, 2011
 *
 */
final class TimeFrameTest extends GroovyTestCase {

	private static final def SAMPLE = '00:02:46,399'
	private static final long MILLIS = 166399L

	@Test
	public final void testValueOf() {
		TimeFrame f = TimeFrame.valueOf(MILLIS)
		assertEquals(MILLIS, f.totalMillis)
		assertEquals(SAMPLE, f.toString())
		[-1L, -3L, -4L].each { er ->
			shouldFail(IllegalArgumentException) { TimeFrame.valueOf(er) }
		}
	}

	@Test
	public final void testExtractSingleTimeFrame() {
		TimeFrame f = TimeFrame.extractSingleTimeFrame(SAMPLE)
		assertEquals(MILLIS, f.totalMillis)
		assertEquals(SAMPLE, f.toString())
	}

	@Test
	public final void testAddMillis() {
		TimeFrame f = TimeFrame.valueOf(MILLIS).addMillis(602)
		assertEquals(MILLIS + 602, f.totalMillis)
		assertEquals('00:02:47,001', f.toString())
		[-170000L, -250003L].each { er ->
			shouldFail(IllegalArgumentException) { f.addMillis(er) }
		}
	}

	@Test
	public final void testSubMillis() {
		TimeFrame f = TimeFrame.valueOf(MILLIS).subMillis(666)
		assertEquals(MILLIS - 666, f.totalMillis)
		assertEquals('00:02:45,733', f.toString())
		[170000L, 250003L].each { er ->
			shouldFail(IllegalArgumentException) { f.subMillis(er) }
		}
	}

	@Test
	public final void testToString() {
		TimeFrame f = TimeFrame.valueOf(MILLIS)
		assertEquals(SAMPLE, f.toString())
		f = TimeFrame.valueOf(0L)
		assertEquals('00:00:00,000', f.toString())
	}

	@Test
	public final void testShiftSubtitle() {
		assertEquals('00:02:47,043 --> 00:02:49,238',
				TimeFrame.shiftSubtitle('00:02:46,399 --> 00:02:48,594', 644L))
		assertEquals('00:02:45,854 --> 00:02:48,049',
				TimeFrame.shiftSubtitle('00:02:46,399 --> 00:02:48,594', -545L))
	}
}
