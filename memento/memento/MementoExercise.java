package memento;

import java.util.ArrayList;

class Token {
	public int value = 0;

	public Token(int value) {
		this.value = value;
	}
}

class Memento {
	public ArrayList<Token> tokens = new ArrayList<>();
}

class TokenMachine {
	public ArrayList<Token> tokens = new ArrayList<>();

	public Memento addToken(int value) {
		tokens.add(new Token(value));
		Memento memento = new Memento();
		// It's important to create a new token list that cannot be modified by an outsider altering the memento list directly
		memento.tokens = new ArrayList<Token>(tokens);

		return memento;
	}

	public Memento addToken(Token token) {
		// It's important to create a new token that cannot be modified by an outsider altering the token value directly
		tokens.add(new Token(token.value));
		Memento memento = new Memento();
		memento.tokens = new ArrayList<Token>(tokens);

		return memento;
	}

	public void revert(Memento memento) {
		tokens = memento.tokens;
	}
}

class Demo {
	public static void main(String[] args) {
		TokenMachine token_machine = new TokenMachine();
		Token token = new Token(111);
		
		Memento memento_a = token_machine.addToken(111);
		System.out.println(memento_a.tokens);
		System.out.println();
		
		Memento memento_b = token_machine.addToken(token);
		System.out.println(memento_b.tokens);
		System.out.println(memento_b.tokens.get(1).value);
		System.out.println();
		
		// Check if the tickets inside TicketMachine are independent from the ones that we enter as input
		token.value = 333;
		System.out.println(memento_b.tokens.get(1).value);
		System.out.println();
		
		// We should be able to go back to the state of memento_a
		token_machine.revert(memento_a);
		System.out.println(memento_a.tokens);
		System.out.println();
	}
}