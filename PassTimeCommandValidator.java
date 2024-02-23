package banking;

public class PassTimeCommandValidator {
	private Bank bank;

	public PassTimeCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] commandArray = command.split(" ");
		if (commandArray[0].equalsIgnoreCase("pass")) {
			return (correctNumberOfArgumentsForWithdraw(commandArray) && validAge(commandArray[1]));
		}
		return false;
	}

	private int intArgument(String age) {
		try {
			return Integer.parseInt(age);
		} catch (NumberFormatException e) {
			return 0;
		}

	}

	private boolean validAge(String age) {
		int months = intArgument(age);
		return (months >= 1 && months <= 60);
	}

	private boolean correctNumberOfArgumentsForWithdraw(String[] commandArray) {
		return commandArray.length == 2;
	}
}