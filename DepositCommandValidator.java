package banking;

public class DepositCommandValidator {
	private Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		try {
			String[] commandArray = command.split(" ");
			String accountType = bank.getAccounts().get(commandArray[1]).getAccountType();
			if (commandArray[0].equalsIgnoreCase("deposit")) {
				if (accountType.equals("Savings")) {
					return validateSavingsDeposit(command);
				} else if (accountType.equals("Checking")) {
					return validateCheckingDeposit(command);
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private boolean validateCheckingDeposit(String command) {
		String[] commandArray = command.split(" ");
		return (correctNumberOfArgumentsForDeposit(commandArray) && hasValidID(commandArray[1])
				&& hasValidCheckingDepositAmount(commandArray[2]) && hasExistingAccount(commandArray[1]));
	}

	private boolean hasValidCheckingDepositAmount(String depositAmount) {
		try {
			double parseDeposit = Double.parseDouble(depositAmount);
			return (parseDeposit >= 0) && (parseDeposit <= 1000);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean validateSavingsDeposit(String command) {
		String[] commandArray = command.split(" ");
		return (correctNumberOfArgumentsForDeposit(commandArray) && hasValidID(commandArray[1])
				&& hasValidSavingsDepositAmount(commandArray[2]) && hasExistingAccount(commandArray[1]));
	}

	private boolean hasExistingAccount(String ID) {
		try {
			String existingID = bank.getAccounts().get(ID).getID();
			return (existingID.equals(ID));
		} catch (NullPointerException e) {
			return true;
		}
	}

	private boolean hasValidSavingsDepositAmount(String depositAmount) {
		try {
			double parseDeposit = Double.parseDouble(depositAmount);
			return (parseDeposit >= 0) && (parseDeposit <= 2500);
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

	private boolean correctNumberOfArgumentsForDeposit(String[] commandArray) {
		return commandArray.length == 3;
	}
}
