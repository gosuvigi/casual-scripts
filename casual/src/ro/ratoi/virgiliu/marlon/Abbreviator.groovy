package ro.ratoi.virgiliu.marlon

/**
 * 
 * @author virgiliu
 *
 */
final class Abbreviator implements IAbbreviatorStrategy {

	public static final String VOWELS = '[aeiou]'

	private static final String ALPHA_NUMERIC = '[^A-Za-z0-9 _]'

	final String abbreviate(final String str, int maxLength) {
		if (str.size() <= maxLength) {
			return str
		}

		final def onlyAlphanumeric = str.replaceAll(ALPHA_NUMERIC, '')
		if (onlyAlphanumeric.size() <= maxLength) {
			return onlyAlphanumeric
		}

		final def words = onlyAlphanumeric.split(' ')
		final def noOfWords = words.size()

		if (noOfWords == 1) {
			return abbreviateSingleWord(words[0], maxLength)
		}

		final def withoutWhitespaces = words.join('')
		if (withoutWhitespaces.size() <= maxLength) {
			return withoutWhitespaces
		}

		if (noOfWords > 3) {
			return abbreviateLargeWord(onlyAlphanumeric, maxLength)
		}

		def fromSubWords = abbreviateSubWords(words, maxLength)
		if (fromSubWords.size() > maxLength) {
			return fromSubWords.substring(0, maxLength - 1)
		}
		return fromSubWords
	}

	private String abbreviateSingleWord(final String word, final int maxLength) {
		final String withoutVowels = removeVowels(word)
		if (withoutVowels.size() <= maxLength) {
			return withoutVowels
		}

		return withoutVowels.substring(0, maxLength - 1)
	}

	private String abbreviateLargeWord(final String str, final int maxLength) {
		def withoutWhiteSpace = str.replaceAll('\\s', '')
		if (withoutWhiteSpace.size() <= maxLength) {
			return withoutWhiteSpace
		}

		final String withoutVowels = removeVowels(withoutWhiteSpace)
		if (withoutVowels.size() <= maxLength) {
			return withoutVowels
		}

		return abbreviate(str.split(' ')[0..2].join(''), maxLength)
	}

	private String abbreviateSubWords(final def words, final int maxLength) {
		final int noOfWords = words.size()
		words.collect { abbreviate(it, maxLength / noOfWords as int) }.join('')
	}

	private String removeVowels(final String str) {
		return str.replaceAll(VOWELS, '')
	}
}