package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingAccountTest {
	public static final String CHECKING_ID = "10000000";
	public static final int CHECKING_APR = 1;
	CheckingAccount checkingAccount;

	@BeforeEach
	public void setUp() {
		checkingAccount = new CheckingAccount(CHECKING_ID, CHECKING_APR);
	}

	@Test
	public void checking_account_created_with_0_balance() {
		double actual = checkingAccount.getBalance();

		assertEquals(0, actual);
	}
}