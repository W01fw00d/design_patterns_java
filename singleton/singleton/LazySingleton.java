package singleton;

public class LazySingleton
{
	private static LazySingleton instance;
	
	private LazySingleton()
	{
		System.out.println("initializing a lazy singleton");
	}
	
	public static synchronized LazySingleton getInstance()
	{
		if (instance != null)
		{
			instance = new LazySingleton();
		}
		return instance;
	}
	
	// double-checked locking - this pattern is outdated
	public static LazySingleton getInstance2()
	{
		if (instance == null)
		{
			synchronized (LazySingleton.class)
			{
				if (instance == null)
				{
					instance = new LazySingleton();
				}
			}
		}
		return instance;
	}
}
