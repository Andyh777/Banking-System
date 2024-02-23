package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferCommandProcessorTest {
	private CommandProcessor processor;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		processor = new CommandProcessor(bank);
	}

	@Test
	void transfer_between_checking_and_Savings_account() {
		String command = ("create checking 12345678 2");
		processor.process(command);
		String secondCommand = ("deposit 12345678 500");
		processor.process(secondCommand);
		String thirdCommand = ("create Savings 87654321 2");
		processor.process(thirdCommand);
		String fourthCommand = ("transfer 12345678 87654321 200");
		processor.process(fourthCommand);
		assertEquals(200, bank.getAccounts().get("87654321").getBalance());
	}

	@Test
	void transfer_between_Savings_and_checking_account() {
		String command = ("create Savings 87654321 2");
		processor.process(command);
		String secondCommand = ("deposit 87654321 500");
		processor.process(secondCommand);
		String thirdCommand = ("create checking 12345678 2");
		processor.process(thirdCommand);
		String fourthCommand = ("transfer 87654321 12345678 200");
		processor.process(fourthCommand);
		assertEquals(200, bank.getAccounts().get("12345678").getBalance());
	}
}