package Math;
class Fraction implements Comparable<Fraction>
{
	long num, den;

	public Fraction(long n)
	{
		this(n, 1);
	}

	public Fraction(long n, long d)
	{
		num = n;
		den = d;
		simplify();
	}

	public Fraction reciprocal()
	{
		return new Fraction(den, num);
	}

	public Fraction multiply(Fraction f)
	{
		return new Fraction(num * f.num, den * f.den);
	}

	public Fraction multiply(long l)
	{
		return new Fraction(num * l, den);
	}

	public Fraction divide(Fraction f)
	{
		return new Fraction(num * f.den, den * f.num);
	}

	public Fraction divide(long l)
	{
		return new Fraction(num, den * l);
	}

	public Fraction add(Fraction f)
	{
		long gcd1 = GCD(den, f.num);
		long gcd2 = GCD(num, f.den);
		den /= gcd1;
		f.num /= gcd1;
		num /= gcd2;
		f.den /= gcd2;
		return new Fraction(num * f.den + den * f.num, den * f.den);
	}

	public Fraction subtract(Fraction f)
	{
		long gcd1 = GCD(den, f.num);
		long gcd2 = GCD(num, f.den);
		den /= gcd1;
		f.num /= gcd1;
		num /= gcd2;
		f.den /= gcd2;
		return new Fraction(num * f.den + den * f.num, den * f.den);
	}

	private void simplify()
	{
		if (den == 0)
			divby0();
		long gcd = GCD(num, den);
		num /= gcd;
		den /= gcd;
		if (den < 0)
		{
			den = -den;
			num = -num;
		}
	}

	private void divby0()
	{
		throw new ArithmeticException("Cannot divide by 0!");
	}

	private static long GCD(long num2, long den2)
	{
		return (den2 == 0) ? num2 : GCD(den2, num2 % den2);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || !(o instanceof Fraction))
			return false;
		Fraction f = (Fraction) o;
		return num == f.num && den == f.den;
	}

	@Override
	public int hashCode()
	{
		return (int) (num ^ (num >>> 32)) * 31 + (int) (den ^ (den >>> 32));
	}

	@Override
	public int compareTo(Fraction f)
	{
		if (num == f.num && den == f.den)
			return 0;
		double dif = (double) num / den - (double) f.num / f.den;
		if (dif < 0)
			return -1;
		return 1;
	}

	@Override
	public String toString()
	{
		return num + "/" + den;
	}
}
