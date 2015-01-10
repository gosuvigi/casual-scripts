package com.vigi.tree

import java.util.concurrent.ArrayBlockingQueue

/**
 * 
 * @author Virgiliu
 * Sep 26, 2011
 *
 */
final class BinaryTree {

	Entry root

	BinaryTree(final def value) {
		root = new Entry(value)
	}

	final void addValue(final def value) {
		if (root == null) {
			root = new Entry(value)
			return
		}
		addValue(value, root)
	}

	final void addValue(final def value, final Entry node) {
		if (value < node.value) {
			if (node.left == null) {
				Entry left = new Entry(value)
				node.left = left
			} else {
				addValue(value, node.left)
			}
		} else if (value > node.value) {
			if (node.right == null) {
				Entry right = new Entry(value)
				node.right = right
			} else {
				addValue(value, node.right)
			}
		}
	}

	private final String correct(String s) {
		return s[0..s.size() - 3]
	}

	int depth(final Entry node) {
		if (node.isLeaf()) {
			return 1
		}
		def left = node.left != null ? 1 + depth(node.left) : 0
		def right = node.right != null ? 1 + depth(node.right) : 0
		return left > right ? left : right
	}

	final String preOrder(final Entry node) {
		if (node == null) {
			return ""
		}
		StringBuilder buff = new StringBuilder(50)
		buff << node.getValue() << ', '
		buff << preOrder(node.left)
		buff << preOrder(node.right)
		return buff.toString()
	}

	final String postOrder(final Entry node) {
		if (node == null) {
			return ""
		}
		StringBuilder buff = new StringBuilder(50)
		buff << postOrder(node.left)
		buff << postOrder(node.right)
		buff << node.getValue() << ', '
		return buff.toString()
	}

	final String inOrder(final Entry node) {
		if (node == null) {
			return ""
		}
		StringBuilder buff = new StringBuilder(50)
		buff << inOrder(node.left)
		buff << node.getValue() << ', '
		buff << inOrder(node.right)
		return buff.toString()
	}

	final String breadthFirst(final Entry fromNode) {
		Entry node = fromNode
		if (node == null) {
			return ""
		}
		StringBuilder buff = new StringBuilder(50)
		def q = new ArrayBlockingQueue(10)
		while (node != null) {
			buff << node.getValue() << ', '
			if (node.left != null) {
				q.offer(node.left)
			}
			if (node.right != null) {
				q.offer(node.right)
			}
			if (!q.isEmpty()) {
				node = q.poll()
			} else {
				node = null
			}
		}
		return buff.toString()
	}

	final String printLeafs(final Entry node) {
		if (node.isLeaf()) {
			return node.value + ', '
		}
		def buff = new StringBuilder(50)
		if (node.left != null) {
			buff << printLeafs(node.left)
		}
		if (node.right != null) {
			buff << printLeafs(node.right)
		}
		return buff.toString()
	}

	/**
	 * Visit the root first, then left subtree and after that the right
	 * subtree.
	 *
	 * @return
	 */
	final String preOrder() {
		return correct(preOrder(root))
	}

	/**
	 * The value of the node is yielded after traversing both subtrees.
	 *
	 * @return
	 */
	final String postOrder() {
		return correct(postOrder(root))
	}

	/**
	 * The value of the current node is yielded in between traversing
	 * the left subtree and the right subtree.
	 *
	 * @return
	 */
	final String inOrder() {
		return correct(inOrder(root))
	}

	/**
	 * Yields the values of all nodes of a particular depth in the tree
	 * before any deeper ones. In other words, given a depth d we would
	 * visit the values of all nodes at d in a left to right fashion,
	 * then we would proceed to d + 1 and so on until we hade no more nodes
	 * to visit.
	 *
	 * @return
	 */
	final String breadthFirst() {
		return correct(breadthFirst(root))
	}

	final int depth() {
		return depth(root)
	}

	final String printLeafs() {
		return correct(printLeafs(root))
	}
}
