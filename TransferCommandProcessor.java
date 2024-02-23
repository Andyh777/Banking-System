package banking;

public class TransferCommandProcessor {
	private Bank bank;

	TransferCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] commandArguments = command.split(" ");
		double amountToTransfer = Double.parseDouble(commandArguments[3]);
		bank.transfer(commandArguments[1], commandArguments[2], amountToTransfer);
	}
}
