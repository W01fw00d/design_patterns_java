package singleton_pattern;

// This example avoids the problem with synchronization - it's thread safe
class InnerStaticSingleton {
	private InnerStaticSingleton()
	{
		
	}
	
	private static class Impl
	{
		private static final InnerStaticSingleton
			INSTANCE = new InnerStaticSingleton();
	}
	
	public InnerStaticSingleton getInstance()
	{
		return Impl.INSTANCE;
	}
}
