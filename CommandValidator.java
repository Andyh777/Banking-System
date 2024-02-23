package banking;

public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		String firstCommandArgument = commandArguments[0];
		if (firstCommandArgument.equalsIgnoreCase("create")) {
			CreateCommandValidator validator = new CreateCommandValidator(bank);
			return validator.validate(command);
		} else if (firstCommandArgument.equalsIgnoreCase("deposit")) {
			DepositCommandValidator validator = new DepositCommandValidator(bank);
			return validator.validate(command);
		} else if (firstCommandArgument.equalsIgnoreCase("withdraw")) {
			WithdrawCommandValidator validator = new WithdrawCommandValidator(bank);
			return validator.validate(command);
		} else if (firstCommandArgument.equalsIgnoreCase("transfer")) {
			TransferCommandValidator validator = new TransferCommandValidator(bank);
			return validator.validate(command);
		} else if (firstCommandArgument.equalsIgnoreCase("pass")) {
			PassTimeCommandValidator validator = new PassTimeCommandValidator(bank);
			return validator.validate(command);
		} else {
			return false;
		}
	}
}
