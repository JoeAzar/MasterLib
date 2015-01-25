package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *	A simple graph structure, supports integer weights and direction
 * supports djikstra's, bfs, dfs
 */
public class UnweightedGraph<V>
{
	// ------------------------------ START ESSENTIALS ----------------------------------------
	TwinMap<V, Integer> tm = new TwinMap<V, Integer>();
	ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	void addEdge(V a, V b, boolean directed)
	{
		if (directed == false)
		{
			addEdge(a, b, true);
			addEdge(b, a, true);
			return;
		}
		int a_ind = addVert(a);
		int b_ind = addVert(b);
		adj.get(a_ind).add(b_ind);
	}

	int addVert(V a)
	{
		Integer ind;
		if ((ind = tm.get1(a)) == null)
		{
			tm.put(a, adj.size());
			adj.add(new ArrayList<Integer>());
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
	// ------------------------------ START BFS ----------------------------------------

	/**
	 * Solo Breadth first search
	 */
	void BFS(int v)
	{
		boolean[] visited = new boolean[adj.size()];
		LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<Integer>();
		lbq.add(v);
		while (!lbq.isEmpty())
		{
			int node = lbq.poll();
			visited[node] = true;
			ArrayList<Integer> next = adj.get(node);
			for (int i : next)
			{
				if (!visited[i])
					lbq.add(i);
			}
		}
	}

	/**
	 * Find shortest path from a to b using BFS
	 * returns null if no path, otherwise returns an ArrayList of visited nodes
	 */
	ArrayList<Integer> shortestPath(int a, int b)
	{
		boolean[] visited = new boolean[adj.size()];
		int[] previous = new int[adj.size()];
		Arrays.fill(previous, -1);
		previous[a] = a;

		LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<Integer>();
		lbq.add(a);
		queue: while (!lbq.isEmpty())
		{
			int node = lbq.poll();
			visited[node] = true;
			ArrayList<Integer> next = adj.get(node);
			for (int i : next)
			{
				if (!visited[i])
				{
					lbq.add(i);
					previous[i] = node;
					if (i == b)
						break queue;
				}
			}
		}
		if (previous[b] == -1)
			return null;
		ArrayList<Integer> path = new ArrayList<Integer>();
		while (b != a)
		{
			path.add(b);
			b = previous[b];
		}
		path.add(a);
		Collections.reverse(path);
		return path;
	}

	/**
	 * Shortest path if you don't need a backtrace
	 */

	int shortestDist(int a, int b)
	{
		return shortestPath(a, b).size() - 1;
	}

	// ------------------------------ END BFS ----------------------------------------
	// ------------------------------ START DFS ----------------------------------------

	/**
	 * general depth first search method for usage/modification
	 */
	void DFS(int v, boolean[] visited)
	{
		visited[v] = true;
		// DO STUFF BEFORE YOU GO TO NEXT NODE
		for (int i : adj.get(v))
		{
			if (!visited[i])
				DFS(i, visited);
		}
		//DO STUFF AFTER COMING BACK FROM ALL NODES
	}

	/**
	 * Returns the number of connected components in a graph
	 * ONLY WORKS FOR UNDIRECTED GRAPHS!
	 * @return
	 */
	public int connectedComponents()
	{
		boolean[] visited = new boolean[adj.size()];
		int num = 0;
		for (int i = 0; i < visited.length; ++i)
		{
			if (!visited[i])
			{
				++num;
				DFS(i, visited);
			}
		}
		return num;
	}
	// ------------------------------ END DFS ----------------------------------------
}
