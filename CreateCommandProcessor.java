package banking;

public class CreateCommandProcessor {

	private Bank bank;

	CreateCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] commandArguments = command.split(" ");
		if (commandArguments[1].equalsIgnoreCase("Checking")) {
			double APR = Double.parseDouble(commandArguments[3]);
			CheckingAccount checkingAccount = new CheckingAccount(commandArguments[2], APR);
			bank.addAccount(checkingAccount);
		} else if (commandArguments[1].equalsIgnoreCase("Savings")) {
			double APR = Double.parseDouble(commandArguments[3]);
			SavingsAccount SavingsAccount = new SavingsAccount(commandArguments[2], APR);
			bank.addAccount(SavingsAccount);
		} else if (commandArguments[1].equalsIgnoreCase("Cd")) {
			double APR = Double.parseDouble(commandArguments[3]);
			double Balance = Double.parseDouble(commandArguments[4]);
			CertificateOfDepositAccount certificateOfDepositAccount = new CertificateOfDepositAccount(
					commandArguments[2], APR, Balance);
			bank.addAccount(certificateOfDepositAccount);
		}
	}
}
