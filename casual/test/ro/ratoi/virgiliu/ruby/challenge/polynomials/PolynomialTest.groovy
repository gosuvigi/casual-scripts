package ro.ratoi.virgiliu.ruby.challenge.polynomials;

import static org.junit.Assert.*

import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Oct 13, 2011
 *
 */
final class PolynomialTest extends GroovyTestCase {

	private static final def POLYS = [
		[-3, -4, 1, 0, 6],
		[1, 0 , 2],
		[-1, -2, 3, 0],
		[0, 0, 1],
		[0, -2],
		[0, 0, 0, 0, 0, 0]
	]
	private static final def RESULTS = [
		'-3x^4-4x^3+x^2+6',
		'x^2+2',
		'-x^3-2x^2+3x',
		'1',
		'-2',
		'0'
	]
	private def poly

	@Test
	public final void testPrettyPrint() {
		assertEquals(RESULTS, POLYS.collect {
			poly = new Polynomial(it)
			poly.prettyPrint()
		})
		[[-1], [0], [6], []].each { coeffs ->
			shouldFail(IllegalArgumentException) {
				poly = new Polynomial(coeffs)
			}
		}
	}
}
