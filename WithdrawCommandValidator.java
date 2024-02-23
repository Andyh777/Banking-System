package banking;

public class WithdrawCommandValidator {
	private Bank bank;

	public WithdrawCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		try {
			String[] commandArray = command.split(" ");
			String accountType = bank.getAccounts().get(commandArray[1]).getAccountType();
			if (commandArray[0].equalsIgnoreCase("withdraw")) {
				if (accountType.equals("Savings")) {
					return validateSavingsWithdraw(command);
				} else if (accountType.equals("Checking")) {
					return validateCheckingWithdraw(command);
				} else if (accountType.equals("Cd")) {
					return validateCDWithdraw(command);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private boolean validateCDWithdraw(String command) {
		String[] commandArray = command.split(" ");
		return (correctNumberOfArgumentsForWithdraw(commandArray) && hasValidID(commandArray[1])
				&& hasValidCDWithdrawAmount(commandArray) && hasExistingAccount(commandArray[1])
				&& validAge(commandArray[1]));
	}

	private boolean validAge(String ID) {
		int accountAge = bank.getAccounts().get(ID).getAge();
		return accountAge >= 12;
	}

	private boolean hasValidCDWithdrawAmount(String[] commandArray) {
		Double withdrawAmount = Double.parseDouble(commandArray[2]);
		Double accountBalance = bank.getAccounts().get(commandArray[1]).getBalance();
		return (withdrawAmount >= accountBalance);
	}

	private boolean validateCheckingWithdraw(String command) {
		String[] commandArray = command.split(" ");
		return (correctNumberOfArgumentsForWithdraw(commandArray) && hasValidID(commandArray[1])
				&& hasValidCheckingWithdrawAmount(commandArray[2]) && hasExistingAccount(commandArray[1]));
	}

	private boolean hasValidCheckingWithdrawAmount(String withdrawAmount) {
		try {
			double parseWithdraw = Double.parseDouble(withdrawAmount);
			return (parseWithdraw >= 0) && (parseWithdraw <= 400);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean validateSavingsWithdraw(String command) {
		String[] commandArray = command.split(" ");
		return (correctNumberOfArgumentsForWithdraw(commandArray) && hasValidID(commandArray[1])
				&& hasValidSavingsWithdrawAmount(commandArray[2]) && hasExistingAccount(commandArray[1])
				&& withdrawalStatus(commandArray[1]));
	}

	private boolean withdrawalStatus(String ID) {
		SavingsAccount account = (SavingsAccount) bank.getAccounts().get(ID);
		return !account.getWithdrawalStatus();
	}

	private boolean hasExistingAccount(String ID) {
		try {
			String existingID = bank.getAccounts().get(ID).getID();
			return (existingID.equals(ID));
		} catch (NullPointerException e) {
			return true;
		}
	}

	private boolean hasValidSavingsWithdrawAmount(String withdrawAmount) {
		try {
			double parseWithdraw = Double.parseDouble(withdrawAmount);
			return (parseWithdraw >= 0) && (parseWithdraw <= 1000);
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

	private boolean correctNumberOfArgumentsForWithdraw(String[] commandArray) {
		return commandArray.length == 3;
	}
}
