package DataManipulation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayMethods
{
	/**
	 * @param array
	 * @return a reversed version of the array.
	 */
	public static <T> T[] reverse(T[] array)
	{
		for (int i = 0; i < array.length / 2; i++)
		{
			T temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
		return array;
	}

	/**
	 * @param darray
	 * @return a reversed version of the array.
	 */
	public static int[] reverse(int[] array)
	{
		for (int i = 0; i < array.length / 2; i++)
		{
			int temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
		return array;
	}

	/**
	 * @param element
	 * @param array
	 * @return if the given element exists in the array.
	 * 
	 *         This version uses .equals() for comparison.
	 */
	public static <T> boolean elem(T element, T[] array)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(element))
				return true;
		return false;
	}

	/**
	 * @param element
	 * @param array
	 * @return if the given element exists in the array.
	 */
	public static boolean elem(int element, int[] array)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i] == (element))
				return true;
		return false;
	}

	
	/**
	 * @param element
	 * @param array
	 * @return all indexes where the element equals the given element.
	 * 
	 *         This version uses .equals() for comparison.
	 */
	public static <T> int[] elemIndices(T element, T[] array)
	{
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(element))
				temp.add(i);
		int[] tem1 = new int[temp.size()];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = temp.get(i);
		return tem1;
	}

	/**
	 * @param element
	 * @param array
	 * @return all indexes where the element equals the given element.
	 * 
	 * 
	 */
	public static int[] elemIndices(int element, int[] array)
	{
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++)
			if (array[i] == (element))
				temp.add(i);
		int[] tem1 = new int[temp.size()];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = temp.get(i);
		return tem1;
	}

	/**
	 * @param element
	 * @param array
	 * @return the index of the first element to equal the target element.
	 * 
	 * 
	 */
	public static int elemIndex(int element, int[] array)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i] == element)
				return i;
		return -1;
	}


	/**
	 * @param element
	 * @param array
	 * @return the index of the first element to equal the target element.
	 * 
	 *         This version uses .equals() for comparison.
	 */
	public static <T> int elemIndex(T element, T[] array)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(element))
				return i;
		return -1;
	}

	/**
	 * @param array
	 * @return all strings in the array concatenated to one-another.
	 */
	public static String concat(String[] array)
	{
		String temp = "";
		for (String s : array)
			temp += s;
		return temp;
	}


	/**
	 * @param array
	 * @return a 1D array containing all the elements of the 2D Array.
	 */
	public static int[] concat(int[][] array)
	{
		int[] temp = new int[array.length * array[0].length];
		int cnt = 0;
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array.length; j++)
			{
				temp[cnt] = array[i][j];
				cnt++;
			}
		return temp;
	}
	
	public static <T> T[] concatenateArr(T[] A, T[] B)
	{
		int aLen = A.length;
		int bLen = B.length;

		@SuppressWarnings("unchecked")
		T[] C = (T[]) Array.newInstance(A.getClass().getComponentType(), aLen + bLen);
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);

		return C;
	}

	// From Stack Overflow
	static <T> T[] append(T[] arr, T lastElement)
	{
		final int N = arr.length;
		arr = java.util.Arrays.copyOf(arr, N + 1);
		arr[N] = lastElement;
		return arr;
	}

	static <T> T[] prepend(T[] arr, T firstElement)
	{
		final int N = arr.length;
		arr = java.util.Arrays.copyOf(arr, N + 1);
		System.arraycopy(arr, 0, arr, 1, N);
		arr[0] = firstElement;
		return arr;
	}

	static ArrayList<int[]> permutations(int[] a)
	{
		ArrayList<int[]> ret = new ArrayList<int[]>();
		permutation(a, 0, ret);
		return ret;
	}

	public static void permutation(int[] arr, int pos, ArrayList<int[]> list)
	{
		if (arr.length - pos == 1)
			list.add(arr.clone());
		else
			for (int i = pos; i < arr.length; i++)
			{
				int h = arr[pos];
				arr[pos] = arr[i];
				arr[i] = h;
				permutation(arr, pos + 1, list);
				h = arr[pos];
				arr[pos] = arr[i];
				arr[i] = h;
			}
	}

	public static int[][] combinations(int[] s)
	{
		ArrayList<int[]> tem = new ArrayList<int[]>();
		for (int sampSize = 1; sampSize <= s.length; sampSize++)
		{
			for (int locInStr = 0; locInStr < (s.length + 1) - sampSize; locInStr++)
			{
				int endIndex = locInStr + sampSize;
				tem.add(Arrays.copyOfRange(s, locInStr, endIndex));
			}
		}
		int[][] tem1 = new int[tem.size()][];
		for (int i = 0; i < tem1.length; i++)
			tem1[i] = tem.get(i);
		return tem1;
	}

	public static int compareMatrices(int[][] num, int[][] mat)
	{
		int highest = 0, score = 0;
		int fX = 0, fY = 0; // if you need the coords...
		for (int numX = 0; numX <= num.length - mat.length; numX++)
		{
			for (int numY = 0; numY <= num[0].length - mat[0].length; numY++)
			{
				score = 0;
				for (int matX = 0; matX < mat.length; matX++)
				{
					for (int matY = 0; matY < mat[0].length; matY++)
					{
						if (num[numX + matX][numY + matY] == mat[matX][matY])
							score++;
					}
				}

				if (score > highest)
				{
					fX = numX;
					fY = numY;
					highest = score;
				}
			}
		}
		return highest; // highest num of elements in array that match sub-array
	}

	static char[][] strToDiagMatrix(String s)
	{
		int sideLen = (int) Math.sqrt(s.length()); // assume string fits into
													// square matrix
		char[][] m = new char[sideLen][sideLen];
		int index = 0;
		for (int i = m[0].length - 1; i >= 0; i--)
		{
			for (int k = 0; k <= m[0].length - 1 - i; k++)
			{
				m[i + k][k] = s.charAt(index++);
			}
		}
		for (int i = sideLen % 2 == 1 ? sideLen / 2 : sideLen / 2 - 1; i <= m[0].length; i++)
		{
			for (int k = 0; k <= m[0].length - 1 - i; k++)
			{
				m[k][i + k] = s.charAt(index++);
			}
		}
		return m;
	}

	public static char[][] flipOnXAxis(char[][] original)
	{
		char[][] tempA = new char[original.length][original[0].length];
		System.arraycopy(original, 0, tempA, 0, original.length);
		for (int i = 0; i < (tempA.length / 2); i++)
		{
			char[] temp = tempA[i];
			tempA[i] = tempA[tempA.length - i - 1];
			tempA[tempA.length - i - 1] = temp;
		}
		return tempA;
	}

	public static char[][] flipOnYAxis(char[][] original)
	{
		char[][] yAxisReverse = new char[original.length][original[0].length];
		if (yAxisReverse.length == 1)
		{
			System.arraycopy(original, 0, yAxisReverse, 0, original.length);
			return yAxisReverse;
		}

		for (int i = 0; i < yAxisReverse.length; i++)
		{
			for (int j = 0; j < ((yAxisReverse[0].length % 2 == 0) ? yAxisReverse[0].length / 2
					: (yAxisReverse[0].length / 2) + 1); j++)
			{
				yAxisReverse[i][j] = original[i][original[0].length - 1 - j];
				yAxisReverse[i][original[0].length - 1 - j] = original[i][j];
			}
		}
		return yAxisReverse;
	}

	public static char[][] rotate90Right(char[][] ori)
	{
		char[][] temp = new char[ori[0].length][ori.length];
		for (int i = 0; i < ori[0].length; i++)
		{
			for (int j = 0; j < ori.length; j++)
			{
				temp[i][j] = ori[j][i];
			}
		}
		return temp;
	}

	public static void deepCopy(char[][] m, char[][] h)
	{
		for (int i = 0; i < h.length; i++)
		{
			System.arraycopy(m[i], 0, h[i], 0, m[i].length);
		}
	}
}
