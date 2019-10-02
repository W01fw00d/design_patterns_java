package null_object;

import org.junit.Test;

interface Log {
	// max # of elements in the log
	int getRecordLimit();

	// number of elements already in the log
	int getRecordCount();

	// expected to increment record count
	void logInfo(String message);
}

class Account {
	private Log log;

	public Account(Log log) {
		this.log = log;
	}

	public void someOperation() throws Exception {
		int c = log.getRecordCount();
		log.logInfo("Performing an operation");
		if (c + 1 != log.getRecordCount())
			throw new Exception();
		if (log.getRecordCount() >= log.getRecordLimit())
			throw new Exception();
	}
}

class ConsoleLog implements Log {
	private int record_count = 0;
	
	@Override
	public int getRecordLimit() {
		return 10;
	}

	@Override
	public int getRecordCount() {
		return this.record_count;
	}

	@Override
	public void logInfo(String message) {
		record_count++;
		System.out.println(message);
	}
	
}

class NullLog implements Log {
	private int recordCount = Integer.MIN_VALUE;
	
	@Override
	public int getRecordLimit() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int getRecordCount() {
		return this.recordCount;
	}

	@Override
	public void logInfo(String message) {
		++recordCount;
	}
}

class Demo {
	public static void main(String[] args) {
		// Without logging message
		Account account_a = new Account(new NullLog());
			try {
				account_a.someOperation();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		System.out.println("---");
		// With logging message
		Account account_b = new Account(new ConsoleLog());
		try {
			account_b.someOperation();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}


public class NullObjectExercise
{
  @Test
  public void singleCallTest() throws Exception
  {
    Account a = new Account(new NullLog());
    a.someOperation();
  }

  @Test
  public void manyCallsTest() throws Exception
  {
    Account a = new Account(new NullLog());
    for (int i = 0; i < 100; ++i)
      a.someOperation();
  }
}
