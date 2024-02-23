package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {
	public static final String SAVINGS_ID = "10000000";
	public static final int SAVINGS_APR = 1;
	SavingsAccount SavingsAccount;

	@BeforeEach
	public void setUp() {
		SavingsAccount = new SavingsAccount(SAVINGS_ID, SAVINGS_APR);
	}

	@Test
	public void Savings_account_created_with_0_balance() {
		double actual = SavingsAccount.getBalance();

		assertEquals(0, actual);
	}
}