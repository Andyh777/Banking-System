package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandValidatorTest {

	CommandValidator commandValidator;
	Bank bank;
	BankAccount checkingAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
		checkingAccount = new CheckingAccount("12345678", 2);
	}

	@Test
	void random_string_input() {
		boolean actual = commandValidator.validate("foobar");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_valid_id_and_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 1");
		assertTrue(actual);
	}

	@Test
	void create_checking_with_string_id_and_valid_apr() {
		boolean actual = commandValidator.validate("create checking stringti 3");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_valid_id_and_string_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 o");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_no_other_arguments() {
		boolean actual = commandValidator.validate("create checking");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_no_apr() {
		boolean actual = commandValidator.validate("create checking 12345678");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_no_id() {
		boolean actual = commandValidator.validate("create checking 2");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_too_long_id() {
		boolean actual = commandValidator.validate("create checking 123456789 2");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_too_short_id() {
		boolean actual = commandValidator.validate("create checking 1234567 2");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_special_character_id() {
		boolean actual = commandValidator.validate("create checking !@#$%^&* 2");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_special_character_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 $");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_misspelled_create() {
		boolean actual = commandValidator.validate("crete checking 12345678 2");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_misspelled_account() {
		boolean actual = commandValidator.validate("create checkig 12345678 2");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_negative_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 -0.1");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_too_high_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 10.1");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_low_boundary_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 0");
		assertTrue(actual);
	}

	@Test
	void create_checking_with_high_boundary_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 10");
		assertTrue(actual);
	}

	@Test
	void create_checking_with_under_high_boundary_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 9.9");
		assertTrue(actual);
	}

	@Test
	void create_checking_with_above_low_boundary_apr() {
		boolean actual = commandValidator.validate("create checking 12345678 0.1");
		assertTrue(actual);
	}

	@Test
	void create_checking_with_gap_in_command() {
		boolean actual = commandValidator.validate("create  checking 12345678 9.9");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_too_many_arguments() {
		boolean actual = commandValidator.validate("create  checking 12345678 9.9 35");
		assertFalse(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4 5000");
		assertTrue(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_and_too_low_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4 999");
		assertFalse(actual);
	}

	@Test
	void create_cd_with_valid_id_negative_apr_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4 -500");
		assertFalse(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_low_boundary_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4 1000");
		assertTrue(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_above_low_boundary_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4 1001");
		assertTrue(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_above_high_boundary_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4 10001");
		assertFalse(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_high_boundary_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4 10000");
		assertTrue(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_under_high_boundary_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4 9999");
		assertTrue(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_no_balance() {
		boolean actual = commandValidator.validate("create cd 12345678 4");
		assertFalse(actual);
	}

	@Test
	void create_cd_with_valid_id_apr_balance_and_argument() {
		boolean actual = commandValidator.validate("create cd 12345678 4 1500 500");
		assertFalse(actual);
	}

	@Test
	void create_cd_with_uppercase_d() {
		boolean actual = commandValidator.validate("create cD 12345678 4 1500");
		assertTrue(actual);
	}

	@Test
	void create_checking_with_existing_ID() {
		bank.addAccount(checkingAccount);
		boolean actual = commandValidator.validate("create checking 12345678 3");
		assertFalse(actual);
	}

}
