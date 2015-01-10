package com.vigi.ruby.challenge.subtitle

import org.junit.Assert;

import static org.junit.Assert.*

import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Oct 6, 2011
 *
 */
final class TimeFrameTest {

	private static final def SAMPLE = '00:02:46,399'
	private static final long MILLIS = 166399L

	@Test(expected = IllegalArgumentException)
	void valueOfShouldNotAcceptNegativeValues() {
		TimeFrame f = TimeFrame.valueOf(MILLIS)
		Assert.assertEquals(MILLIS, f.totalMillis)
		assertEquals(SAMPLE, f.toString())
		[-1L, -3L, -4L].each { er ->
			TimeFrame.valueOf(er)
		}
	}

	@Test
	void valueOfString() {
		TimeFrame f = TimeFrame.valueOf(SAMPLE)
		Assert.assertEquals(MILLIS, f.totalMillis)
		assertEquals(SAMPLE, f.toString())
	}

	@Test(expected = IllegalArgumentException)
	void testAddMillis() {
		TimeFrame f = TimeFrame.valueOf(MILLIS).addMillis(602)
		Assert.assertEquals(MILLIS + 602, f.totalMillis)
		assertEquals('00:02:47,001', f.toString())
		[-170000L, -250003L].each { er ->
			f.addMillis(er)
		}
	}

	@Test
	void testShouldIncrementHours() {
		TimeFrame f = new TimeFrame(0, 59, 59, 900).addMillis(500)
		assertEquals('01:00:00,400', f.toString())
	}

	@Test(expected = IllegalArgumentException)
	void testSubMillis() {
		TimeFrame f = TimeFrame.valueOf(MILLIS).subMillis(666)
		Assert.assertEquals(MILLIS - 666, f.totalMillis)
		assertEquals('00:02:45,733', f.toString())
		[170000L, 250003L].each { er ->
			f.subMillis(er)
		}
	}

	@Test
	void testShouldDecrementHours() {
		TimeFrame f = TimeFrame.valueOf("01:00:00,300").subMillis(500)
		assertEquals('00:59:59,800', f.toString())
	}

	@Test
	void testToString() {
		TimeFrame f = TimeFrame.valueOf(MILLIS)
		assertEquals(SAMPLE, f.toString())
		f = TimeFrame.valueOf(0L)
		assertEquals('00:00:00,000', f.toString())
	}

	@Test
	void testShiftSubtitle() {
		Assert.assertEquals('00:02:47,043 --> 00:02:49,238',
				TimeFrame.shiftSubtitle('00:02:46,399 --> 00:02:48,594', 644L))
		Assert.assertEquals('00:02:45,854 --> 00:02:48,049',
				TimeFrame.shiftSubtitle('00:02:46,399 --> 00:02:48,594', -545L))
	}

	@Test
	void shouldConvertFrom24FpsTo25Fps() {
		TimeFrame f1 = TimeFrame.valueOf("00:01:36,343")
		assert "00:01:32,396" == f1.convert24FpsTo25Fps().toString()
	}

	@Test
	void shouldConvertFrom25FpsTo24Fps() {
		TimeFrame f1 = TimeFrame.valueOf("00:53:56,160")
		assert "00:56:14,374" == f1.convert25FpsTo24Fps().toString()
	}

	@Test
	void valueOfAsString() {
		TimeFrame f1 = TimeFrame.valueOf("00:56:13,669")
		assert "00:56:13,669" == f1.toString()
	}

	@Test
	public void shouldConvertFromInputFpsToOutputFps() {
		TimeFrame f1 = TimeFrame.valueOf("00:01:36,343")
		assert "00:01:32,396" == f1.convertFromInputFpsToOutputFps(23976, 25000).toString()
	}
}
