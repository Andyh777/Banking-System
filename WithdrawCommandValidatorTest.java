package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandValidatorTest {
	CommandValidator commandValidator;
	WithdrawCommandValidator withdrawCommandValidator;
	Bank bank;
	CheckingAccount checkingAccount;
	SavingsAccount SavingsAccount;
	CertificateOfDepositAccount certificateOfDepositAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
		withdrawCommandValidator = new WithdrawCommandValidator(bank);
		checkingAccount = new CheckingAccount("12345678", 2);
		SavingsAccount = new SavingsAccount("00001234", 4);
		certificateOfDepositAccount = new CertificateOfDepositAccount("10000001", 3, 500);
	}

	@Test
	public void withdraw_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 12345678 200");
		assertTrue(actual);
	}

	@Test
	public void withdraw_into_valid_saving_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 300");
		assertTrue(actual);
	}

	@Test
	public void withdraw_into_invalid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 22345678 200");
		assertFalse(actual);
	}

	@Test
	public void withdraw_into_invalid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 122345678 200");
		assertFalse(actual);
	}

	@Test
	public void withdraw_with_too_many_arguments_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 200 fish");
		assertFalse(actual);
	}

	@Test
	public void withdraw_with_too_little_arguments_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234");
		assertFalse(actual);
	}

	@Test
	public void withdraw_negative_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 -1");
		assertFalse(actual);
	}

	@Test
	public void withdraw_negative_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 12345678 -1");
		assertFalse(actual);
	}

	@Test
	public void withdraw_zero_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 0");
		assertTrue(actual);
	}

	@Test
	public void withdraw_zero_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 12345678 0");
		assertTrue(actual);
	}

	@Test
	public void withdraw_under_upper_boundary_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 999");
		assertTrue(actual);
	}

	@Test
	public void withdraw_under_upper_boundary_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 12345678 399");
		assertTrue(actual);
	}

	@Test
	public void withdraw_upper_boundary_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 1000");
		assertTrue(actual);
	}

	@Test
	public void withdraw_upper_boundary_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 12345678 400");
		assertTrue(actual);
	}

	@Test
	public void withdraw_over_upper_boundary_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 1001");
		assertFalse(actual);
	}

	@Test
	public void withdraw_over_upper_boundary_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 12345678 401");
		assertFalse(actual);
	}

	@Test
	public void withdraw_string_id_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw fishwish 0");
		assertFalse(actual);
	}

	@Test
	public void withdraw_into_empty_bank() {
		boolean actual = withdrawCommandValidator.validate("deposit 12345678 500");
		assertFalse(actual);
	}

	@Test
	public void withdraw_gap_into_valid_checking_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 12345678   200");
		assertFalse(actual);
	}

	@Test
	public void withdraw_with_misspelled_withdraw_into_valid_Savings_account() {
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdrw 00001234 200");
		assertFalse(actual);
	}

	@Test
	public void withdraw_string_amount_Savings_account() {
		bank.addAccount(checkingAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 12345678 foo");
		assertFalse(actual);
	}

	@Test
	public void withdraw_into_invalid_saving_account_id() {
		SavingsAccount = new SavingsAccount("00001234", 4);
		bank.addAccount(SavingsAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 000012345 300");
		assertFalse(actual);
	}

	@Test
	public void withdraw_into_cd_account_invalid_id() {
		certificateOfDepositAccount = new CertificateOfDepositAccount("00001234", 4, 500);
		bank.addAccount(certificateOfDepositAccount);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 300");
		assertFalse(actual);
	}

	@Test
	public void withdraw_into_cd_account_valid_amount_valid_age() {
		certificateOfDepositAccount = new CertificateOfDepositAccount("00001234", 4, 500);
		bank.addAccount(certificateOfDepositAccount);
		bank.passTime(13);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 600");
		assertTrue(actual);
	}

	@Test
	public void withdraw_into_cd_account_valid_amount_valid_age_too_long_id() {
		certificateOfDepositAccount = new CertificateOfDepositAccount("00001234", 4, 500);
		bank.addAccount(certificateOfDepositAccount);
		bank.passTime(13);
		boolean actual = withdrawCommandValidator.validate("withdraw 000012345 600");
		assertFalse(actual);
	}

	@Test
	public void withdraw_into_cd_account_valid_amount_exact_age_id() {
		certificateOfDepositAccount = new CertificateOfDepositAccount("00001234", 4, 500);
		bank.addAccount(certificateOfDepositAccount);
		bank.passTime(12);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 600");
		assertTrue(actual);
	}

	@Test
	public void withdraw_into_cd_account_valid_amount_invalid_age() {
		certificateOfDepositAccount = new CertificateOfDepositAccount("00001234", 4, 500);
		bank.addAccount(certificateOfDepositAccount);
		bank.passTime(11);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 600");
		assertFalse(actual);
	}

	@Test
	public void withdraw_into_cd_account_invalid_amount_valid_age() {
		certificateOfDepositAccount = new CertificateOfDepositAccount("00001234", 4, 500);
		bank.addAccount(certificateOfDepositAccount);
		bank.passTime(13);
		boolean actual = withdrawCommandValidator.validate("withdraw 00001234 100");
		assertFalse(actual);
	}

}
