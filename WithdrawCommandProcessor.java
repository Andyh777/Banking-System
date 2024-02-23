package banking;

public class WithdrawCommandProcessor {

	private Bank bank;

	WithdrawCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] commandArguments = command.split(" ");
		String ID = commandArguments[1];
		double amountToWithdraw = Double.parseDouble(commandArguments[2]);
		bank.getAccounts().get(ID).withdraw(amountToWithdraw);
		if (bank.getAccounts().get(ID).accountType.equalsIgnoreCase("Savings")) {
			bank.getAccounts().get(ID).changeWithdrawalStatus();
		}
	}
}
