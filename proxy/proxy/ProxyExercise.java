package proxy;

class Person {
	private int age;

	public Person(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String drink() {
		return "drinking";
	}

	public String drive() {
		return "driving";
	}

	public String drinkAndDrive() {
		return "driving while drunk";
	}
}

class ResponsiblePerson {
	String AGE_ADVICE = "too young";

	private Person person;

	public ResponsiblePerson(Person person) {
		this.person = person;
	}

	public int getAge() {
		return person.getAge();
	}

	public void setAge(int age) {
		this.person.setAge(age);
	}

	public String drink() {
		return person.getAge() >= 18 ? this.person.drink() : AGE_ADVICE;
	}

	public String drive() {
		return person.getAge() >= 16 ? this.person.drive() : AGE_ADVICE;
	}

	public String drinkAndDrive() {
		return "dead";
	}
}

class ProxyExerciseDemo {
	public static void main(String[] args) throws Exception {
		Person person = new Person(15);
		ResponsiblePerson responsiblePerson = new ResponsiblePerson(person);

		System.out.println(person.drink());
		System.out.println(responsiblePerson.drink());
	}
}
