package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeValidatorTest {
	CommandValidator commandValidator;
	PassTimeCommandValidator passTimeCommandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
		passTimeCommandValidator = new PassTimeCommandValidator(bank);
	}

	@Test
	public void pass_one() {
		boolean actual = passTimeCommandValidator.validate("pass 1");
		assertTrue(actual);
	}

	@Test
	public void pass_zero() {
		boolean actual = passTimeCommandValidator.validate("pass 0");
		assertFalse(actual);
	}

	@Test
	public void pass_negative() {
		boolean actual = passTimeCommandValidator.validate("pass -1");
		assertFalse(actual);
	}

	@Test
	public void pass_upper_boundary_under() {
		boolean actual = passTimeCommandValidator.validate("pass 59");
		assertTrue(actual);
	}

	@Test
	public void pass_upper_boundary() {
		boolean actual = passTimeCommandValidator.validate("pass 60");
		assertTrue(actual);
	}

	@Test
	public void pass_upper_boundary_over() {
		boolean actual = passTimeCommandValidator.validate("pass 61");
		assertFalse(actual);
	}

	@Test
	public void empty_command() {
		boolean actual = passTimeCommandValidator.validate("");
		assertFalse(actual);
	}

	@Test
	public void misspelled_pass_command() {
		boolean actual = passTimeCommandValidator.validate("ass 1");
		assertFalse(actual);
	}

	@Test
	public void string_months_pass_command() {
		boolean actual = passTimeCommandValidator.validate("pass fish");
		assertFalse(actual);
	}

	@Test
	public void too_many_arguments_pass_command() {
		boolean actual = passTimeCommandValidator.validate("pass 10 hello");
		assertFalse(actual);
	}

}
