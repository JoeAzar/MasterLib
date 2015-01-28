class Geometry
{
	/**
	 * Returns the area of a triangle given sides a, b, and c
	 */
	public static double heron(double a, double b, double c)
	{
		double p = (a + b + c) / 2;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}

	/**
	 * returns the area of a triangle given it's coordinates
	 */
	public static double tri_area(double x1, double y1, double x2, double y2, double x3, double y3)
	{
		return Math.abs((x1 - x3) * (y2 - y1) - (x1 - x2) * (y3 - y1)) / 2;
	}

	/**
	 * returns the area of a non-intersecting polygon
	 * can be convex or concave
	 */
	public static double polygon_area(double x[], double y[])
	{
		double a = 0;
		int n = x.length - 1;
		for (int i = 0; i < x.length; ++i)
		{
			a += (x[n] + x[i]) * (y[n] - y[i]);
			n = i;
		}
		return a / 2;
	}

	/**
	 * takes input a1, b1, c1, a2, b2, c2
	 * solves the line intersection problem a1x + b1y = c1 and a2x + b2y = c2
	 * returns in the form {x, y}
	 */
	public static double[] line_intersect(double a1, double b1, double c1, double a2, double b2, double c2)
	{
		double d = a1 * b2 + a2 * b1;
		if (d == 0)
			return new double[] { Double.NaN, Double.NaN };
		return new double[] { (b2 * c1 - b1 * c2) / d, (a1 * c2 - a2 * c1) / d };

	}

	/**
	 * Euclidean distance in an n dimensional space
	 */
	public static double distance(double[] a, double[] b)
	{
		double sum = 0, tmp;
		for (int i = 0; i < a.length; ++i)
		{
			tmp = a[i] - b[i];
			sum += tmp * tmp;
		}
		return Math.sqrt(sum);
	}
}
