package prototype_pattern;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

class Foo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int stuff;
	public String whatever;

	@Override
	public String toString() {
		return "Foo [stuff=" + stuff + ", whatever=" + whatever + "]";
	}

	public Foo(int stuff, String whatever) {
		super();
		this.stuff = stuff;
		this.whatever = whatever;
	}
	
	
}

class SerializationCopyDemo
{
	public static void main(String[] args) throws Exception
	{
		Foo foo = new Foo(42,"life");
		
		Foo foo2 =  SerializationUtils.roundtrip(foo);
		
		foo2.whatever = "xyz";
		
		System.out.println(foo);
		System.out.println(foo2);
	}
}