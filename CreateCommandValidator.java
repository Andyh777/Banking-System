package banking;

public class CreateCommandValidator {
	private Bank bank;

	public CreateCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		if (createCheckingAccount(command) || createSavingsAccount(command)) {
			return validateSavingsOrCheckingAccount(command);
		} else if (createCDAccount(command)) {
			return validateCDAccount(command);
		} else {
			return false;
		}
	}

	private boolean validateCDAccount(String command) {
		String[] commandArray = command.split(" ");
		return (correctNumberOfArgumentsForCDAccount(commandArray) && hasValidID(commandArray[2])
				&& hasValidAPR(commandArray[3]) && hasValidBalance(commandArray[4])
				&& noExistingAccount(commandArray[2]));
	}

	private boolean hasValidBalance(String Balance) {
		try {
			double parseBalance = Double.parseDouble(Balance);
			return (parseBalance >= 1000) && (parseBalance <= 10000);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean correctNumberOfArgumentsForCDAccount(String[] commandArray) {
		return (commandArray.length == 5);
	}

	private boolean createCDAccount(String command) {
		try {
			String[] commandArray = command.split(" ");
			return commandArray[1].equalsIgnoreCase("Cd");
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	private boolean createSavingsAccount(String command) {
		try {
			String[] commandArray = command.split(" ");
			return commandArray[1].equalsIgnoreCase("Savings");
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	private boolean validateSavingsOrCheckingAccount(String command) {
		String[] commandArray = command.split(" ");
		try {
			return (correctNumberOfArgumentsForSavingsOrCheckingAccount(commandArray) && hasValidID(commandArray[2])
					&& hasValidAPR(commandArray[3]) && noExistingAccount(commandArray[2]));
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	private boolean noExistingAccount(String ID) {
		try {
			String existingID = bank.getAccounts().get(ID).getID();
			return !(existingID.equals(ID));
		} catch (Exception e) {
			return true;
		}
	}

	private boolean hasValidAPR(String APR) {
		try {
			double parseAPR = Double.parseDouble(APR);
			return (parseAPR >= 0) && (parseAPR <= 10);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean hasValidID(String ID) {
		try {
			Double.parseDouble(ID);
			return (ID.length() == 8);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean correctNumberOfArgumentsForSavingsOrCheckingAccount(String[] commandArray) {
		return (commandArray.length == 4);
	}

	private boolean createCheckingAccount(String command) {
		try {
			String[] commandArray = command.split(" ");
			return commandArray[1].equalsIgnoreCase("Checking");
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
}
