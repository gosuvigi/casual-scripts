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
		def inFile = new File(inputFile)
		if (!inFile.exists()) {
			throw new IllegalArgumentException('Please provide a valid existing file.')
		}
		def outFile = new File(inputFile + '.srt')
		if (outFile.exists()) {
			outFile.write('')
		}

		outFile.withWriter { out ->
			inFile.eachLine { line ->
				def sub = TimeFrame.shiftSubtitle(line, millis)
				if (sub) {
					out.writeLine(sub)
				} else {
					out.writeLine(line)
				}
			}
		}
	}
}



