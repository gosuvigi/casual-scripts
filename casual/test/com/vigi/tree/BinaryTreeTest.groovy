package com.vigi.tree

import org.junit.Assert;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 
 * @author Virgiliu
 * Sep 26, 2011
 *
 */
final class BinaryTreeTest extends GroovyTestCase {

	private BinaryTree tree
	private static final def PREORDER = '23, 14, 7, 9, 17, 31'
	private static final def POSTORDER = '9, 7, 17, 14, 31, 23'
	private static final def INORDER = '7, 9, 14, 17, 23, 31'
	private static final def BREADTH_FIRST = '23, 14, 31, 7, 17, 9'
	private static final def LEAFS = '9, 17, 31'

	@Before
	public final void setUp() throws Exception {
		tree = new BinaryTree(23)
		tree.addValue(14)
		tree.addValue(31)
		tree.addValue(7)
		tree.addValue(17)
		tree.addValue(9)
	}

	@After
	public final void tearDown() throws Exception {
		tree = null
	}

	@Test
	public final void testAddValue() {
		def t = new BinaryTree(23)
		assertEquals('23', t.preOrder())
		t.addValue(14)
		assertEquals('23, 14', t.preOrder())
		t.addValue(31)
		assertEquals('23, 14, 31', t.preOrder())
		t.addValue(7)
		assertEquals('23, 14, 7, 31', t.preOrder())
		t.addValue(17)
		assertEquals('23, 14, 7, 17, 31', t.preOrder())
		t.addValue(9)
		assertEquals(PREORDER, t.preOrder())
	}

	@Test
	public final void testPreOrder() {
		assertEquals(PREORDER, tree.preOrder())
	}

	@Test
	public final void testPostOrder() {
		assertEquals(POSTORDER, tree.postOrder())
	}

	@Test
	public final void testInOrder() {
		assertEquals(INORDER, tree.inOrder())
	}

	@Test
	public final void testBreadthFirst() {
		assertEquals(BREADTH_FIRST, tree.breadthFirst())
	}

	@Test
	public final void testDepth() {
		def t = new BinaryTree(23)
		Assert.assertEquals(1, t.depth())
		t.addValue(14)
		Assert.assertEquals(2, t.depth())
		t.addValue(31)
		Assert.assertEquals(2, t.depth())
		t.addValue(7)
		Assert.assertEquals(3, t.depth())
		t.addValue(17)
		Assert.assertEquals(3, t.depth())
		t.addValue(9)
		Assert.assertEquals(4, t.depth())
	}

	@Test
	public final void testPrintLeafs() {
		assertEquals(LEAFS, tree.printLeafs())
	}
}
