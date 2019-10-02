package state_machine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

enum Status {
	LOCKED, OPEN, ERROR
}

class CombinationLock {
	private int[] combination;
	public String status;

	public CombinationLock(int[] combination) {
		this.combination = combination;
		this.status = Status.LOCKED.toString();
	}

	public void enterDigit(int digit) {
		System.out.println(digit + " inputed!");

		if (status.equals(Status.LOCKED.toString())) {
			this.status = this.checkCombination(digit, 0, "");
		} else {
			// Check next position on the combination
			this.status = this.checkCombination(digit, status.length(), status);

			if (this.status.length() == this.combination.length) {
				this.status = Status.OPEN.toString();
			}
		}
	}

	private String checkCombination(int digit, int index, String current_status) {
		return digit == combination[index] ? current_status + Integer.toString(digit) : Status.ERROR.toString();
	}
}

public class StateMachineExercise {

	@Test
	public void exampleTest() {
		CombinationLock lock = new CombinationLock(new int[] { 1, 2, 3, 4 });
		assertEquals("LOCKED", lock.status);
		lock.enterDigit(1);
		assertEquals("1", lock.status);
		lock.enterDigit(2);		
		assertEquals("12", lock.status);
		lock.enterDigit(3);
		assertEquals("123", lock.status);
		lock.enterDigit(4);
		assertEquals("OPEN", lock.status);
	}
}
