package banking;

public class DepositCommandProcessor {

	private Bank bank;

	DepositCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] commandArguments = command.split(" ");
		String ID = commandArguments[1];
		double amountToDeposit = Double.parseDouble(commandArguments[2]);
		bank.getAccounts().get(ID).deposit(amountToDeposit);
	}
}
