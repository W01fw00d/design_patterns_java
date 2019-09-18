package factory_pattern;

class Person
{
  public int id;
  public String name;

  public Person(int id, String name)
  {
    this.id = id;
    this.name = name;
  }
}

class PersonFactory
{
	private int nextId = 0;
	
	public Person createPerson(String name)
	 {
	    Person person = new Person(nextId, name);
	    nextId++;
	    return person;
	 }
}

class FactoryExerciseDemo
{
	public static void main(String[] args) throws Exception
	{
		PersonFactory personFactory = new PersonFactory();
		
		Person person = personFactory.createPerson("Gabriel");
		Person person2 = personFactory.createPerson("Gabriel");
		
		System.out.println(person.id);
		System.out.println(person2.id);
	}
}
