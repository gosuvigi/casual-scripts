package ro.ratoi.virgiliu.ruby.challenge.average.time;

import static org.junit.Assert.*

import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Oct 10, 2011
 *
 */
final class AverageTimeTest {

	private static final def TIMES = [
		[
			'11:51pm',
			'11:56pm',
			'12:01am',
			'12:06am',
			'12:11am'
		],
		['6:41pm', '6:51pm', '7:01pm'],
		[
			'10:01am',
			'11:01am',
			'12:01pm',
			'1:01pm'
		],
		[
			'12:06am',
			'12:01am',
			'11:51pm',
			'11:56pm',
			'12:11am'
		],
		[
			'4:20am',
			'2:20pm',
			'3:20pm',
			'6:40am'
		]
	]

	private static final def RESULTS = [
		'12:01am',
		'06:51pm',
		'11:31am',
		'12:01am',
		'10:10am'
	]


	@Test
	public final void testAverage() {
		assertEquals(RESULTS, TIMES.collect { AverageTime.average(it).toString() })
	}
}
