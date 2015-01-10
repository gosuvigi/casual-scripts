package com.vigi.ruby.challenge.subtitle

import com.vigi.util.Benchmark

/**
 * 
 * @author Virgiliu
 * Oct 10, 2011
 *
 */
final class TweakSubtitles {

	static final void shiftSubtitles(final String inputFile, long millis) {
		def time = Benchmark.benchmark({shift(inputFile, millis)})
		println('It took: ' + time + ' millis to perform.')
	}

	private static void shift(final String inputFile, long millis) {
		performOperationOnFile(inputFile) { TimeFrame.shiftSubtitle(it, millis) }
	}

	private static void performOperationOnFile(String inputFile, Closure operation) {
		def inFile = new File(inputFile)
		if (!inFile.exists()) {
			throw new IllegalArgumentException('Please provide a valid existing file.')
		}
		def outFile = new File(inputFile + "_new.srt")
		if (outFile.exists()) {
			outFile.write('')
		}

		outFile.withWriter { out ->
			inFile.eachLine { line ->
				def containsTimeFrame = operation.call(line)
				if (containsTimeFrame) {
					out.writeLine(containsTimeFrame)
				} else {
					out.writeLine(line)
				}
			}
		}
	}

	static void convert23FpsTo25Fps(String inputFile) {
		performOperationOnFile(inputFile) { TimeFrame.convert23FpsTo25FpsSubtitle(it) }
	}

	static void convert25FpsTo23Fps(String inputFile) {
		performOperationOnFile(inputFile) { TimeFrame.convert25FpsTo23FpsSubtitle(it) }
	}
}



