package flyweight;

class Sentence {
	String[] words;
	WordToken[] tokens;

	public Sentence(String plainText) {
		this.words = plainText.split(" ");
		this.tokens = new WordToken[this.words.length];
		for (int i = 0; i < this.words.length; ++i) {
			tokens[i] = new WordToken();
		}
	}

	public WordToken getWord(int index) {
		return tokens[index];
	}

	@Override
	public String toString() {
		int limit = words.length;
		String output = "";
		String word;
		for (int i = 0; i < limit; ++i) {
			word = tokens[i].capitalize ?
					words[i].toUpperCase() :
					words[i];
					
			output += (i != 0 ? " " : "") +word;
		}

		return output;
	}

	class WordToken {
		public boolean capitalize;
	}
}

class DecoratorExerciseDemo {
	public static void main(String[] args) throws Exception {
		Sentence sentence = new Sentence("hello world");
		sentence.getWord(1).capitalize = true;

		System.out.println(sentence); // writes "hello WORLD"
	}
}
