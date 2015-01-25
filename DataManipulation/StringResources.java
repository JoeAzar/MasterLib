package DataManipulation;

import java.util.ArrayList;

public class StringResources
{

	static String[] morse = { "01", "0111", "0101", "011", "1", "1101", "001", "1111", "11", "1000", "010", "1011", "00", "01",
			"000", "1001", "0010", "101", "111", "0", "110", "1110", "100", "0110", "0100", "0011" };

	static char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	/**
	 * @param str
	 * @return an array of permutations
	 * 
	 */
	public static String[] permutation(String str)
	{
		return permutation("", str, new ArrayList<String>());
	}

	private static String[] permutation(String prefix, String str, ArrayList<String> a)
	{
		int n = str.length();
		if (n == 0)
			a.add(prefix);
		else
		{
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), a);
		}
		String[] tem1 = new String[a.size()];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = a.get(i);
		return tem1;
	}

	// ---------------------------------------------------------------------
	/**
	 * @param s
	 * @return all possible substrings of a string, with order maintained.
	 */
	public static String[] substrings(String s)
	{
		ArrayList<String> tem = new ArrayList<String>();
		for (int sampSize = 1; sampSize <= s.length() - 1; sampSize++)
		{
			for (int locInStr = 0; locInStr < (s.length() + 1) - sampSize; locInStr++)
			{
				int endIndex = locInStr + sampSize;
				tem.add(s.substring(locInStr, endIndex).trim());
			}
		}
		String[] tem1 = new String[tem.size()];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = tem.get(i);
		return tem1;
	}

	/**
	 * @param s
	 * @param lengthOfSubstring
	 * @return all possible substrings of a string, with order maintained, up to
	 *         provided length.
	 */
	public static String[] substrings(String s, int lengthOfSubstring)
	{
		ArrayList<String> tem = new ArrayList<String>();
		for (int sampSize = 1; sampSize <= lengthOfSubstring; sampSize++)
		{
			for (int locInStr = 0; locInStr < (s.length() + 1) - sampSize; locInStr++)
			{
				int endIndex = locInStr + sampSize;
				tem.add(s.substring(locInStr, endIndex).trim());
			}
		}
		String[] tem1 = new String[tem.size()];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = tem.get(i);
		return tem1;
	}
	// ----------------------------------------------------------------------
}
