package singleton_pattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

enum EnumBasedSingleton {
	INSTANCE;
	
	EnumBasedSingleton()
	{
		value = 42;
	}
	
	// Will not be serialized, because only the name of the INSTANCE is serialized by default on an enum. Also, you cannot inherit from enum.
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

class EnumSingletonDemo
{
	static void saveToFile(EnumBasedSingleton singleton, String filename)
			throws Exception
	{
		try (FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut))
		{
			out.writeObject(singleton);
		}
	}
	
	static EnumBasedSingleton readFromFile(String filename)
		throws Exception
		{
			try (FileInputStream fileIn = new FileInputStream(filename);
					ObjectInputStream in = new ObjectInputStream(fileIn))
			{
				return (EnumBasedSingleton) in.readObject();
			}
		}
	
	public static void main(String[] args)
			throws Exception
	{
		String filename = "myfile.bin";
		
		EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
		singleton.setValue(111);
		saveToFile(singleton, filename);
		
		EnumBasedSingleton singleton2 = readFromFile(filename);
		System.out.println(singleton2.getValue());
	}
}
