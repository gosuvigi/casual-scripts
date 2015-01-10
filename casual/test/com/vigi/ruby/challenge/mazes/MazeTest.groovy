package com.vigi.ruby.challenge.mazes

import static org.junit.Assert.*

import java.awt.Point

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Oct 13, 2011
 *
 */
final class MazeTest extends GroovyTestCase {

	private static final def MAZES = [
		'MAZE1',
		'MAZE2',
		'MAZE3',
		'MAZE4',
		'MAZE5',
		'MAZE6',
		'MAZE7'
	]

	private static final def SOLVABLE = [
		true,
		true,
		false,
		false,
		true,
		true,
		true
	]

	private static final def STEPS = [
		44,
		75,
		0,
		0,
		5,
		15,
		15
	]

	private static final def STARTS = [
		new Point(13, 1),
		new Point(7, 4),
		new Point(15, 5),
		new Point(5, 1),
		new Point(1, 1),
		new Point(1, 1),
		new Point(1, 1)
	]

	private static final def ENDS = [
		new Point(23, 7),
		new Point(35, 11),
		new Point(17, 9),
		new Point(3, 3),
		new Point(6, 1),
		new Point(1, 3),
		new Point(1, 3)
	]

	private def mazes = [:]

	@Before
	public final void setUp() throws Exception {
		mazes = MazeFileReader.fromFile(MazeFileReaderTest.FILE_PATH)
	}

	@After
	public final void tearDown() throws Exception {
		mazes = null
	}

	@Test
	public final void testIsSolvable() {
		assertEquals(SOLVABLE, MAZES.collect {
			return mazes[it].isSolvable()
		})
	}

	@Test
	public final void testSteps() {
		//		assertEquals(STEPS, MAZES.collect {
		//			return mazes[it].steps()
		//		})
	}

	@Test
	public final void testGetStartLocation() {
		assertEquals(STARTS, MAZES.collect {
			mazes[it].getStartLocation()
		})
		shouldFail(IllegalArgumentException) {
			mazes['MAZE8'].getStartLocation()
		}
	}

	@Test
	public final void testGetEndLocation() {
		assertEquals(ENDS, MAZES.collect {
			mazes[it].getEndLocation()
		})
		shouldFail(IllegalArgumentException) {
			mazes['MAZE9'].getEndLocation()
		}
	}
}
