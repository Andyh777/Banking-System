package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	Bank bank;
	CheckingAccount checkingAccount;
	SavingsAccount SavingsAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
		checkingAccount = new CheckingAccount("12345678", 2);
		SavingsAccount = new SavingsAccount("00001234", 4);
	}

	@Test
	void random_string_input() {
		boolean actual = commandValidator.validate("foobar");
		assertFalse(actual);
	}

	@Test
	void create_checking_input() {
		boolean actual = commandValidator.validate("create checking 12345678 1");
		assertTrue(actual);
	}

	@Test
	void create_Savings_input() {
		boolean actual = commandValidator.validate("create Savings 12345678 2");
		assertTrue(actual);
	}

	@Test
	void deposit_checking_input() {
		bank.addAccount(checkingAccount);
		boolean actual = commandValidator.validate("deposit 12345678 100");
		assertTrue(actual);
	}

	@Test
	void withdraw_checking_input() {
		bank.addAccount(checkingAccount);
		boolean actual = commandValidator.validate("withdraw 12345678 100");
		assertTrue(actual);
	}

	@Test
	void withdraw_checking_incorrect_input() {
		bank.addAccount(checkingAccount);
		boolean actual = commandValidator.validate("withdraw 12345678 -100");
		assertFalse(actual);
	}

	@Test
	void transfer_checking_to_Savings_input() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = commandValidator.validate("transfer 12345678 00001234 100");
		assertTrue(actual);
	}

	@Test
	void transfer_checking_to_Savings_invalid_input() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = commandValidator.validate("transfer 12345678 00001234 -100");
		assertFalse(actual);
	}

	@Test
	void pass_time_input() {
		boolean actual = commandValidator.validate("pass 10");
		assertTrue(actual);
	}

	@Test
	void pass_time_invalid_input() {
		boolean actual = commandValidator.validate("pass 66");
		assertFalse(actual);
	}
}
