package Math;

import java.util.ArrayList;

public class MathStuff
{
	public static long findFactorial(int num) //Inefficient
	{
		long sum = 1;
		for (int i = 2; i <= num; i++)
			sum *= i;
		return sum;
	}

	public static int findTriangular(int num)
	{
		return (num * (num + 1)) / 2;
	}

	public static double findDistance(double x1, double y1, double x2, double y2)
	{
		return Math.hypot(x2 - x1, y2 - y1);
	}

	public static boolean isPrime(int num)
	{
		if (num == 2)
			return true;
		if (num % 2 == 0)
			return false;
		int max = num + 1;
		boolean[] numlist = new boolean[max];
		for (int i = 3; i < max; i += 2)
		{
			if (!numlist[i])
			{
				int i2 = i * 2;
				for (int j = i; j < max; j += i2)
				{
					if (j == num && j != i)
						return false;
					numlist[j] = true;
				}
			}
		}
		return true;
	}

	public static ArrayList<Integer> findPrimes(int max)
	{
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
}
