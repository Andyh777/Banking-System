package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandProcessorTest {
	private CommandProcessor processor;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		processor = new CommandProcessor(bank);
	}

	@Test
	void deposit_into_checking_account() {
		String command = ("create checking 12345678 2");
		processor.process(command);
		String[] commandArguments = command.split(" ");
		String secondCommand = ("deposit 12345678 500");
		processor.process(secondCommand);
		assertEquals(500, bank.getAccounts().get(commandArguments[2]).getBalance());
	}

	@Test
	void deposit_into_cd_account() {
		String command = ("create CD 12345678 2 1000");
		processor.process(command);
		String[] commandArguments = command.split(" ");
		double Balance = Double.parseDouble(commandArguments[4]);
		String secondCommand = ("deposit 12345678 500");
		processor.process(secondCommand);
		assertEquals(Balance + 500, bank.getAccounts().get(commandArguments[2]).getBalance());
	}
}
