package com.vigi.ruby.challenge.average.time;

import static org.junit.Assert.*

import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Oct 10, 2011
 *
 */
final class TimeTest extends GroovyTestCase {

	private static final def TIMES = [
		'02:33pm',
		'04:55am',
		'11:51pm',
		'11:56pm',
		'12:01am',
		'12:06am',
		'12:11am'
	]

	private static final def TOTAL_MINUTES = [
		873,
		295,
		1431,
		1436,
		1,
		6,
		11
	]

	private static final def FAIL_TIMES = [
		'-2:51pm',
		'13:55pm',
		'25:56pm',
		'12:-3am',
		'12:61am',
		'12:11dd',
	]

	@Test
	public final void testValueOf() {
		assertEquals(TIMES, TIMES.collect { Time.valueOf(it).toString() })
		FAIL_TIMES.each { f->
			shouldFail(IllegalArgumentException) { Time.valueOf(f)
			} }
	}

	@Test
	public final void testTotalMinutes() {
		assertEquals(TOTAL_MINUTES, TIMES.collect { Time.valueOf(it).getTotalMinutes() })
	}

	@Test
	public final void testToString() {
		final Time t1 = new Time(0, 45)
		assertEquals('12:45am', t1.toString())
		final Time t2 = new Time(12, 45)
		assertEquals('12:45pm', t2.toString())
		final Time t3 = new Time(14, 43)
		assertEquals('02:43pm', t3.toString())
		final Time t4 = new Time(7, 5)
		assertEquals('07:05am', t4.toString())
		shouldFail(IllegalArgumentException) {  new Time (24, 12) }
		shouldFail(IllegalArgumentException) {  new Time (-1, 12) }
		shouldFail(IllegalArgumentException) {  new Time (12, 67) }
		shouldFail(IllegalArgumentException) {  new Time (12, -3) }
	}

	@Test
	public final void testCompareTo() {
		final Time t1 = new Time(2, 45)
		final Time t2 = new Time(3, 45)
		assertTrue(t1 < t2)
		final Time t3 = new Time(2, 45)
		final Time t4 = new Time(2, 46)
		assertTrue(t3 < t4)
		final Time t5 = new Time(2, 45)
		final Time t6 = new Time(14, 32)
		assertTrue(t5 < t6)
	}
}
