package DataStructures;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class BTreeResources
{

	public static void main(String[] args)
	{
		BTree<String> tr = new BTree<String>("j");
		tr.setLR("j", "f", "k");
		tr.setLR("f", "a", "h");
		tr.setR("a", "d");
		tr.setR("k", "z");
		tr.prettyPrintTree();
		tr.printPreOrder();
		tr.printPostOrder();
		tr.printInOrder();
		System.out.println(tr.size());
		System.out.println(tr.depth());
		// -----------------------------
		AmorphousBTreeCreator<String> atr = new AmorphousBTreeCreator<String>();
		// Allows out-of-order creation
		atr.addR("k", "z");
		atr.addLR("j", "f", "k");
		atr.addR("a", "d");
		atr.addLR("f", "a", "h");
		BTree<String> bt = atr.createTree();
		bt.deleteNode("d");
		bt.deleteNode("a");
		bt.deleteNode("f");
		bt.insertR("j", "o");
		bt.insertL("j", "m");
		bt.setR("m", "u");
		bt.insertR("m", "s");
		bt.prettyPrintTree();
		System.out.println(bt.size());
		System.out.println(bt.depth());
	}

}

/**
 * @author Joe A-W
 * 
 * @param <T>
 */
class AmorphousBTreeCreator<T>
{
	ArrayList<TreeNode<T>> unassignedTrees = new ArrayList<TreeNode<T>>();
	boolean fNode = true;
	private TreeNode<T> head;

	public void addLR(T headE, T leftE, T rightE)
	{
		if (fNode)
		{
			head = new TreeNode<T>(headE);
			fNode = false;
		}
		TreeNode<T> h = head.get(headE);
		if (h == null)
		{
			h = new TreeNode<T>(headE);
			unassignedTrees.add(h);
		}
		TreeNode<T> lc = new TreeNode<T>(leftE);
		lc.setParent(h);
		h.setLeftChild(lc);
		TreeNode<T> rc = new TreeNode<T>(rightE);
		rc.setParent(h);
		h.setRightChild(rc);
	}

	public void addL(T headE, T leftE)
	{
		if (fNode)
		{
			head = new TreeNode<T>(headE);
			fNode = false;
		}
		TreeNode<T> h = head.get(headE);
		if (h == null)
		{
			h = new TreeNode<T>(headE);
			unassignedTrees.add(h);
		}
		TreeNode<T> lc = new TreeNode<T>(leftE);
		lc.setParent(h);
		h.setLeftChild(lc);
	}

	public void addR(T headE, T rightE)
	{
		if (fNode)
		{
			head = new TreeNode<T>(headE);
			fNode = false;
		}
		TreeNode<T> h = head.get(headE);
		if (h == null)
		{
			h = new TreeNode<T>(headE);
			unassignedTrees.add(h);
		}
		TreeNode<T> rc = new TreeNode<T>(rightE);
		rc.setParent(h);
		h.setRightChild(rc);
	}

	/**
	 * * @return BTree
	 
	 *  Big O on this is (by my rough estimation): 
	 *               Worst Case: O(n^2) - no subtrees are connected to head
	 *               Best Case: O(n) - All subtrees were in natural order and connected.
	 *                      where n is `unassignedTrees.size()`
	 *  Returns a Tree constructed from all isolated sub-trees, using node values as keys.
	 */
	public BTree<T> createTree()
	{
		int x = unassignedTrees.size();
		for (int i = 0; i < x && unassignedTrees.size() > 0; i++)
		{
			for (int j = 0; j < unassignedTrees.size(); j++)
			{
				TreeNode<T> n = unassignedTrees.get(j);
				TreeNode<T> link = head.get(n.self);
				if (link != null)
				{
					TreeNode<T> lc = n.getLeftChild();
					if (lc != null)
						lc.setParent(link);
					link.setLeftChild(lc);
					TreeNode<T> rc = n.getRightChild();
					if (rc != null)
						rc.setParent(link);
					link.setRightChild(rc);

					unassignedTrees.remove(n);
				}
				link = n.get(head.self);
				if (link != null)
				{
					TreeNode<T> lc = head.getLeftChild();
					if (lc != null)
						lc.setParent(link);
					link.setLeftChild(lc);

					TreeNode<T> rc = head.getRightChild();
					if (rc != null)
						rc.setParent(link);
					link.setRightChild(rc);
					head = n;
					unassignedTrees.remove(n);
				}
			}
		}
		return new BTree<T>(head);
	}
}

