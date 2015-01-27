class Modular
{

	/**
	 * modulus defined in the 'math' way
	 */
	static long mod(long n, long m)
	{
		if (n >= 0)
			return n % m;
		return (n + m * (-n / m + 1)) % m;
	}

	/**
	 * returns a^b mod m
	 */
	public static long mod_exp(long a, long b, long m)
	{
		long x = 1, y = a;
		while (b > 0)
		{
			if (b % 2 == 1)
				x = mod_mult(x, y, m);
			y = mod_mult(y, y, m);
			b /= 2;
		}
		return x % m;
	}

	/**
	 * returns a*b mod m
	 */
	public static long mod_mult(long a, long b, long m)
	{
		long x = 0, y = a % m;
		while (b > 0)
		{
			if (b % 2 == 1)
				x = (x + y) % m;
			y = (y * 2) % m;
			b /= 2;
		}
		return x % m;
	}

	/**
	 * returns a + b mod m, slightly safer from overflow than (a+b)%m
	 */
	public static long mod_add(long a, long b, long m)
	{
		return (a % m + b % m) % m;
	}

	/**
	 * returns the greatest common divisor of a and b
	 */
	static long gcd(long a, long b)
	{
		return (b == 0) ? a : gcd(b, a % b);
	}

	/**
	 * extended gcd algorithm. finds x and y such that
	 * ax+by=gcd(a, b) and returns them in the form {gcd(a,b), x, y}
	 */
	static long[] egcd(long a, long b)
	{
		if (a == 0)
			return new long[] { b, 0, 1 };
		long[] val = egcd(b % a, a);
		long y = val[1], x = val[2];
		val[1] = x - (b / a) * y;
		val[2] = y;
		return val;
	}

	/**
	 * returns the modular inverse of n mod m, or
	 * Integer.MAX_VALUE if it does not exist
	 */
	static long mod_inv(long n, long m)
	{
		long[] v = egcd(n, m);
		if (v[0] != 1)
			return Integer.MAX_VALUE;
		return mod(v[1], m);
	}

	/**
	 * solves the linear congruence ax = b (mod m) for x
	 */
	static long lin_con(long a, long b, long m)
	{
		long d = gcd(a, m);
		if (b % d != 0)
			return Integer.MAX_VALUE;
		a /= d;
		b /= d;
		m /= d;
		return mod(egcd(a, m)[1] * b, m);
	}

	/**
	 * chinese remainder theorem
	 * solves a system of linear congruences n = a[i] (mod m[i]) for n (mod m[0]*m[1]*...*m[m.length-1])
	 */
	static long chinese_remainder(long[] a, long[] m)
	{
		int N = 1, c = 0;
		for (int i = 0; i < a.length; ++i)
			N *= m[i];
		for (int i = 0; i < a.length; ++i)
			c += a[i] * mod_inv(N / m[i], m[i]) * N / m[i];
		return mod(c, N);
	}
}
