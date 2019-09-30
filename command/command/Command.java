package command;

public class Command {
	enum Action {
		DEPOSIT, WITHDRAW
	}

	public Action action;
	public int amount;
	public boolean success;

	public Command(Action action, int amount) {
		this.action = action;
		this.amount = amount;
	}
}

class Account {
	public int balance;

	public void process(Command command) {
		switch (command.action) {
			case DEPOSIT:
				balance += command.amount;
				command.success = true;
				break;
			case WITHDRAW:
				command.success = balance >= command.amount;
				if (command.success) {
					balance -= command.amount;
				}
				break;
		}
	}
}

class CommandDemo {
	public static void main(String[] args) throws Exception {
		Account account= new Account();
		System.out.println(account.balance);
		account.process(new Command(Command.Action.DEPOSIT, 100));

		System.out.println(account.balance);
	}
}
