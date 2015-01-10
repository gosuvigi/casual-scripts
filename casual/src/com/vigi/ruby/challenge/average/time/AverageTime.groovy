package com.vigi.ruby.challenge.average.time

/**
 * 
 * @author Virgiliu
 * Oct 10, 2011
 *
 */
final class AverageTime {

	/**
	 * Computes the average time but assumes that the times are within
	 * maximum 12 hours between each other.
	 * 
	 * @param timez
	 * @return
	 */
	final static Time average(final def arrivals) {
		final def timez = arrivals.collect { Time.valueOf(it) }
		final def min = timez.min()
		final def max = timez.max()
		int mins = timez.collect { it.totalMinutes }.sum()
		final def halfDay = 720
		final int oneDay = 1440
		for (curr in timez) {
			if (Math.abs(curr.totalMinutes - min.totalMinutes) > halfDay) {
				if (curr < min) {
					mins += oneDay
				} else if (curr > min) {
					mins -= oneDay
				}
			}
		}
		mins = mins / timez.size()
		int h = mins / 60
		final int m = mins - h * 60
		if (h >= 24) {
			h -= 24
		}
		return new Time(h, m)
	}
}
