package com.vigi.linkedlist;

import static org.junit.Assert.*
import static SingleLinkedList.SIZE_AT_LEAST_THREE_ERROR
import groovy.util.GroovyTestCase

import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Sep 23, 2011
 *
 */
final class LinkedListTest extends GroovyTestCase {

	private static def LISTS = [
		new SingleLinkedList(1..1),
		new SingleLinkedList(1..2),
		new SingleLinkedList(1..3),
		new SingleLinkedList(1..4),
		new SingleLinkedList(1..5),
		new SingleLinkedList(1..6),
		new SingleLinkedList(1..100)
	]
	private static def MIDDLES = [1, 1, 2, 2, 3, 3, 50]
	private static def THIRD_TO_LAST = [1, 2, 3, 4, 98]

	/**
	 * All the predecessors of the LISTS' tails
	 */
	private static def TAIL_PREDECESSORS = [
		null,
		1,
		2,
		3,
		4,
		5,
		99
	]

	private static REVERSED = [
		new SingleLinkedList(1..1),
		new SingleLinkedList(2..1),
		new SingleLinkedList(3..1),
		new SingleLinkedList(4..1),
		new SingleLinkedList(5..1),
		new SingleLinkedList(6..1),
		new SingleLinkedList(100..1)
	]

	@Test
	public final void testFindMiddle() {
		assertEquals(MIDDLES, LISTS.collect { it.findMiddle().value })
	}

	@Test
	/**
	 * Tests that an IllegalArgumentException is thrown when the size of 
	 * the list is less than 3 and tests for correctness in the other cases.
	 */
	public final void testFindThirdElementFromLast() {
		def msg = shouldFail(IllegalArgumentException) {
			LISTS[0..1].each { it.findThirdElementFromLast() } }
		assertEquals(SIZE_AT_LEAST_THREE_ERROR, msg)
		assertEquals(THIRD_TO_LAST, LISTS[2..6].collect { it.findThirdElementFromLast().value })
	}

	@Test
	public final void testHasLoops() {
		SingleLinkedList list = new SingleLinkedList(1..2)
		Entry third = new Entry(3)
		list.appendElem(third)
		list.appendElem(4)
		Entry fifth = new Entry(5)
		list.appendElem(fifth)
		fifth.nextNode = third
		assertTrue(list.hasLoops())
	}

	@Test
	public final void testHasNoLoops() {
		assertFalse(LISTS.every { it.hasLoops() })
	}

	@Test
	public final void testPredecessor() {
		def preds = []
		LISTS.each {  list ->
			list.each {
				def pred = list.getPredecessor(it.getTail(), it.getHead())
				preds.add(pred != null ? pred.getValue() : null)
			}
			return preds
		}
		assertEquals(TAIL_PREDECESSORS, preds)
	}

	@Test
	public final void testReverseList() {
		def rev = LISTS.collect { it.reverseList() }
		def s2 = REVERSED.collect { it.toString() }
		def s1 = rev.collect { it.toString() }

		assertEquals(s1, s2)
	}

	@Test
	public final void testDelete() {
		//		fail("Not yet implemented"); // TODO
	}
}
