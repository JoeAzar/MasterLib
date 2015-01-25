package DataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MultigraphStuff //sample usage
{
	public static void main(String args[])
	{
		Multigraph<String> ng = new Multigraph<String>();
		ng.addRule("a", "h", 35);
		ng.addRule("a", "c", 5);
		ng.addRule("a", "b", 7);
		ng.addRule("b", "d", 8);
		ng.addRule("b", "f", 9);
		ng.addRule("b", "g", 6);
		ng.addRule("d", "e", 7);
		ng.addRule("e", "f", 12);
		ng.addRule("e", "i", 10);
		ng.addRule("c", "g", 4);
		ng.addRule("g", "h", 6);
		ng.addRule("g", "i", 4);
		ng.addRule("h", "i", 3);
		ng.addRule("h", "j", 4);
		ng.addRule("d", "j", 12);
		System.out.println(ng.shortestPath("a", "i"));
		System.out.println(ng.shortestPathHistory("a", "i"));
	}
}

class Multigraph<T>
{
	private HashMap<T, ArrayList<GraphNode<T>>> rules = new HashMap<T, ArrayList<GraphNode<T>>>();

	/**
	 * add a connection to the graph
	 * 
	 * @param a
	 *            first node to be connected
	 * @param b
	 *            second node to be connected
	 * @param distance
	 *            distance between the two nodes
	 */
	public void addRule(T a, T b, double distance)
	{
		addDirectionalRule(a, b, distance);
		addDirectionalRule(b, a, distance);
	}

	/**
	 * add a connection to the graph with distance one
	 * 
	 * @param a
	 *            first node to be connected
	 * @param b
	 *            second node to be connected
	 */
	public void addRule(T a, T b)
	{
		addRule(a, b, 1);
	}

	/**
	 * add a unidirectional connection to the graph of distance one
	 * 
	 * @param a
	 *            first node to be connected
	 * @param b
	 *            second node to be connected
	 */
	public void addDirectionalRule(T a, T b)
	{
		addDirectionalRule(a, b, 1);
	}

	/**
	 * add a unidirectional connection to the graph
	 * 
	 * @param a
	 *            first node to be connected
	 * @param b
	 *            second node to be connected
	 * @param distance
	 *            distance between the two nodes
	 */
	public void addDirectionalRule(T a, T b, double distance)
	{
		if (rules.containsKey(a))
		{
			GraphNode<T> n = new GraphNode<T>(b, distance);
			ArrayList<GraphNode<T>> al = rules.get(a);
			al.remove(n);
			al.add(n);
		} else
		{
			ArrayList<GraphNode<T>> al = new ArrayList<GraphNode<T>>();
			al.add(new GraphNode<T>(b, distance));
			rules.put(a, al);
		}
	}

	/**
	 * find the minimum cost traversal between two points
	 * 
	 * @param a
	 *            start node
	 * @param b
	 *            end node
	 * @return cost of traversal
	 */
	public double shortestPath(T a, T b)
	{
		if (a.equals(b))
			return 0;
		ArrayList<GraphNode<T>> pq = new ArrayList<GraphNode<T>>();

		for (T obj : rules.keySet())
		{
			if (obj.equals(a))
				pq.add(new GraphNode<T>(obj, 0));
			else
				pq.add(new GraphNode<T>(obj, Double.POSITIVE_INFINITY));
		}
		Collections.sort(pq);
		while (pq.size() != 1)
		{
			if (pq.get(0).value.equals(b))
				return pq.get(0).distance;

			GraphNode<T> n = pq.get(0);
			pq.remove(0);
			for (GraphNode<T> v : rules.get(n.value))
			{
				double d = v.distance + n.distance;
				for (GraphNode<T> node : pq)
				{
					if (node.value.equals(v.value) && d < node.distance)
					{
						node.distance = d;
						break;
					}
				}

			}
			Collections.sort(pq);
		}
		return pq.get(0).distance;
	}

	/**
	 * find the minimum cost traversal between two points
	 * 
	 * @param a
	 *            start node
	 * @param b
	 *            end node
	 * @return Sequence of objects traversed
	 */
	public ArrayList<T> shortestPathHistory(T a, T b)
	{
		ArrayList<GraphNode<T>> pq = new ArrayList<GraphNode<T>>();
		ArrayList<ArrayList<T>> pqd = new ArrayList<ArrayList<T>>();

		for (T obj : rules.keySet())
		{
			if (obj.equals(a))
				pq.add(new GraphNode<T>(obj, 0));
			else
				pq.add(new GraphNode<T>(obj, Double.POSITIVE_INFINITY));
			ArrayList<T> tmpa = new ArrayList<T>();
			tmpa.add(obj);
			pqd.add(tmpa);
		}
		Collections.sort(pq);
		while (pq.size() != 1)
		{
			if (pq.get(0).value.equals(b))
				break;
			GraphNode<T> n = pq.get(0);
			pq.remove(0);
			for (GraphNode<T> v : rules.get(n.value))
			{
				double d = v.distance + n.distance;
				for (GraphNode<T> node : pq)
				{
					if (node.value.equals(v.value) && d < node.distance)
					{
						int ind = indexin(node.value, pqd);
						int ind2 = indexin(n.value, pqd);
						ArrayList<T> tmpstk = new ArrayList<T>();
						tmpstk.add(node.value);
						if (ind2 >= 0)
							tmpstk.addAll(pqd.get(ind2));
						if (ind == -1)
							pqd.add(tmpstk);
						else
							pqd.set(ind, tmpstk);
						node.distance = d;
						break;
					}
				}

			}
			Collections.sort(pq);
		}
		ArrayList<T> tp = pqd.get(indexin(pq.get(0).value, pqd));
		Collections.reverse(tp);
		return tp;
	}

	/**
	 * find the connections to a particular node
	 * 
	 * @param val
	 *            the value to find connections from
	 * @return list of connections
	 */
	public ArrayList<T> getConnections(T val)
	{
		ArrayList<T> al = new ArrayList<T>();
		for (GraphNode<T> n : rules.get(val))
			al.add(n.value);
		return al;
	}

	public void scanGrid(T[][] arr, T[] accepted)
	{
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[i].length - 1; j++)
			{
				if (elem(arr[i][j], accepted) && elem(arr[i][j + 1], accepted))
				{
					addRule(arr[i][j], arr[i][j + 1]);
				}
			}
		}
		for (int i = 0; i < arr[0].length; i++)
		{
			for (int j = 0; j < arr.length - 1; j++)
			{
				if (elem(arr[i][j], accepted) && elem(arr[i][j + 1], accepted))
				{
					addRule(arr[i][j], arr[i][j + 1]);
				}
			}
		}
	}

	public static <T> boolean elem(T element, T[] array)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(element))
				return true;
		return false;
	}

	private int indexin(T val, ArrayList<ArrayList<T>> pqd)
	{
		for (int i = 0; i < pqd.size(); i++)
			if (pqd.get(i).get(0).equals(val))
				return i;

		return -1;
	}

	@Override
	public String toString()
	{
		return rules.toString();
	}
}

class GraphNode<T> implements Comparable<GraphNode<T>>
{
	T value;
	double distance;

	public GraphNode(T v, double d)
	{
		value = v;
		distance = d;
	}

	@Override
	public int compareTo(GraphNode<T> n)
	{
		double tmp = distance - n.distance;
		if (tmp > 0)
			return 1;
		else if (tmp < 0)
			return -1;
		return 0;
	}
}
