package banking;

public class PassTimeCommandProcessor {
	private Bank bank;

	PassTimeCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] commandArguments = command.split(" ");
		int months = Integer.parseInt(commandArguments[1]);
		bank.passTime(months);
	}
}
