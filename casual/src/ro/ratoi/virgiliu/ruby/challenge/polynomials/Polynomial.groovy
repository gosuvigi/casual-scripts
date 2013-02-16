package ro.ratoi.virgiliu.ruby.challenge.polynomials

/**
 * 
 * @author Virgiliu
 * Oct 13, 2011
 *
 */
final class Polynomial {

	private def coeffs

	Polynomial(final def coeffs) {
		if (coeffs.size() < 2) {
			throw new IllegalArgumentException('Need at least 2 coefficients.')
		}
		this.coeffs = coeffs
	}

	/**
	 * Pretty-prints polynomials, following some simple rules:
	 *  - if a coefficient is 1, it doesn’t get printed
	 *  - if a coefficient is negative, you have to display something 
	 *    like "- 2x^3", not "+ -2x^3"
	 *  - if a coefficient is 0, nothing gets added to the output
	 *  - for x^1 the ^1 part gets omitted
	 *  - x^0 == 1, so we don’t need to display it
	 * @return
	 * @throws If somebody tries to create a polynomial with less than
	 * 2 elements, an {@link IllegalArgumentException} with the message
	 * "Need at least 2 coefficients." is thrown.
	 */
	final String prettyPrint() {
		def gr = coeffs.size()
		def str = new StringBuilder(5 * gr)
		def allZero = true
		coeffs.eachWithIndex { coeff, idx ->
			def curr = gr - idx - 1
			if (coeff == 0 && curr > 0) {
				return
			}
			if (coeff != 0) {
				allZero = false
			}
			if (curr == 0) {
				if (coeff > 1) {
					str << '+' << coeff
				} else if (coeff == 0 && allZero == true) {
					str << '0'
				} else if (coeff != 0) {
					str << coeff
				}
			} else if (curr == 1) {
				if (coeff > 0) {
					str << '+' << coeff << 'x'
				} else if (coeff < 0) {
					str << coeff << 'x'
				}
			} else if (curr > 1) {
				if (coeff == 1 && curr != gr - 1) {
					str << '+'
				} else if (coeff == -1) {
					str << '-'
				} else if (coeff < 0) {
					str << coeff
				} else if (coeff > 1) {
					str << '+' << coeff
				}
				str << 'x^' << curr
			}
		}
		return str.toString()
	}
}
