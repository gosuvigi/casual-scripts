package ro.ratoi.virgiliu.ruby.challenge.mazes

import java.util.Map

/**
 * 
 * @author Virgiliu
 * Oct 13, 2011
 *
 */
final class MazeFileReader {

	private static final def MAZE_NAME = /^(MAZE\d+) = %\{$/
	private static final def MAZE_CONTENT = /^#.*#$/
	private static final def MAZE_END = /^\}$/

	/**
	 * Reads mazes from a file and stores them into a {@link Map} whose
	 * keys are the maze number (i.e. MAZE1, MAZE2 etc) and whose values
	 * are the corresponding maze content.
	 *
	 * @param fileName
	 * @return
	 */
	static final Map fromFile(final def fileName) {
		def mazes = [:]
		def file = new File(fileName)
		def str
		def key

		file.eachLine { line ->
			def match1 = (line =~ MAZE_NAME)
			if (match1.matches()) {
				str = new StringBuilder(512)
				key = match1[0][1]
			} else if (line.matches(MAZE_CONTENT)) {
				str << line << '\n'
			} else if (line.matches(MAZE_END)) {
				def cont = str.toString()[0..str.size() - 2]
				mazes[key] = new Maze(cont)
			}
		}
		return mazes
	}
}
