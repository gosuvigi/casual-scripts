package ro.ratoi.virgiliu.tree

/**
 * 
 * @author Virgiliu
 * Sep 26, 2011
 *
 */
final class Entry {

	def value
	Entry left
	Entry right

		super();
		this.value = value;
	}

	final boolean isLeaf() {
		return left == null && right == null
	}
}