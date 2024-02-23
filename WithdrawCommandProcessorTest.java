package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandProcessorTest {
	private CommandProcessor processor;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		processor = new CommandProcessor(bank);
	}

	@Test
	void withdraw_into_checking_account() {
		String command = ("create checking 12345678 2");
		processor.process(command);
		String[] commandArguments = command.split(" ");
		String secondCommand = ("deposit 12345678 500");
		processor.process(secondCommand);
		String thirdCommand = ("withdraw 12345678 200");
		processor.process(thirdCommand);
		assertEquals(300, bank.getAccounts().get(commandArguments[2]).getBalance());
	}
}
