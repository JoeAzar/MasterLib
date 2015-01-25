package Input;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ScannerMethods
{
	// Redirect input to a file: System.setIn(new FileInputStream("inputFileName"));

	// ---------------------------- String -----------------------------
	abstract void __________String__________();

	public static String[] scanStringRow(Scanner s)
	{
		return s.nextLine().split(" ");
	}

	public static String[][] scan2DStringArray(Scanner s, int rows)
	{
		String[][] grid = new String[rows][];
		for (int i = 0; i < rows; i++)
			grid[i] = s.nextLine().split(" ");
		return grid;
	}

	public static String[] scanStringCol(Scanner s, int rows)
	{
		String[] vals = new String[rows];
		for (int i = 0; i < rows; i++)
			vals[i] = s.nextLine();
		return vals;
	}

	public static String[][] scan2DStringArray(Scanner s, String endDel)
	{
		ArrayList<String[]> al = new ArrayList<String[]>();
		while (true)
		{
			String str = s.nextLine();
			if (str.equals(endDel))
				break;
			al.add(str.split(" "));
		}
		String[][] tem1 = new String[al.size()][];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = al.get(i);
		return tem1;
	}

	public static String[] scanStringCol(Scanner s, String endDel)
	{
		ArrayList<String> al = new ArrayList<String>();
		while (true)
		{
			String str = s.nextLine();
			if (str.equals(endDel))
				break;
			al.add(str);
		}
		String[] tem1 = new String[al.size()];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = al.get(i);
		return tem1;
	}

	// ------------------------------ Int ------------------------------
	abstract void __________int__________();

	public static int[] scanIntRow(Scanner s)
	{
		String[] str = s.nextLine().split(" ");
		int in[] = new int[str.length];
		for (int i = 0; i < str.length; i++)
			in[i] = Integer.parseInt(str[i].trim());
		s.nextLine();
		return in;
	}

	public static int[][] scan2DIntArray(Scanner s, int rows)
	{
		int[][] grid = new int[rows][];
		for (int i = 0; i < rows; i++)
		{
			String[] str = s.nextLine().split(" ");
			int in[] = new int[str.length];
			for (int j = 0; j < str.length; j++)
				in[j] = Integer.parseInt(str[j].trim());
			grid[i] = in;
		}
		return grid;
	}

	public static int[] scanIntCol(Scanner s, int rows)
	{
		int[] vals = new int[rows];
		for (int i = 0; i < rows; i++)
			vals[i] = Integer.parseInt(s.nextLine());
		return vals;
	}

	public static int[][] scan2DIntArray(Scanner s, String endDel)
	{
		ArrayList<int[]> al = new ArrayList<int[]>();
		while (true)
		{
			String strtmp = s.nextLine();
			if (strtmp.equals(endDel))
				break;
			String[] str = strtmp.split(" ");
			int[] in = new int[str.length];
			for (int j = 0; j < str.length; j++)
				in[j] = Integer.parseInt(str[j]);
			al.add(in);
		}
		int[][] tem1 = new int[al.size()][];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = al.get(i);
		return tem1;
	}

	public static int[] scanIntCol(Scanner s, String endDel)
	{
		ArrayList<Integer> al = new ArrayList<Integer>();
		while (true)
		{
			String str = s.nextLine();
			if (str.equals(endDel))
				break;
			al.add(new Integer(str));
		}
		int[] tem1 = new int[al.size()];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = al.get(i);
		return tem1;
	}

	// ------------------------------ Double ------------------------------
	abstract void __________double__________();

	public static double[] scanDoubleRow(Scanner s)
	{
		String[] str = s.nextLine().split(" ");
		double in[] = new double[str.length];
		for (int i = 0; i < str.length; i++)
			in[i] = Double.parseDouble(str[i].trim());
		s.nextLine();
		return in;
	}

	public static double[][] scan2DDoubleArray(Scanner s, int rows)
	{
		double[][] grid = new double[rows][];
		for (int i = 0; i < rows; i++)
		{
			String[] str = s.nextLine().split(" ");
			double in[] = new double[str.length];
			for (int j = 0; j < str.length; j++)
				in[i] = Double.parseDouble(str[i].trim());
			grid[i] = in;
		}
		s.nextLine();
		return grid;
	}

	public static double[] scanDoubleCol(Scanner s, int rows)
	{
		double[] vals = new double[rows];
		for (int i = 0; i < rows; i++)
			vals[i] = Double.parseDouble(s.nextLine());
		s.nextLine();
		return vals;
	}

	public static double[][] scan2DDoubleArray(Scanner s, String endDel)
	{
		ArrayList<double[]> al = new ArrayList<double[]>();
		while (true)
		{
			String strtmp = s.nextLine();
			if (strtmp.equals(endDel))
				break;
			String[] str = strtmp.split(" ");
			double[] in = new double[str.length];
			for (int j = 0; j < str.length; j++)
				in[j] = Double.parseDouble(str[j]);
			al.add(in);
		}
		double[][] tem1 = new double[al.size()][];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = al.get(i);
		return tem1;
	}

	public static double[] scanDoubleCol(Scanner s, String endDel)
	{
		ArrayList<Double> al = new ArrayList<Double>();
		while (true)
		{
			String str = s.nextLine();
			if (str.equals(endDel))
				break;
			al.add(new Double(str));
		}
		double[] tem1 = new double[al.size()];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = al.get(i);
		return tem1;
	}

	// ------------------------------ Char ------------------------------
	abstract void __________char__________();

	public static char[] scanCharRow(Scanner s)
	{
		return s.nextLine().toCharArray();
	}

	public static char[][] scan2DCharArray(Scanner s, int rows)
	{
		char[][] grid = new char[rows][];
		for (int i = 0; i < rows; i++)
			grid[i] = s.nextLine().toCharArray();
		return grid;
	}

	public static char[] scanCharCol(Scanner s, int rows)
	{
		char[] vals = new char[rows];
		for (int i = 0; i < rows; i++)
			vals[i] = s.nextLine().charAt(0);
		return vals;
	}
}
