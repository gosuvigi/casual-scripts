package ro.ratoi.virgiliu.ruby.challenge.mazes;

import static org.junit.Assert.*

import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Oct 13, 2011
 *
 */
final class MazeFileReaderTest {

	private static final def MAZE2 = """#####################################
# #       #             #     #     #
# ### ### # ########### ### # ##### #
# #   # #   #   #   #   #   #       #
# # ###A##### # # # # ### ###########
#   #   #     #   # # #   #         #
####### # ### ####### # ### ####### #
#       # #   #       # #       #   #
# ####### # # # ####### # ##### # # #
#       # # # #   #       #   # # # #
# ##### # # ##### ######### # ### # #
#     #   #                 #     #B#
#####################################"""

	static final String FILE_PATH = 'test/ro/ratoi/virgiliu/ruby/challenge/mazes/mazes.txt'

	@Test
	public final void testFromFile() {
		def mazes = MazeFileReader.fromFile(FILE_PATH)
		assertEquals(MAZE2, mazes['MAZE2'].getContent())
	}
}
