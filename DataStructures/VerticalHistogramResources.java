package DataStructures;

import java.util.TreeMap;

public class VerticalHistogramResources //example usage
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

class VerticalHistogram<T>
{
	TreeMap<T, Integer> histo = new TreeMap<T, Integer>();

	public VerticalHistogram(T[] init)
	{
		for (T var : init)
			addField(var);
	}

	public VerticalHistogram()
	{
	}
	
	public void print() // destroys histogram...
	{
		@SuppressWarnings("unchecked")
		TreeMap<T, Integer> tempc = (TreeMap<T, Integer>) histo.clone();
		String printString = "*"; // For ease of modification
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

					System.out.print(printString);
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
