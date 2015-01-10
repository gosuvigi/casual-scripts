package com.vigi.ruby.challenge.average.time

/**
 * 
 * @author Virgiliu
 * Oct 10, 2011
 *
 */
final class Time implements Comparable<Time> {

	static final def REG = /(\d{1,2}):(\d{2})(am|pm)/
	final int hh
	final int mm
	private final int totalMinutes

	Time(final int hh, final int mm) {
		this.hh = hh
		this.mm = mm
		if (hh < 0 || hh > 23) {
			throw new IllegalArgumentException('Hour must be between 0-23.')
		}
		if (mm < 0 || mm > 59) {
			throw new IllegalArgumentException('Minute must be between 0-59.')
		}
		totalMinutes = hh * 60 + mm
	}

	@Override
	public final int compareTo(final Time o) {
		if (o == null) {
			return -1
		}
		return totalMinutes - o.totalMinutes
	}

	/**
	 * Constructs a Time object from a String like '07:44pm'.
	 * 
	 * @param s
	 * @return
	 */
	static final Time valueOf(final String s) {
		def matcher = (s =~ REG)
		if (!matcher.matches()) {
			throw new IllegalArgumentException('Illegal time format for string: <<' + s + '>>.')
		}
		def h = matcher[0][1].toInteger()
		def m = matcher[0][2].toInteger()
		def ampm = matcher[0][3]
		if (12 == h) {
			h = ampm == 'am' ? 0 : 12
		} else if ('pm' == ampm) {
			h += 12
		}
		return new Time(h, m)
	}

	private static String pad(final int val) {
		return val.toString().padLeft(2, '0')
	}

	@Override
	public final String toString() {
		final def isPm = hh >= 12
		if (hh == 0) {
			return String.format('12:%sam', pad(mm))
		} else if (hh == 12) {
			return String.format('12:%spm', pad(mm))
		} else if (hh > 12) {
			return String.format('%s:%spm', pad(hh - 12), pad(mm))
		} else if (hh <12) {
			return String.format('%s:%sam', pad(hh), pad(mm))
		}
		return 'N/A'
	}

	final int getTotalMinutes() {
		return totalMinutes
	}
}
