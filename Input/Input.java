import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Input
{
	BufferedReader br;

	public Input()
	{
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public String String() throws IOException
	{
		StringBuilder sb = new StringBuilder();
		boolean started = false;
		while (true)
		{
			char c = (char) br.read();
			if (!Character.isWhitespace(c))
			{
				started = true;
				sb.append(c);
			}
			else if (started)
				return sb.toString();
		}
	}

	public int Int() throws IOException
	{
		return Integer.parseInt(String());
	}

	public int[] Int(int n) throws IOException
	{
		int[] a = new int[n];
		for (int i = 0; i < n; ++i)
			a[i] = Int();
		return a;
	}

	public long Long() throws IOException
	{
		return Long.parseLong(String());
	}

	public long[] Long(int n) throws IOException
	{
		long[] a = new long[n];
		for (int i = 0; i < n; ++i)
			a[i] = Long();
		return a;
	}
}