class BTree<T>
{
	private TreeNode<T> head;

	public BTree(T head)
	{
		this.head = new TreeNode<T>(head);
	}

	public BTree(TreeNode<T> head)
	{
		this.head = head;
	}

	public void setLR(T headE, T leftE, T rightE)
	{
		TreeNode<T> h = head.get(headE);
		if (h == null)
			throw new NullPointerException("Parent node does not exist!");
		TreeNode<T> lc = new TreeNode<T>(leftE);
		lc.setParent(h);
		h.setLeftChild(lc);
		TreeNode<T> rc = new TreeNode<T>(rightE);
		rc.setParent(h);
		h.setRightChild(rc);
	}

	public void setL(T headE, T leftE)
	{
		TreeNode<T> h = head.get(headE);
		if (h == null)
			throw new NullPointerException("Parent node does not exist!");
		TreeNode<T> lc = new TreeNode<T>(leftE);
		lc.setParent(h);
		h.setLeftChild(lc);
	}

	public void setR(T headE, T rightE)
	{
		TreeNode<T> h = head.get(headE);
		if (h == null)
			throw new NullPointerException("Parent node does not exist!");
		TreeNode<T> rc = new TreeNode<T>(rightE);
		rc.setParent(h);
		h.setRightChild(rc);
	}
	
	public void insertL(T headE, T leftE)
	{
		TreeNode<T> h = head.get(headE);
		if (h == null)
			throw new NullPointerException("Parent node does not exist!");
		TreeNode<T> lc = new TreeNode<T>(leftE);
		TreeNode<T> plc = h.getLeftChild();
		if(plc == null)
			throw new NullPointerException("Parent node does not have a left child!");
		lc.setParent(h);
		lc.setLeftChild(plc);
		h.setLeftChild(lc);
	}

	public void insertR(T headE, T rightE)
	{
		TreeNode<T> h = head.get(headE);
		if (h == null)
			throw new NullPointerException("Parent node does not exist!");
		TreeNode<T> rc = new TreeNode<T>(rightE);
		TreeNode<T> prc = h.getRightChild();
		if(prc == null)
			throw new NullPointerException("Parent node does not have a left child!");
		rc.setParent(h);
		rc.setRightChild(prc);
		h.setRightChild(rc);
	}

	public void deleteNode(T search)
	{
		head.deleteNode(search);
	}

	public TreeNode<T> get(T headE)
	{
		return head.get(headE);
	}
	
	public int size()
	{
		return head.size();
	}
	
	public int depth()
	{
		return head.depth();
	}
	
	public void prettyPrintTree()
	{
		BTreePrinter.printNode(head);
	}

	public void printPreOrder()
	{
		head.printPreOrder();
		System.out.println();
	}

	public void printPostOrder()
	{
		head.printPostOrder();
		System.out.println();
	}

	public void printInOrder()
	{
		head.printInOrder();
		System.out.println();
	}
}

class TreeNode<T>
{
	private TreeNode<T> rightChild;
	private TreeNode<T> leftChild;
	private TreeNode<T> parent;

	T self;

	public TreeNode(T self)
	{
		this.self = self;
	}

	public void deleteNode(T search)
	{
		if (search.equals(self))
		{
			if (leftChild != null && rightChild != null)
			{
				throw new UnsupportedOperationException("Cannot remove a node with two children!");
			}
			else if (rightChild == null)
			{
				if (parent.getLeftChild() == this)
				{
					if (leftChild == null)
						parent.setLeftChild(null);
					else
					{
						leftChild.setParent(parent);
						parent.setLeftChild(leftChild);
					}
				}
				else if (parent.getRightChild() == this)
				{
					if (leftChild == null)
						parent.setRightChild(null);
					else
					{
						leftChild.setParent(parent);
						parent.setRightChild(leftChild);
					}
				}
			}
			else if (leftChild == null)
			{
				if (parent.getLeftChild() == this)
				{
					if (rightChild == null)
						parent.setLeftChild(null);
					else
					{
						rightChild.setParent(parent);
						parent.setLeftChild(rightChild);
					}
				}
				else if (parent.getRightChild() == this)
				{
					if (rightChild == null)
						parent.setRightChild(null);
					else
					{
						rightChild.setParent(parent);
						parent.setRightChild(rightChild);
					}
				}
			}
			return;
		}
		if (leftChild != null)
			leftChild.deleteNode(search);
		if (rightChild != null)
			rightChild.deleteNode(search);
	}
	
	
	public TreeNode<T> get(T search)
	{
		if (self.equals(search))
			return this;

		if (rightChild == null && leftChild == null)
			return null;

		if (rightChild != null && leftChild == null)
			return rightChild.get(search);

		if (leftChild != null && rightChild == null)
			return leftChild.get(search);

		TreeNode<T> rf = rightChild.get(search);
		return (rf != null) ? rf : leftChild.get(search);
	}

