package banking;

public class CommandProcessor {

	private Bank bank;

	CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] commandArguments = command.split(" ");
		if (commandArguments[0].equalsIgnoreCase("create")) {
			CreateCommandProcessor processor = new CreateCommandProcessor(bank);
			processor.process(command);
		} else if (commandArguments[0].equalsIgnoreCase("deposit")) {
			DepositCommandProcessor processor = new DepositCommandProcessor(bank);
			processor.process(command);
		} else if (commandArguments[0].equalsIgnoreCase("withdraw")) {
			WithdrawCommandProcessor processor = new WithdrawCommandProcessor(bank);
			processor.process(command);
		} else if (commandArguments[0].equalsIgnoreCase("transfer")) {
			TransferCommandProcessor processor = new TransferCommandProcessor(bank);
			processor.process(command);
		} else if (commandArguments[0].equalsIgnoreCase("pass")) {
			PassTimeCommandProcessor processor = new PassTimeCommandProcessor(bank);
			processor.process(command);
		}
	}
}
