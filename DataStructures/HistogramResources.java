package DataStructures;

import java.util.TreeMap;

public class HistogramResources //example usage
{

	public static void main(String[] args)
	{
		Histogram<String> k = new Histogram<String>();
		k.addField("A");
		k.addField("B");
		k.addField("C");
		k.addField("D");
		k.addField("E");

		k.addOccurences("A", 5);
		k.addOccurences("B", 8);
		k.addOccurences("C", 12);
		k.addOccurences("D", 3);
		k.addOccurences("E", 2);

		k.print();
	}

}

class Histogram<T>
{
	TreeMap<T, Integer> histo;
	final String barString = "*"; // For ease of modification

	public Histogram(T[] init, Comparator<T> c)
	{
		this(c);
		for (T var : init)
			addField(var);
	}

	public Histogram(T[] init)
	{
		this();
		for (T var : init)
			addField(var);
	}

	public Histogram(Comparator<T> c)
	{
		histo = new TreeMap<T, Integer>(c);
	}

	public Histogram()
	{
		histo = new TreeMap<T, Integer>();
	}

	public void printVertical() // destroys histogram...
	{
		@SuppressWarnings("unchecked")
		TreeMap<T, Integer> tempc = (TreeMap<T, Integer>) histo.clone();
		int x = findLargest();
		boolean hit = false;
		for (T key : histo.keySet())
		{
			if (hit)
				histo.put(key, histo.get(key) - 1);
			if (histo.get(key) == x)
				hit = true;

		}
		for (int h = 0; h < x; h++)
		{
			for (T key : histo.keySet())
			{

				int k = findLargest();
				int i = histo.get(key);
				if (i == k)
				{

					System.out.print(barString);
					histo.put(key, --i);
				}
				else
					System.out.print(" ");
				System.out.print(" ");
			}
			System.out.println();
		}

		for (T key : histo.keySet())
		{
			System.out.print(key + " ");
		}
		System.out.println();
		histo = tempc;
	}

	public void printHorizontal()
	{
		for (T key : histo.keySet())
		{
			int occ = histo.get(key);
			System.out.print(key);
			for (int i = 0; i < occ; i++)
			{
				System.out.print(" " + barString);
			}
			System.out.println();
		}
	}

	private int findLargest()
	{
		int cV = 0;
		for (Integer i : histo.values())
		{
			if (i > cV)
			{
				cV = i;
			}
		}
		return cV;
	}

	public void addField(T field)
	{
		histo.put(field, new Integer(0));
	}

	public void addOccurence(T field)
	{
		Integer found = histo.get(field);
		if (found == null)
			throw new NullPointerException("Field does not exist!");
		histo.put(field, found + 1);
	}

	public void addOccurences(T field, int oc)
	{
		Integer found = histo.get(field);
		if (found == null)
			throw new NullPointerException("Field does not exist!");
		histo.put(field, found + oc);
	}

}
