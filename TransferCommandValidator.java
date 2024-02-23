package banking;

public class TransferCommandValidator {
	private Bank bank;

	public TransferCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		try {
			String[] commandArray = command.split(" ");
			if (commandArray[0].equalsIgnoreCase("transfer")) {
				return (accountExists(commandArray[1]) && accountExists(commandArray[2])
						&& correctAccountType(commandArray[1]) && correctAccountType(commandArray[2])
						&& followDepositRules(commandArray) && followWithdrawRules(commandArray)
						&& differentAccountID(commandArray));
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private boolean differentAccountID(String[] command) {
		return !(command[1].equals(command[2]));
	}

	private boolean accountExists(String ID) {
		try {
			String existingID = bank.getAccounts().get(ID).getID();
			return (existingID.equals(ID));
		} catch (NullPointerException e) {
			return true;
		}
	}

	private boolean correctAccountType(String ID) {
		try {
			String accountType = bank.getAccounts().get(ID).getAccountType();
			return ((accountType.equals("Savings")) || (accountType.equals("Checking")));
		} catch (NullPointerException e) {
			return false;
		}
	}

	private boolean followWithdrawRules(String[] command) {
		WithdrawCommandValidator validator = new WithdrawCommandValidator(bank);
		String newCommand = ("withdraw" + " " + command[1] + " " + command[3]);
		return validator.validate(newCommand);
	}

	private boolean followDepositRules(String[] command) {
		DepositCommandValidator validator = new DepositCommandValidator(bank);
		String newCommand = ("deposit" + " " + command[2] + " " + command[3]);
		return validator.validate(newCommand);
	}
}
