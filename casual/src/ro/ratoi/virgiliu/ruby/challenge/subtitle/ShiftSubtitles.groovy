package ro.ratoi.virgiliu.ruby.challenge.subtitle

import ro.ratoi.virgiliu.util.Benchmark

/**
 * 
 * @author Virgiliu
 * Oct 10, 2011
 *
 */
final class ShiftSubtitles {

	static final void shiftSubtitles(final String inputFile, long millis) {
		def time = Benchmark.benchmark({shift(inputFile, millis)})
		println('It took: ' + time + ' millis to perform.')
	}

	private static void shift(final String inputFile, long millis) {
		//		def inFile = new File(inputFile)
		//		if (!inFile.exists()) {
		//			throw new IllegalArgumentException('Please provide a valid existing file.')
		//		}
		//		def outFile = new File(inputFile + '_new.srt')
		//		if (outFile.exists()) {
		//			outFile.write('')
		//		}
		//
		//		outFile.withWriter { out ->
		//			inFile.eachLine { line ->
		//				def containsTimeFrame = TimeFrame.shiftSubtitle(line, millis)
		//				if (containsTimeFrame) {
		//					out.writeLine(containsTimeFrame)
		//				} else {
		//					out.writeLine(line)
		//				}
		//			}
		//		}
		performOperationOnFile(inputFile) { TimeFrame.shiftSubtitle(it, millis) }
	}

	private static void performOperationOnFile(String inputFile, Closure operation) {
		def inFile = new File(inputFile)
		if (!inFile.exists()) {
			throw new IllegalArgumentException('Please provide a valid existing file.')
		}
		def outFile = new File(inputFile + '_new.srt')
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
	}
}



