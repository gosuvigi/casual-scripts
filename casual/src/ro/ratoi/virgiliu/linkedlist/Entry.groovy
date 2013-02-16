package ro.ratoi.virgiliu.linkedlist

/**
 * 
 * @author Virgiliu
 * Sep 23, 2011
 *
 */
final class Entry {

	private def value
	Entry nextNode

	@Override
	public final String toString() {
		return String.format("%s ", value);
	}

	Entry(final def value) {
		super();
		this.value = value;
	}

	final getValue() {
		return value
	}
}
