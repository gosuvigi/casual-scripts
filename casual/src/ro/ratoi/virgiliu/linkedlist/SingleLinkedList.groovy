package ro.ratoi.virgiliu.linkedlist

/**
 * 
 * @author Virgiliu
 * Sep 23, 2011
 *
 */
final class SingleLinkedList {

	def static final SIZE_AT_LEAST_THREE_ERROR = 'Size must be at least 3.'
	private Entry head
	private Entry tail // for appending and other purposes
	private def listSize = 0

	SingleLinkedList(final Range range) {
		for (i in range) {
			appendElem(i)
		}
	}

	SingleLinkedList() {
	}

	private boolean isEmpty() {
		return head == null;
	}

	/**
	 * Finds the middle element of a linked list in a single pass.
	 * 
	 * @return
	 */
	final Entry findMiddle() {
		Entry oneStep = head // one step iteration
		Entry twoSteps = head // two steps iteration
		while (twoSteps != tail) {
			twoSteps = twoSteps.nextNode
			if (twoSteps.nextNode != null) {
				twoSteps = twoSteps.nextNode
				oneStep = oneStep.nextNode
			}
		}

		return oneStep
	}

	final def size() {
		return listSize
	}

	/**
	 * Finds the 3rd element from last in a single pass.
	 * 
	 * @return
	 */
	final Entry findThirdElementFromLast() {
		if (listSize < 3) {
			throw new IllegalArgumentException(SIZE_AT_LEAST_THREE_ERROR)
		}
		Entry first = head
		Entry third = head
		first = first.nextNode.nextNode
		while (first != tail) {
			first = first.nextNode
			third = third.nextNode
		}
		return third
	}

	/**
	 * Finds if there is any loop in a singly linked list.
	 * 
	 * @return
	 */
	final boolean hasLoops() {
		Entry oneStep = head // one step iteration
		Entry twoSteps = head // two steps iteration
		while (twoSteps.nextNode != null) {
			twoSteps = twoSteps.nextNode
			if (twoSteps.nextNode != null) {
				twoSteps = twoSteps.nextNode
				oneStep = oneStep.nextNode
			}
			if (oneStep == twoSteps) {
				return true
			}
		}

		return false
	}

	/**
	 * Reverses a list.
	 * 
	 * @return
	 */
	final reverseList() {
		if (listSize <= 1) {
			return this
		}
		def rev = []
		Entry curr = tail
		while (curr != null) {
			rev.add(curr)
			def pred = getPredecessor(curr, head)
			curr = pred
		}
		return rev
	}

	final Entry getPredecessor(final def node, final def start) {
		if (listSize <= 1 || node == start) {
			return null
		}
		Entry curr = start
		while (curr != null) {
			if (curr.nextNode != null && curr.nextNode == node) {
				return curr
			}
			curr = curr.nextNode
		}
		return null
	}

	@Override
	public final String toString() {
		Entry curr = head
		StringBuilder buff = new StringBuilder(100)
		buff<< '['
		while (curr != null) {
			buff << curr.value << ' , '
			curr = curr.nextNode
		}
		buff.delete(buff.length() - 3, buff.length() - 1)
		buff << ']'
		return buff.toString()
	}

	final boolean appendElem(def value) {
		Entry added = new Entry(value)
		appendElem(added)
	}

	final boolean appendElem(final Entry entry) {
		listSize++
		if (isEmpty()) {
			head = entry
			tail = head
			return true
		}
		tail.setNextNode(entry)
		tail = entry
	}

	/**
	 * Deletes a node from the list.
	 * 
	 * @param node
	 */
	final boolean delete(def node) {
		return false
	}

	final Entry getHead() {
		return head
	}

	final Entry getTail() {
		return tail
	}
}
