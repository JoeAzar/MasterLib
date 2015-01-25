package DataStructures;

import java.util.HashMap;
import java.util.Set;

public class TwinMap<A, B>
{
	HashMap<A, B> a = new HashMap<A, B>();
	HashMap<B, A> b = new HashMap<B, A>();

	public void put(A k, B v)
	{
		a.put(k, v);
		b.put(v, k);
	}

	public B get1(A key)
	{
		return a.get(key);
	}

	public A get2(B key)
	{
		return b.get(key);
	}

	public Set<A> set1()
	{
		return a.keySet();
	}

	public Set<B> set2()
	{
		return b.keySet();
	}

	public int size()
	{
		return a.size();
	}

	public boolean contains1(A k)
	{
		return a.containsKey(k);
	}

	public boolean contains2(B k)
	{
		return b.containsKey(k);
	}

	public void remove1(A k)
	{
		b.remove(a.get(k));
		a.remove(k);
	}

	public void remove2(B k)
	{
		a.remove(b.get(k));
		b.remove(k);
	}
}
