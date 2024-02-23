package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeProcessorTest {
	private CommandProcessor processor;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		processor = new CommandProcessor(bank);
	}

	@Test
	void pass_one_month_with_balance_bank() {
		String command = ("create checking 87654321 0");
		processor.process(command);
		processor.process("deposit 87654321 200");
		String thirdCommand = ("pass 1");
		processor.process(thirdCommand);
		assertEquals(1, bank.getAccounts().get("87654321").getAge());
	}

	@Test
	void pass_one_month_under_100_bank() {
		String command = ("create checking 87654321 0");
		processor.process(command);
		processor.process("deposit 87654321 50");
		String thirdCommand = ("pass 1");
		processor.process(thirdCommand);
		assertEquals(25, bank.getAccounts().get("87654321").getBalance());
	}

	@Test
	void pass_one_month_with_no_balance_bank() {
		String command = ("create checking 87654321 0");
		processor.process(command);
		String thirdCommand = ("pass 1");
		processor.process(thirdCommand);
		assertNull(bank.getAccounts().get("87654321"));
	}
}
