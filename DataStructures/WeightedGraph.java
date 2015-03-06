public class WeightedGraph<V>
{

	// ------------------------------ START ESSENTIALS ----------------------------------------
	TwinMap<V, Integer> tm = new TwinMap<V, Integer>();
	ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

	class Edge implements Comparable<Edge>
	{
		int a;
		int b;
		int weight;

		public Edge(int a, int b, int w)
		{
			this.a = a;
			this.b = b;
			weight = w;
		}

		@Override
		public int compareTo(Edge e)
		{
			return weight - e.weight;
		}

		@Override
		public String toString()
		{
			return "(" + getV(a) + ", " + getV(b) + "|" + weight + ")";
		}

	}

	void addEdge(V a, V b, int weight, boolean directed)
	{
		if (directed == false)
		{
			addEdge(a, b, weight, true);
			addEdge(b, a, weight, true);
			return;
		}
		int a_ind = addVert(a);
		int b_ind = addVert(b);
		adj.get(a_ind).add(new Edge(a_ind, b_ind, weight));
	}

	int addVert(V a)
	{
		Integer ind;
		if ((ind = tm.get1(a)) == null)
		{
			tm.put(a, adj.size());
			adj.add(new ArrayList<Edge>());
			return adj.size() - 1;
		}
		return ind;
	}

	V getV(int i)
	{
		return tm.get2(i);
	}

	int getI(V v)
	{
		return tm.get1(v);
	}

	// ------------------------------ END ESSENTIALS ----------------------------------------
	// ------------------------------ START DJIKSTRAS ----------------------------------------

	public ArrayList<V> ShortestPath(V a, V b)
	{
		ArrayList<Integer> a1 = ShortestPathInt(getI(a), getI(b));
		ArrayList<V> a2 = new ArrayList<V>(a1.size());
		for (Integer i : a1)
			a2.add(getV(i));
		return a2;
	}

	public ArrayList<Integer> ShortestPathInt(int a, int b)
	{
		int[] prev = new int[adj.size()];
		prev[a] = a;

		int[] min = new int[adj.size()];
		Arrays.fill(min, Integer.MAX_VALUE);
		min[a] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(a, a, 0));

		while (!pq.isEmpty())
		{
			Edge c = pq.poll();
			if (c.b == b)
				break;
			for (Edge e : adj.get(c.b))
			{
				if (c.weight + e.weight < min[e.b])
				{
					prev[e.b] = c.b;
					min[e.b] = c.weight + e.weight;
					pq.add(new Edge(a, e.b, min[e.b]));
				}
			}
		}
		if (prev[b] == -1)
			return null;
		ArrayList<Integer> path = new ArrayList<Integer>();
		while (b != a)
		{
			path.add(b);
			b = prev[b];
		}
		path.add(a);
		Collections.reverse(path);
		return path;
	}

	public int ShortestDist(V a, V b)
	{
		return ShortestDistInt(getI(a), getI(b));
	}

	public int ShortestDistInt(int a, int b)
	{
		int[] min = new int[adj.size()];
		Arrays.fill(min, Integer.MAX_VALUE);
		min[a] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(a, a, 0));

		while (!pq.isEmpty())
		{
			Edge c = pq.poll();
			if (c.b == b)
				break;
			for (Edge e : adj.get(c.b))
			{
				if (c.weight + e.weight < min[e.b])
				{
					min[e.b] = c.weight + e.weight;
					pq.add(new Edge(a, e.b, min[e.b]));
				}
			}
		}
		if (min[b] == Integer.MAX_VALUE)
			return -1;
		return min[b];
	}

	// ------------------------------ END DJIKSTRAS ----------------------------------------
	// ------------------------------ START MIN SPANNING TREE ----------------------------------------

	/**
	 * returns the weight of the a minimum spanning tree of the graph
	 * ONLY WORKS FOR UNDIRECTED GRAPHS
	 */
	public int Kruskals()
	{
		UnionFind<Integer> components = new UnionFind<Integer>();
		for (int i = 0; i < adj.size(); ++i)
			components.add(i);
		PriorityQueue<Edge> edgequeue = new PriorityQueue<Edge>();
		for (ArrayList<Edge> adjlist : adj)
		{
			for (Edge e : adjlist)
			{
				if (e.a < e.b)
					edgequeue.add(e);
			}
		}
		int weight = 0;
		while (!edgequeue.isEmpty() && components.sets > 1)
		{
			Edge e = edgequeue.poll();
			System.out.println(e + " : " + components.sets);

			if (!components.sameSet(e.a, e.b))
			{
				weight += e.weight;
				components.merge(e.a, e.b);
			}
		}
		return weight;
	}

	// ------------------------------ END MIN SPANNING TREE ----------------------------------------
}

class TwinMap<A, B>
{
	HashMap<A, B> a = new HashMap<A, B>();
	HashMap<B, A> b = new HashMap<B, A>();

	public void put(A k, B v)
	{
		a.put(k, v);
		b.put(v, k);
	}

	public B get1(A key)
	{
		return a.get(key);
	}

	public A get2(B key)
	{
		return b.get(key);
	}

	public Set<A> set1()
	{
		return a.keySet();
	}

	public Set<B> set2()
	{
		return b.keySet();
	}

	public int size()
	{
		return a.size();
	}

	public boolean contains1(A k)
	{
		return a.containsKey(k);
	}

	public boolean contains2(B k)
	{
		return b.containsKey(k);
	}

	public void remove1(A k)
	{
		b.remove(a.get(k));
		a.remove(k);
	}

	public void remove2(B k)
	{
		a.remove(b.get(k));
		b.remove(k);
	}
}
