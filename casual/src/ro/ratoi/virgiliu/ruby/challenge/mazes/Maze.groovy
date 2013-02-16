package ro.ratoi.virgiliu.ruby.challenge.mazes

import java.awt.Point


/**
 * 
 * @author Virgiliu
 * Oct 13, 2011
 *
 */
final class Maze {

	private static final String START_CHAR = 'A'
	private static final String END_CHAR = 'B'
	private static final String WALL_CHAR = '#'
	private static final String VISITED_CHAR = 'V'
	private static final String FREE_CHAR = ' '

	/**
	 * A representation of the maze. Points are given by their horizontal
	 * and vertical coordinates.
	 * <p>
	 * map[0], map[1] etc are rows.
	 */
	private final def maze
	private final def visited
	private def width // 37
	private def height // 13

	private Maze(final String content) {
		this.maze = constructMaze(content)
		this.visited = new String[height][width]
	}

	private def constructMaze(final String content) {
		height = content.split('\n').size()
		width = (int) content.size() / height
		final def maze = new String[height][width]

		int row = 0
		content.eachLine { line ->
			line.eachWithIndex { li, idx ->
				maze[row][idx] = li
			}
			row++
		}
		return maze
	}

	/**
	 * Find the first location of the character denoted by c
	 * in the maze map.
	 * 
	 * @param c
	 * @return
	 */
	private Point findFirstLocation(final def c) {
		def start
		// traverse up -> down and then left -> right
		maze.eachWithIndex { row, v ->
			row.eachWithIndex { r, h ->
				if (c == r) {
					start = new Point(h, v)
				}
			}
		}
		return start
	}

	/**
	 * Returns true/false depending on whether it’s possible to navigate
	 * the maze from point A to point B.
	 * 
	 * @return
	 */
	final boolean isSolvable() {
		final int startH = (int) startLocation.x
		final int startV = (int) startLocation.y
		return exploreMaze(startH, startV)
	}

	private boolean exploreMaze(int h, int v) {
		if (h >= width || v >= height || h < 1 || v < 1) {
			return false
		}
		if (WALL_CHAR == maze[v][h] || VISITED_CHAR == maze[v][h]) {
			return false
		}
		if (END_CHAR == maze[v][h]) {
			return true
		}
		// mark
		maze[v][h] = VISITED_CHAR
		// down
		if (exploreMaze(h, v + 1)) {
			return true
		}
		// up
		if (exploreMaze(h, v - 1)) {
			return true
		}
		// left
		if (exploreMaze(h - 1, v)) {
			return true
		}
		// right
		if (exploreMaze(h + 1, v)) {
			return true
		}
		maze[v][h] = FREE_CHAR
		return false
	}

	/**
	 * Returns an integer of the least number of "steps" one would have to
	 * take within the maze to get from point A to point B.
	 * 
	 * @return
	 */
	final int steps() {
		return 0
	}

	/**
	 * Get the content of the maze as a multi line String.
	 * @return
	 */
	final def getContent() {
		def str = new StringBuilder(512)
		for (row in maze) {
			for (col in row) {
				str << col
			}
			str << '\n'
		}
		return str.toString()[0..str.size() - 2]
	}

	/**
	 * Get the start location of the maze as a Point with x,y coordinates.
	 * 
	 * @return
	 */
	final Point getStartLocation() {
		def st = findFirstLocation(START_CHAR)
		if (st == null) {
			throw new IllegalArgumentException('No start point found.')
		}
		return st
	}

	/**
	 * Get the end location of the maze as a Point with x,y coordinates.
	 * 
	 * @return
	 */
	final Point getEndLocation() {
		def end = findFirstLocation(END_CHAR)
		if (end == null) {
			throw new IllegalArgumentException('No end point found.')
		}
		return end
	}
}
