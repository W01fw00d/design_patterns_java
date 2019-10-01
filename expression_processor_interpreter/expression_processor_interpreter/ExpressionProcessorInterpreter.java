package expression_processor_interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class RealNumber {
	enum Symbol {
		PLUS, MINUS
	}

	private Symbol symbol;
	private Integer natural_number;

	public RealNumber() {
		this.symbol = Symbol.PLUS;
	}

	public RealNumber(Symbol symbol) {
		this.symbol = symbol;
	}

	public void setNaturalNumber(Integer number) {
		this.natural_number = number;
	}

	public int get() {
		if (this.symbol == Symbol.MINUS) {
			return -1 * this.natural_number;
		}

		return this.natural_number;
	}
}

class ExpressionProcessorInterpreter {
	public Map<Character, Integer> variables = new HashMap<>();

	public ExpressionProcessorInterpreter() {
		super();
	}

	public ExpressionProcessorInterpreter(Map<Character, Integer> variables) {
		super();
		this.variables = variables;
	}

	public int calculate(String expression) {
		try {
			// We first check that if all elements in the expressions can be parsed to integers
			// Then we sum them
			return this.sum(
					this.getRealNumbers(expression)
			);

		} catch (Exception e) {
			System.out.println("Variable/s where not correctly added to the expression");
			return 0;
		}
	}

	// For efficiency, we do both lexing and parsing inside this method iteration 
	private ArrayList<RealNumber> getRealNumbers(String expression) throws Exception {
		ArrayList<RealNumber> real_numbers = new ArrayList<RealNumber>();
		String number = "";
		RealNumber real_number = new RealNumber();

		for (int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);

			switch (character) {
			case '+':
				real_number.setNaturalNumber(this.getNumericValue(number));
				real_numbers.add(real_number);

				real_number = new RealNumber(RealNumber.Symbol.PLUS);
				number = "";
				break;
			case '-':
				real_number.setNaturalNumber(this.getNumericValue(number));
				real_numbers.add(real_number);

				real_number = new RealNumber(RealNumber.Symbol.MINUS);
				number = "";
				break;
			default:
				number += character;
				break;
			}
		}

		real_number.setNaturalNumber(this.getNumericValue(number));
		real_numbers.add(real_number);

		return real_numbers;
	}

	private int getNumericValue(String number) throws Exception {
		if (Character.isLetter(number.charAt(0))) {
			return this.getVariableNumericValue(number);
		}

		return Integer.parseInt(number);
	}

	private int getVariableNumericValue(String number) throws Exception {
		int result = 0;

		if (number.length() == 1 && this.variables.containsKey(number.charAt(0))) {
			result = this.variables.get(number.charAt(0));
		} else {
			throw new Exception();
		}

		return result;
	}
	
	private int sum(ArrayList<RealNumber> numbers) {
		int sum = 0;
		for (RealNumber number : numbers) {
			sum += number.get();
		}
		
		return sum;
	}
}

class ExpressionProcessorDemo {
	public static void main(String[] args) throws Exception {
		ExpressionProcessorInterpreter processor = new ExpressionProcessorInterpreter();
		ExpressionProcessorInterpreter processor_with_var = new ExpressionProcessorInterpreter(Map.of('x', 3));

		System.out.println(processor.calculate("1+2+3"));
		System.out.println(processor.calculate("1+2+xy"));
		System.out.println(processor_with_var.calculate("10-2-x"));
	}
}
