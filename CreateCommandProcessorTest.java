package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandProcessorTest {
	private CommandProcessor processor;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		processor = new CommandProcessor(bank);
	}

	@Test
	void open_checking_account() {
		String command = ("create checking 12345678 2");
		processor.process(command);
		String[] commandArguments = command.split(" ");
		String ID = commandArguments[2];
		double APR = Double.parseDouble(commandArguments[3]);
		assertEquals(ID, bank.getAccounts().get(commandArguments[2]).getID());
		assertEquals(APR, bank.getAccounts().get(commandArguments[2]).getAPR());
	}

	@Test
	void open_Savings_account() {
		String command = ("create Savings 12345678 2");
		processor.process(command);
		String[] commandArguments = command.split(" ");
		String ID = commandArguments[2];
		double APR = Double.parseDouble(commandArguments[3]);
		assertEquals(ID, bank.getAccounts().get(commandArguments[2]).getID());
		assertEquals(APR, bank.getAccounts().get(commandArguments[2]).getAPR());
	}

	@Test
	void open_cd_account() {
		String command = ("create CD 12345678 2 1000");
		processor.process(command);
		String[] commandArguments = command.split(" ");
		String ID = commandArguments[2];
		double APR = Double.parseDouble(commandArguments[3]);
		double Balance = Double.parseDouble(commandArguments[4]);
		assertEquals(ID, bank.getAccounts().get(commandArguments[2]).getID());
		assertEquals(APR, bank.getAccounts().get(commandArguments[2]).getAPR());
		assertEquals(Balance, bank.getAccounts().get(commandArguments[2]).getBalance());
	}
}
