class UnionFind<T>
{
	ArrayList<T> nodes;
	ArrayList<Integer> pointers;
	ArrayList<Integer> size;

	public UnionFind()
	{
		nodes = new ArrayList<T>();
		pointers = new ArrayList<Integer>();
		size = new ArrayList<Integer>();
	}

	void add(T t)
	{
		nodes.add(t);
		pointers.add(pointers.size());
		size.add(1);
	}

	int getSet(T t)
	{
		int i = nodes.indexOf(t);
		int j = i;
		while (pointers.get(i) != i)
			i = pointers.get(i);
		if (i != j)
			pointers.set(j, i);
		return i;
	}

	void merge(T a, T b)
	{
		int a_set = getSet(a);
		int b_set = getSet(b);
		if (a_set != b_set)
		{
			pointers.set(a_set, b_set);
			size.set(b_set, size.get(b_set) + size.get(a_set));
		}
	}

	boolean sameSet(T a, T b)
	{
		return getSet(a) == getSet(b);
	}

	int size(T t)
	{
		return size.get(getSet(t));
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (T t : nodes)
			sb.append("[" + t + " : " + getSet(t) + "]");
		return sb.toString();
	}
}
