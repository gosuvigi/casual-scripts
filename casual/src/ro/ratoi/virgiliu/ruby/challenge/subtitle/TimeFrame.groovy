package ro.ratoi.virgiliu.ruby.challenge.subtitle


/**
 * 
 * @author Virgiliu
 * Oct 6, 2011
 *
 */
final class TimeFrame {

	/**
	 * Time frame pattern that captures a string like 00:03:21,501
	 */
	static final String TIME = /(\d{2}):(\d{2}):(\d{2}),(\d{3})/
	static final int MILLIS_IN_HOUR = 3600000
	static final int MILLIS_IN_MINUTE = 60000
	static final int MILLIS_IN_SECOND = 1000
	static final int FPS_24 = 23976
	static final int FPS_25 = 25000

	final int hh
	final int mm
	final int ss
	final int millis
	final long totalMillis
	TimeFrame(int hh, int mm, int ss, int millis) {
		super();
		this.hh = hh;
		this.mm = mm;
		this.ss = ss;
		this.millis = millis;
		totalMillis = millis + ss * MILLIS_IN_SECOND + mm * MILLIS_IN_MINUTE + hh * MILLIS_IN_HOUR
	}

	@Override
	final String toString() {
		return String.format(
		"%s:%s:%s,%s", hh.toString().padLeft(2, '0'), mm.toString().padLeft(2, '0'),
		ss.toString().padLeft(2, '0'), millis.toString().padLeft(3, '0'));
	}

	/**
	 * Constructs a new TimeFrame object with the number of initial millis.
	 * 
	 * @param millis
	 * @return
	 */
	static final TimeFrame valueOf(final long millis) {
		if (millis < 0) {
			throw new IllegalArgumentException('Value must be positive.')
		}
		int hh = millis / MILLIS_IN_HOUR
		long dif1 = millis - hh * MILLIS_IN_HOUR
		int mm = dif1 / MILLIS_IN_MINUTE
		long dif2 = dif1 - mm * MILLIS_IN_MINUTE
		int ss = dif2 / MILLIS_IN_SECOND
		int milli = dif2 - ss * MILLIS_IN_SECOND
		return new TimeFrame(hh, mm, ss, milli)
	}

	/**
	 * Constructs from a string of type 00:03:21,501 a new TimeFrame object.
	 * 
	 * @param s
	 * @return
	 */
	static final TimeFrame valueOf(final String s) {
		def matcher = (s =~ TIME)
		if (matcher.matches()) {
			def hh = matcher[0][1].toInteger()
			def mm = matcher[0][2].toInteger()
			def ss = matcher[0][3].toInteger()
			def milli = matcher[0][4].toInteger()
			return new TimeFrame(hh, mm, ss, milli)
		}
		return this
	}

	/**
	 * Add the given number of milliseconds to the current TimeFrame object
	 * and return a new TimeFrame with the appropriate values.
	 * 
	 * @param millis
	 * @return
	 */
	final TimeFrame addMillis(final long millis) {
		if (millis + totalMillis < 0) {
			throw new IllegalArgumentException(String.format('Cannot subtract more than %s millis.', totalMillis))
		}
		return valueOf(millis + totalMillis)
	}

	/**
	 * Subtract the given number of milliseconds to the current TimeFrame 
	 * Object and return a new TimeFrame with the appropriate values.
	 *
	 * @param millis
	 * @return
	 */
	final TimeFrame subMillis(final long millis) {
		return addMillis(-millis)
	}

	/**
	 * Shifts a given subtitle with the number of millis.
	 * <p>
	 * A subtitle must look like <b>00:02:45,854 --> 00:02:48,049</b>
	 * otherwise <b>null</b> will be returned.
	 * 
	 * @param subtitle
	 * @param millis
	 * @return The shifted subtitle or <b>null</b> if the given subtitle is not
	 * a genuine subtitle in the expected format.
	 */
	final static shiftSubtitle(final String subtitle, final long millis) {
		def matcher = (subtitle =~ /($TIME) --> ($TIME)/)
		if (matcher.matches()) {
			def m1 = matcher[0][1]
			def m2 = matcher[0][6]
			def tf1 = valueOf(m1)
			def tf2 = valueOf(m2)
			def add1 = tf1.addMillis(millis)
			def add2 = tf2.addMillis(millis)
			return add1.toString() + ' --> ' + add2.toString()
		}
		return null
	}

	final TimeFrame convert24FpsTo25Fps() {
		convertFromInputFpsToOutputFps(FPS_24, FPS_25)
	}

	final TimeFrame convert25FpsTo24Fps() {
		convertFromInputFpsToOutputFps(FPS_25, FPS_24)
	}

	final TimeFrame convertFromInputFpsToOutputFps(int inputFps, int outputFps) {
		int resultMillis = totalMillis * inputFps / outputFps
		return TimeFrame.valueOf(resultMillis)
	}
}
