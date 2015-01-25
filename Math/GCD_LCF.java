package Math;

public class GCD_LCF
{

	public static int GCF(int n1, int n2)
	{
		int gcf = 0;
		int rem = 0;
		do
		{
			rem = n1 % n2;
			if (rem == 0)
				gcf = n2;
			else
			{
				n1 = n2;
				n2 = rem;
			}
		} while (rem != 0);
		return gcf;
	}

	public static int LCM(int n1, int n2)
	{
		int a = Math.max(n1, n2);
		int b = Math.min(n1, n2);
		for (int i = a;; i += a)
		{
			if (i % b == 0)
				return i;
		}
	}

}
