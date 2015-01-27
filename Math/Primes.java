class Primes
{

	/**
	 * Gets an ArrayList of prime numbers from two to N, inclusive
	 */
	public static ArrayList<Integer> getPrimeList(int max)
	{
		++max;
		ArrayList<Integer> primes = new ArrayList<Integer>();
		if (max > 2)
		{
			primes.add(2);
			boolean[] numlist = new boolean[max];
			int maxit = (int) Math.sqrt(max);
			for (int i = 3; i <= maxit; i += 2)
			{
				if (!numlist[i])
				{
					primes.add(i);
					int i2 = i * 2;
					for (int j = i; j < max; j += i2)
						numlist[j] = true;
				}
			}
			for (int i = maxit + ((maxit % 2 == 0) ? 1 : 0); i < max; i += 2)
				if (!numlist[i])
					primes.add(i);
		}
		return primes;
	}

	/**
	 * Deterministic Miller-Rabin Primality test for n <= 3825123056546413051
	 * most useful for large primality checking
	 */
	public static boolean isPrime(long n)
	{
		if (n % 2 == 0)
			return false;
		long r = 0;
		long s = n - 1;
		while (s % 2 == 0)
		{
			++r;
			s >>= 1;
		}
		int[] mn;
		if (n < 2047L)
			mn = new int[] { 2 };
		else if (n < 1373653L)
			mn = new int[] { 2, 3 };
		else if (n < 9080191L)
			mn = new int[] { 31, 73 };
		else if (n < 25326001L)
			mn = new int[] { 2, 3, 5 };
		else if (n < 4759123141L)
			mn = new int[] { 2, 7, 61 };
		else if (n < 1122004669633L)
			mn = new int[] { 2, 13, 23, 1662803 };
		else if (n < 2152302898747L)
			mn = new int[] { 2, 3, 5, 7, 11 };
		else if (n < 3474749660383L)
			mn = new int[] { 2, 3, 5, 7, 11, 13 };
		else if (n < 341550071728321L)
			mn = new int[] { 2, 3, 5, 7, 11, 13, 17 };
		else if (n < 3825123056546413051L)
			mn = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23 };
		else
			mn = new int[0];
		for (int i : mn)
			if (!millerRabin(i, r, s, n))
				return false;
		return true;
	}

	public static boolean millerRabin(long a, long r, long s, long n)
	{
		if (Modulus.mod_exp(a, s, n) == 1)
			return true;
		long a_s = Modulus.mod_exp(a, s, n);
		for (int j = 0;; ++j)
		{
			if (a_s == n - 1)
				return true;
			if (j >= r)
				break;
			a_s = Modulus.mod_mult(a_s, a_s, n);
		}
		return false;
	}

	/**
	 * A function used to check for primality that dynamically grow it's list of
	 * primes; utilizes data saved outside the function itself
	 */

	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static
	{
		primes.add(2);
	}
	static int max = 2;

	public static boolean isPrime2(int num)
	{
		if (num < 2)
			return false;
		if (num <= max)
			return primes.contains(num);
		if (num % 2 == 0)
			return false;
		l: for (int i = max + ((max % 2 == 1) ? 2 : 1); i <= num; i += 2)
		{
			for (int j : primes)
				if (i % j == 0)
					continue l;
			primes.add(i);
		}
		max = num;
		return primes.get(primes.size() - 1) == num;
	}

	/**
	 * A solution that returns the prime factorization of any number in array
	 * form; DEPENDS ON THE PREVIOUS SECTION TO WORK CORRECTLY; index 0 column
	 * is the prime factor; index 1 column is the exponent of the factor;
	 */

	public static int[][] getFactorization(int num)
	{
		ArrayList<Integer> prm = new ArrayList<Integer>();
		ArrayList<Integer> exp = new ArrayList<Integer>();
		if (isPrime(num))
		{
			prm.add(num);
			exp.add(1);
		}
		else
		{
			for (int p : primes)
			{
				if (num == 1)
					break;
				int e = 0;
				while (num % p == 0)
				{
					num /= p;
					++e;
				}
				if (e != 0)
				{
					prm.add(p);
					exp.add(e);
				}
			}
		}
		int[][] factorization = new int[prm.size()][];
		for (int i = 0; i < prm.size(); ++i)
			factorization[i] = new int[] { prm.get(i), exp.get(i) };
		return factorization;
	}
}
