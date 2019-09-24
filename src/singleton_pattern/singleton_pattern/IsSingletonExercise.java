package singleton_pattern;

import java.util.function.Supplier;

class SingletonTester
{
  public static boolean isSingleton(Supplier<Object> func)
  {
	  Object first_instance = func.get();
	  Object second_instance = func.get();
	  
	  return first_instance.equals(second_instance);
  }
}

class NotASingleton
{
	public int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public NotASingleton() {
	}
}

class ExerciseDemo
{
	public static void main(String[] args) throws Exception
	{
		System.out.println(SingletonTester.isSingleton(() -> BasicSingleton.getInstance()));
		System.out.println(SingletonTester.isSingleton(() -> new NotASingleton()));
	}
}