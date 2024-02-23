package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandValidatorTest {

	CommandValidator commandValidator;
	DepositCommandValidator depositCommandValidator;
	Bank bank;
	CheckingAccount checkingAccount;
	SavingsAccount SavingsAccount;
	CertificateOfDepositAccount certificateOfDepositAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
		depositCommandValidator = new DepositCommandValidator(bank);
		checkingAccount = new CheckingAccount("12345678", 2);
		SavingsAccount = new SavingsAccount("00001234", 4);
		certificateOfDepositAccount = new CertificateOfDepositAccount("10000001", 3, 5000);
	}

	@Test
	public void deposit_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678 500");
		assertTrue(actual);
	}

	@Test
	public void deposit_into_empty_bank() {
		boolean actual = depositCommandValidator.validate("deposit 12345678 500");
		assertFalse(actual);
	}

	@Test
	public void deposit_into_invalid_checking_account_id() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345677 500");
		assertFalse(actual);
	}

	@Test
	public void deposit_negative_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678 -1");
		assertFalse(actual);
	}

	@Test
	public void deposit_zero_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678 0");
		assertTrue(actual);
	}

	@Test
	public void deposit_above_lower_boundary_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678 1");
		assertTrue(actual);
	}

	@Test
	public void deposit_higher_boundary_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678 1000");
		assertTrue(actual);
	}

	@Test
	public void deposit_under_higher_boundary_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678 999");
		assertTrue(actual);
	}

	@Test
	public void deposit_above_higher_boundary_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678 1001");
		assertFalse(actual);
	}

	@Test
	public void deposit_blank_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678");
		assertFalse(actual);
	}

	@Test
	public void deposit_gap_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = depositCommandValidator.validate("deposit 12345678   600");
		assertFalse(actual);
	}

	@Test
	public void deposit_into_valid_cd_account() {
		bank.addAccount(certificateOfDepositAccount);
		boolean actual = depositCommandValidator.validate("deposit 10000001 300");
		assertFalse(actual);
	}

	@Test
	public void deposit_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 500");
		assertTrue(actual);
	}

	@Test
	public void deposit_zero_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 0");
		assertTrue(actual);
	}

	@Test
	public void deposit_under_lower_boundary_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 -1");
		assertFalse(actual);
	}

	@Test
	public void deposit_above_lower_boundary_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 1");
		assertTrue(actual);
	}

	@Test
	public void deposit_under_higher_boundary_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 2499");
		assertTrue(actual);
	}

	@Test
	public void deposit_above_higher_boundary_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 2501");
		assertFalse(actual);
	}

	@Test
	public void deposit_higher_boundary_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 2500");
		assertTrue(actual);
	}

	@Test
	public void deposit_with_no_id_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 200");
		assertFalse(actual);
	}

	@Test
	public void deposit_with_misspelled_deposit_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("depsit 00001234 200");
		assertFalse(actual);
	}

	@Test
	public void deposit_with_too_many_arguments_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 200 348");
		assertFalse(actual);
	}

	@Test
	public void deposit_with_string_id_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit fishwish 200");
		assertFalse(actual);
	}

	@Test
	public void deposit_with_string_amount_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 bill");
		assertFalse(actual);
	}

	@Test
	public void deposit_with_special_id_deposit_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit !@#$%^&* 200");
		assertFalse(actual);
	}

	@Test
	public void deposit_with_special_amount_deposit_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("deposit 00001234 !@#");
		assertFalse(actual);
	}

	@Test
	public void random_string() {
		bank.addAccount(SavingsAccount);
		boolean actual = depositCommandValidator.validate("meep");
		assertFalse(actual);
	}

}
