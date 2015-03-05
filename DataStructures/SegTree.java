//segment tree, for fast min/max/sum of ranges in an array
class SegTree
{
	class Node
	{
		int i, j;

		public Node(int i, int j)
		{
			this.i = i;
			this.j = j;
		}

		int min, max, sum;
		Node left, right;

		@Override
		public String toString()
		{
			return "[(" + i + ", " + j + ") : " + min + ", " + max + ", " + sum + "]";
		}
	}

	Node root;

	public SegTree(int[] data)
	{
		root = build(data, 0, data.length - 1);
	}

	//max
	public int max(int i, int j)
	{
		return max(root, i, j);
	}

	private int max(Node n, int i, int j)
	{
		if (j < n.i || i > n.j) { return Integer.MIN_VALUE; }
		if (n.left == null || (i <= n.i && j >= n.j)) { return n.max; }
		if (n.left == null) { return n.max; }
		return Math.max(max(n.left, i, j), max(n.right, i, j));
	}

	//min
	public int min(int i, int j)
	{
		return min(root, i, j);
	}

	private int min(Node n, int i, int j)
	{
		if (j < n.i || i > n.j) { return Integer.MAX_VALUE; }
		if (n.left == null || (i <= n.i && j >= n.j)) { return n.min; }
		return Math.min(min(n.left, i, j), min(n.right, i, j));
	}

	//sum
	public int sum(int i, int j)
	{
		return sum(root, i, j);
	}

	private int sum(Node n, int i, int j)
	{
		if (j < n.i || i > n.j) { return 0; }
		if (n.left == null || (i <= n.i && j >= n.j)) { return n.sum; }
		return sum(n.left, i, j) + sum(n.right, i, j);
	}

	private Node build(int[] data, int i, int j)
	{
		Node n = new Node(i, j);
		if (i == j)
			n.min = n.max = n.sum = data[i];
		else
		{
			n.left = build(data, i, i + (j - i) / 2);
			n.right = build(data, n.left.j + 1, j);
			n.min = Math.min(n.left.min, n.right.min);
			n.max = Math.max(n.left.max, n.right.max);
			n.sum = n.left.sum + n.right.sum;
		}
		return n;
	}
}
