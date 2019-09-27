package singleton;

// This pattern is confusing, because this class doesn't indicate anyway that is indeed a singleton
// So, you can be making instances without being aware of that they are always shared
class ChiefExecutiveOfficer {
	private static String name;
	private static int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		ChiefExecutiveOfficer.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		ChiefExecutiveOfficer.age = age;
	}

	@Override
	public String toString() {
		return "ChiefExecutiveOfficer [name=" + name + ", age=" + age + "]";
	}

}

class MonostateSingletonDemo
{
	public static void main(String[] args)
			throws Exception
	{
		ChiefExecutiveOfficer ceo = new ChiefExecutiveOfficer();
		ceo.setName("Adam Smith");
		ceo.setAge(55);
		
		ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
		System.out.println(ceo2);
	}
}