	public TreeNode<T> getRightChild()
	{
		return rightChild;
	}

	public void setRightChild(TreeNode<T> rightChild)
	{
		this.rightChild = rightChild;
	}

	public TreeNode<T> getLeftChild()
	{
		return leftChild;
	}

	public void setLeftChild(TreeNode<T> leftChild)
	{
		this.leftChild = leftChild;
	}

	public int depth()
	{
		return depth(this);
	}

	public int depth(TreeNode<T> head)
	{
		if (head == null)
			return 0;
		else
			return 1 + Math.max(depth(head.getLeftChild()), depth(head.getRightChild()));
	}

	public int size()
	{
		return size(this);
	}

	private int size(TreeNode<T> head)
	{
		if (head == null)
			return 0;
		else
			return 1 + size(head.getLeftChild()) + size(head.getRightChild());
	}

	public void printPreOrder()
	{
		System.out.print(self + " ");
		if (leftChild != null)
			leftChild.printPreOrder();
		if (rightChild != null)
			rightChild.printPreOrder();
	}

	public void printPostOrder()
	{

		if (leftChild != null)
			leftChild.printPostOrder();
		if (rightChild != null)
			rightChild.printPostOrder();
		System.out.print(self + " ");
	}

	public void printInOrder()
	{

		if (leftChild != null)
			leftChild.printInOrder();
		System.out.print(self + " ");
		if (rightChild != null)
			rightChild.printInOrder();
	}

	public void prettyPrint()
	{
		BTreePrinter.printNode(this);
	}

	public String toString()
	{
		return self.toString();
	}

	public TreeNode<T> getParent()
	{
		return parent;
	}

	public void setParent(TreeNode<T> parent)
	{
		this.parent = parent;
	}
}

/**
 * @author Michal Kreuzman
 *
 * Pulled from StackOverflow
 */
class BTreePrinter
{

	public static <T> void printNode(TreeNode<T> root)
	{
		int maxLevel = BTreePrinter.maxLevel(root);

		printNodeInternal(Collections.singletonList(root), 1, maxLevel);
	}

	private static <T> void printNodeInternal(List<TreeNode<T>> nodes, int level, int maxLevel)
	{
		if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
			return;

		int floor = maxLevel - level;
		int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		BTreePrinter.printWhitespaces(firstSpaces);

		List<TreeNode<T>> newNodes = new ArrayList<TreeNode<T>>();
		for (TreeNode<T> node : nodes)
		{
			if (node != null)
			{
				System.out.print(node.self);
				newNodes.add(node.getLeftChild());
				newNodes.add(node.getRightChild());
			}
			else
			{
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}

			BTreePrinter.printWhitespaces(betweenSpaces);
		}
		System.out.println("");

		for (int i = 1; i <= endgeLines; i++)
		{
			for (int j = 0; j < nodes.size(); j++)
			{
				BTreePrinter.printWhitespaces(firstSpaces - i);
				if (nodes.get(j) == null)
				{
					BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
					continue;
				}

				if (nodes.get(j).getLeftChild() != null)
					System.out.print("/");
				else
					BTreePrinter.printWhitespaces(1);

				BTreePrinter.printWhitespaces(i + i - 1);

				if (nodes.get(j).getRightChild() != null)
					System.out.print("\\");
				else
					BTreePrinter.printWhitespaces(1);

				BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
			}

			System.out.println("");
		}

		printNodeInternal(newNodes, level + 1, maxLevel);
	}

	private static void printWhitespaces(int count)
	{
		for (int i = 0; i < count; i++)
			System.out.print(" ");
	}

	private static <T> int maxLevel(TreeNode<T> node)
	{
		if (node == null)
			return 0;

		return Math.max(BTreePrinter.maxLevel(node.getLeftChild()), BTreePrinter.maxLevel(node.getRightChild())) + 1;
	}

	private static <T> boolean isAllElementsNull(List<T> list)
	{
		for (Object object : list)
		{
			if (object != null)
				return false;
		}

		return true;
	}
}
