package Math;
// TextLab06st.java
// The "Sieve of Eratosthenes" Program
// This is the student, starting version of the TextLab06 assignment.

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
public class PrimeSieve
{
	public static void main(String args[])
	{
		// This main method needs additions for the 100 point version.
		Scanner input = new Scanner(System.in);
		System.out.print("Compute primes between one and: ");
		final int MAX = input.nextInt();
		input.close();
		System.out.print("--------------------------------------------------------------------------------");

		ArrayList<Integer> primes = findPrimes(MAX);
		DecimalFormat df = new DecimalFormat("0000");
		for (int i = 0; i < primes.size(); i++)
			System.out.print(((i % 20 == 0) ? "\n" : " ") + df.format(primes.get(i)));
	}

	public static ArrayList<Integer> findPrimes(int max)
	{
		ArrayList<Integer> primes = new ArrayList<Integer>();
		if (max > 2)
		{
			primes.add(2);
			boolean[] numlist = new boolean[max];
			// first sweep
			int maxit = (int) Math.sqrt(max);
			// iterate until i^2 > max
			for (int i = 3; i <= maxit; i += 2)
			{
				if (!numlist[i])
				{
					primes.add(i);
					// flag all multiples of i as unnecessary checks
					int i2 = i * 2;
					for (int j = i; j < max; j += i2)
						numlist[j] = true;
				}

			}
			// fill in the rest without internal for loop
			for (int i = maxit + ((maxit % 2 == 0) ? 1 : 0); i < max; i += 2)
				if (!numlist[i])
					primes.add(i);
		}
		return primes;
	}

	public static void displayPrimes(boolean primes[])
	{
		System.out.println("\n\nPRIMES BETWEEN 1 AND " + primes.length);
		System.out.println();

	}
}
