class Formulas
{
	/**
	 * Integer power function
	 */
	public static long pow(int a, int b)
	{
		if (b == 0)
			return 1;
		if (b % 2 == 0)
		{
			long c = pow(a, b / 2);
			return c * c;
		}
		return pow(a, b - 1) * a;
	}

	/**
	 * factorial
	 */
	public static long factorial(int n)
	{
		long f = 1;
		while (n > 1)
			f *= n--;
		return f;
	}

	/**
	 * binomial coefficient
	 */
	public static long nCr(int n, int k)
	{
		if (k > n / 2)
			k = n - k;
		long result = 1;
		for (int i = 0; i < k; ++i)
		{
			result *= n - i;
			result /= i + 1;
		}
		return result;
	}

	/**
	 * returns sum of the first n squares
	 */
	public static long squaresum(long n)
	{
		return (n * (n + 1) * (2 * n + 1)) / 6;
	}
}
