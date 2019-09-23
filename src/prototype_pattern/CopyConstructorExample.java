package prototype_pattern;

class Address2
{
	public String streetAddress, city, country;

	@Override
	public String toString() {
		return "Address [streetAddress=" + streetAddress + ", city=" + city + ", country=" + country + "]";
	}

	public Address2(String streetAddress, String city, String country) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.country = country;
	}
	
	public Address2(Address2 other)
	{
		this(other.streetAddress, other.city, other.country);
	}
}

class Employee
{
	public String name;
	public Address2 address;

	@Override
	public String toString() {
		return "Employee [name=" + name + ", address=" + address + "]";
	}

	public Employee(String name, Address2 address) {
		this.name = name;
		this.address = address;
	}

	public Employee(Employee other) {
		name = other.name;
		address = other.address;
	}
	
}

class CopyConstructorExampleDemo
{
	public static void main(String[] args) throws Exception
	{
		Employee john = new Employee(
				"John",
				new Address2("London Road", "London", "UK")
		);
		
		Employee jane = new Employee(john);
		jane.name = "Jane";
		
		System.out.println(john);
		System.out.println(jane);
	}
}
