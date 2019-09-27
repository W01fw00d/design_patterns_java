package prototype;

import java.util.Arrays;

// Not recommended
class Address implements Cloneable
{
	public String streetName;
	public int houseNumber;
	
	
	public Address(String streetName, int houseNumber) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString() {
		return "Adress [streetName=" + streetName + ", houseNumber=" + houseNumber + "]";
	}
	
	// deep copy (strings are inmutable, so there's no copy by reference inside this clone())
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new Address(streetName, houseNumber);
	}
}

class Person
{
	public String [] names;
	public Address address;
	
	public Person(String[] names, Address address) {
		this.names = names;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [names=" + Arrays.toString(names) + ", address=" + address + "]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return new Person(
				names.clone(),
				(Address) address.clone()
		);
	}
}

class PrototypeExampleDemo
{
	public static void main(String[] args) throws Exception
	{
		Person john = new Person(
				new String[] {"John", "Smith"},
				new Address("London Road", 123)
		);
		
		//Person jane = john; // copy by reference, so it's the same object: shallow copy
		Person jane = (Person) john.clone();
		jane.names[0] = "Jane";
		jane.address.streetName = "Main Street";
		
		System.out.println(john);
		System.out.println(jane);
	}
}