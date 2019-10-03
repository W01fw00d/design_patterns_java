package strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

interface DiscriminantStrategy {
	double calculateDiscriminant(double a, double b, double c);
}

// If the result is negative, we just return it
class OrdinaryDiscriminantStrategy implements DiscriminantStrategy {
	@Override
	public double calculateDiscriminant(double a, double b, double c) {
		return Math.pow(b, 2) - 4 * a * c;
	}
}

// If the result is negative, we return NaN
class RealDiscriminantStrategy implements DiscriminantStrategy {
	@Override
	public double calculateDiscriminant(double a, double b, double c) {
		double result = Math.pow(b, 2) - 4 * a * c;

		if (result < 0) {
			return Float.NaN;
		}

		return result;
	}
}

class QuadraticEquationSolver {
	private DiscriminantStrategy strategy;

	public QuadraticEquationSolver(DiscriminantStrategy strategy) {
		this.strategy = strategy;
	}

	public Pair<Complex, Complex> solve(double a, double b, double c) {
		Complex discriminant_root = Complex.sqrt(this.strategy.calculateDiscriminant(a, b, c));

		return new Pair<>(
				new Complex(-b, 0).plus(discriminant_root).divides(new Complex(2, 0)).divides(new Complex(a, 0)),
				new Complex(-b, 0).minus(discriminant_root).divides(new Complex(2, 0)).divides(new Complex(a, 0)));
	}
}

public class StrategyExercise {
	@Test
	public void positiveTestOrdinaryStrategy() {
		DiscriminantStrategy strategy = new OrdinaryDiscriminantStrategy();
		QuadraticEquationSolver solver = new QuadraticEquationSolver(strategy);
		Pair<Complex, Complex> results = solver.solve(1, 10, 16);
		assertEquals(new Complex(-2, 0), results.first);
		assertEquals(new Complex(-8, 0), results.second);
	}

	@Test
	public void positiveTestRealStrategy() {
		DiscriminantStrategy strategy = new RealDiscriminantStrategy();
		QuadraticEquationSolver solver = new QuadraticEquationSolver(strategy);
		Pair<Complex, Complex> results = solver.solve(1, 10, 16);
		assertEquals(new Complex(-2, 0), results.first);
		assertEquals(new Complex(-8, 0), results.second);
	}

	@Test
	public void negativeTestOrdinaryStrategy() {
		OrdinaryDiscriminantStrategy strategy = new OrdinaryDiscriminantStrategy();
		QuadraticEquationSolver solver = new QuadraticEquationSolver(strategy);
		Pair<Complex, Complex> results = solver.solve(1, 4, 5);
		assertEquals(new Complex(-2, 1), results.first);
		assertEquals(new Complex(-2, -1), results.second);
	}

	@Test
	public void negativeTestRealStrategy() {
		DiscriminantStrategy strategy = new RealDiscriminantStrategy();
		QuadraticEquationSolver solver = new QuadraticEquationSolver(strategy);
		Pair<Complex, Complex> results = solver.solve(1, 4, 5);

		assertTrue(results.first.isNaN());
		assertTrue(results.second.isNaN());
	}
}
