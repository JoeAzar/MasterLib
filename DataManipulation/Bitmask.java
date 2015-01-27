class Bitmask
{
	/**
	 * get's the i'th bit of m, from the left, 0-indexed
	 */
	static boolean geti(int m, int i)
	{
		return ((m >>> i) & 1) == 1;
	}

	/**
	 * flips the i'th bit of m, from the left, 0-indexed
	 */
	static int flipi(int m, int i)
	{
		return m ^ (1 << i);
	}

	/**
	 * sets the i'th bit of m to val'
	 */
	static int seti(int m, int i, boolean val)
	{
		return (val) ? m | 1 << i : m & ~(1 << i);
	}

	/**
	 * prints all bits of an integer, including the signed one
	 */
	static String bitprint(int m)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 32; ++i)
		{
			sb.append(m & 1);
			m >>>= 1;
		}
		return sb.reverse().toString();
	}
}
