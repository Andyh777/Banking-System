package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferCommandValidatorTest {
	CommandValidator commandValidator;
	Bank bank;
	CheckingAccount checkingAccount;
	SavingsAccount SavingsAccount;
	CertificateOfDepositAccount certificateOfDepositAccount;
	TransferCommandValidator transferCommandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
		transferCommandValidator = new TransferCommandValidator(bank);
		checkingAccount = new CheckingAccount("12345678", 2);
		SavingsAccount = new SavingsAccount("00001234", 4);
		certificateOfDepositAccount = new CertificateOfDepositAccount("10000001", 3, 500);
	}

	@Test
	public void transfer_from_checking_to_Savings() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		bank.deposit("12345678", 500);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234 100");
		assertTrue(actual);
	}

	@Test
	public void transfer_from_Savings_to_checking() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		bank.deposit("00001234", 500);
		boolean actual = transferCommandValidator.validate("transfer 00001234 12345678 100");
		assertTrue(actual);
	}

	@Test
	public void empty_command() {
		boolean actual = transferCommandValidator.validate("");
		assertFalse(actual);
	}

	@Test
	public void transfer_only_command() {
		boolean actual = transferCommandValidator.validate("transfer");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_one_id() {
		bank.addAccount(checkingAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_no_amount() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234 ");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_string_amount() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234 fish");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_no_other_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 123");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_gap() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234   123");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_unknown_to_id() {
		bank.addAccount(checkingAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001232 123");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_misspelled_transfer() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("trasnfer 12345678 00001234 123");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_unknown_from_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345278 00001234 123");
		assertFalse(actual);
	}

	@Test
	public void transfer_with_unknown_ids() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345278 00001134 123");
		assertFalse(actual);
	}

	@Test
	public void transfer_Savings_negative_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 00001234 12345278 -1");
		assertFalse(actual);
	}

	@Test
	public void transfer_checking_negative_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234 -1");
		assertFalse(actual);
	}

	@Test
	public void transfer_Savings_zero_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 00001234 12345678 0");
		assertTrue(actual);
	}

	@Test
	public void transfer_checking_zero_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234 0");
		assertTrue(actual);
	}

	@Test
	public void transfer_Savings_under_upper_bound_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 00001234 12345678 999");
		assertTrue(actual);
	}

	@Test
	public void transfer_checking_under_upper_bound_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234 399");
		assertTrue(actual);
	}

	@Test
	public void transfer_Savings_upper_boundary_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 00001234 12345678 1000");
		assertTrue(actual);
	}

	@Test
	public void transfer_checking_upper_boundary_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234 400");
		assertTrue(actual);
	}

	@Test
	public void transfer_Savings_above_upper_boundary_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 00001234 12345678 1001");
		assertFalse(actual);
	}

	@Test
	public void transfer_checking_above_upper_boundary_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 00001234 401");
		assertFalse(actual);
	}

	@Test
	public void transfer_same_account() {
		bank.addAccount(checkingAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 12345678 401");
		assertFalse(actual);
	}

	@Test
	public void transfer_checking_to_cd() {
		bank.addAccount(checkingAccount);
		bank.addAccount(certificateOfDepositAccount);
		boolean actual = transferCommandValidator.validate("transfer 12345678 10000001 100");
		assertFalse(actual);
	}

	@Test
	public void transfer_cd_to_checking() {
		bank.addAccount(checkingAccount);
		bank.addAccount(certificateOfDepositAccount);
		boolean actual = transferCommandValidator.validate("transfer 10000001 12345678 100");
		assertFalse(actual);
	}

}
