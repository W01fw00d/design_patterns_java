package expression_processor;

import java.util.HashMap;
import java.util.Map;

// This implementation is too long and confuse: it can be improved applying the interpreter pattern
class ExpressionProcessor {
	public Map<Character, Integer> variables = new HashMap<>();

	public ExpressionProcessor() {
		super();
	}

	public ExpressionProcessor(Map<Character, Integer> variables) {
		super();
		this.variables = variables;
	}

	public int calculate(String expression) {
		try {
			return this.evaluateExpression(expression);
		} catch (Exception e) {
			System.out.println("Variable/s where not correctly added to the expression");
			return 0;
		}
	}

	private int evaluateExpression(String expression) throws Exception {
		int result = 0;
		String number = "";

		for (int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);

			switch (character) {
			case '+':
				result += this.getNumericValue(number);
				number = "";
				break;
			case '-':
				result += this.getNumericValue(number);
				number = "-";
				break;
			default:
				number += character;
				break;
			}
		}

		result += this.getNumericValue(number);

		return result;
	}

	private int getNumericValue(String number) throws Exception {
		if (Character.isLetter(number.charAt(0)) || (number.length() == 2 && Character.isLetter(number.charAt(1)))) {
			return this.getVariableNumericValue(number);
		}

		return Integer.parseInt(number);
	}

	private int getVariableNumericValue(String number) throws Exception {
		int result = 0;

		if (number.length() == 1 && this.variables.containsKey(number.charAt(0))) {
			result = this.variables.get(number.charAt(0));
		} else if (number.length() == 2 && number.charAt(0) == '-') {
			result = -1 * this.variables.get(number.charAt(1));
		} else {
			throw new Exception();
		}

		return result;
	}
}

class ExpressionProcessorDemo {
	public static void main(String[] args) throws Exception {
		ExpressionProcessor processor = new ExpressionProcessor();
		ExpressionProcessor processor_with_var = new ExpressionProcessor(Map.of('x', 3));

		System.out.println(processor.calculate("1+2+3"));
		System.out.println(processor.calculate("1+2+xy"));
		System.out.println(processor_with_var.calculate("10-2-x"));
	}
}
