package Dynamic;

public class Dynamic
{
	/**
	 * Returns the sum and bounds of the maximum subsequence sum within bounds
	 * returned in the form {sum, start_index, end_index};
	 */
	static int[] max_val_subsequence(int[] vals)
	{
		int a = 0, b = 1, max = Integer.MIN_VALUE;
		int i = 0, sum = 0;
		for (int j = 0; j < vals.length; ++j)
		{
			sum += vals[j];
			if (max < sum)
			{
				max = sum;
				a = i;
				b = j + 1;
			}
			if (sum <= 0)
			{
				sum = 0;
				i = j + 1;
			}
		}
		return new int[] { max, a, b };
	}

	/**
	 * given a set of elements with weight and value, maximize the value if the sum of weights cannot exceed a given sum
	 * each item can be used an infinite number of times!
	 */
	static int knapsack_problem(int[] weights, int values[], int sum)
	{
		int[] arr = new int[sum + 1];
		for (int i = 1; i < arr.length; ++i)
		{
			//if the weight must be EXACTLY the given sum, remove the following line
			arr[i] = arr[i - 1];
			for (int j = 0; j < weights.length; ++j)
			{
				if (i - weights[j] >= 0)
				{
					int tmp = arr[i - weights[j]] + values[j];
					if (tmp > arr[i])
						arr[i] = tmp;
				}
			}
		}
		return arr[sum];
	}

	/**
	 * given a set of elements with weight and value, maximize the value if the sum of weights cannot exceed a given sum
	 * each item can only be used once or zero times!
	 */
	static int int_knapsack_problem(int[] weights, int[] values, int sum)
	{
		int[][] table = new int[weights.length + 1][sum + 1];
		for (int w = 1; w <= weights.length; ++w)
		{
			for (int s = 1; s <= sum; ++s)
			{
				table[w][s] = table[w - 1][s - 1];
				if (s - weights[w - 1] >= 0)
					table[w][s] = Math.max(table[w - 1][s - weights[w - 1]] + values[w - 1], table[w][s]);
			}
		}
		return table[weights.length][sum];

	}

	/**
	 * returns the longest contiguous substring common to both input arrays
	 * written with arrays instead of strings to be easily modifiable to
	 * work with other array types
	 */
	static char[] longest_common_substring(char a[], char b[])
	{
		int[][] grid = new int[a.length + 1][b.length + 1];
		int max = 0;
		int x = -1, y = -1;
		for (int i = 1; i < grid.length; ++i)
		{
			for (int j = 1; j < grid[0].length; ++j)
			{
				if (a[i - 1] == b[j - 1])
				{
					grid[i][j] = grid[i - 1][j - 1] + 1;
					if (grid[i][j] > max)
					{
						max = grid[i][j];
						x = i;
						y = j;
					}
				}
			}
		}
		//If you would like the indices of the lcs in a and b instead, return this:
		//return new int[] { x - grid[x][y], x, y - grid[x][y], y };

		char[] substr = new char[grid[x][y]];
		System.arraycopy(a, x - grid[x][y], substr, 0, max);
		return substr;
	}

	/**
	 * given a set of integers, returns the number of ways to make them add up to sum, order dependent
	 * analogously, 'how many ways can i make change with given coin denominations'
	 */
	static long sum_configurations(int[] elements, int sum)
	{
		long[] arr = new long[sum + 1];
		arr[0] = 1;
		for (int i = 1; i < arr.length; ++i)
		{
			for (int j : elements)
				if (i - j >= 0)
					arr[i] += arr[i - j];
		}
		return arr[sum];
	}
}
