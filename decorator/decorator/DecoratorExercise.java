package decorator;

class Bird {
	public int age;

	public String fly() {
		return age < 10 ? "flying" : "too old";
	}
}

class Lizard {
	public int age;

	public String crawl() {
		return (age > 1) ? "crawling" : "too young";
	}
}

class Dragon {
	private Bird bird;
	private Lizard lizard;

	public Dragon() {
		this.bird = new Bird();
		this.lizard = new Lizard();
	}

	public void setAge(int age) {
		this.bird.age = age;
		this.lizard.age = age;
	}

	public String fly() {
		return this.bird.fly();
	}

	public String crawl() {
		return this.lizard.crawl();
	}
}

class DecoratorExerciseDemo {
	public static void main(String[] args) throws Exception {
		Dragon dragon = new Dragon();
		dragon.setAge(5);
		
		System.out.println(dragon.fly());
		System.out.println(dragon.crawl());
	}
}
