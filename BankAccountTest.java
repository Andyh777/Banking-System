package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
	public static final String CHECKING_ACCOUNT_ID = "10000000";
	public static final String SAVINGS_ACCOUNT_ID = "10000001";
	public static final String CERTIFICATE_OF_DEPOSIT_ID = "10000002";
	public static final int CERTIFICATE_OF_DEPOSIT_APR = 0;
	public static final int CHECKING_APR = 1;
	public static final int SAVINGS_APR = 2;
	CheckingAccount checkingAccount;
	SavingsAccount SavingsAccount;
	CertificateOfDepositAccount certificateOfDepositAccount;
	private int certificateOfDepositStartBalance;

	@BeforeEach
	public void setUp() {
		checkingAccount = new CheckingAccount(CHECKING_ACCOUNT_ID, CHECKING_APR);
		SavingsAccount = new SavingsAccount(SAVINGS_ACCOUNT_ID, SAVINGS_APR);
		certificateOfDepositStartBalance = 500;
		certificateOfDepositAccount = new CertificateOfDepositAccount(CERTIFICATE_OF_DEPOSIT_ID,
				CERTIFICATE_OF_DEPOSIT_APR, certificateOfDepositStartBalance);
	}

	@Test
	public void accounts_has_set_apr() {
		double checkingAccountAPR = checkingAccount.getAPR();

		assertEquals(1, checkingAccountAPR);
	}

	@Test
	public void accounts_can_be_deposited() {
		SavingsAccount.deposit(500);
		double SavingsBalance = SavingsAccount.getBalance();

		assertEquals(500, SavingsBalance);
	}

	@Test
	public void accounts_can_be_withdrawn_from() {
		certificateOfDepositAccount.withdraw(200);
		double certificateOfDepositAccountBalance = certificateOfDepositAccount.getBalance();

		assertEquals(300, certificateOfDepositAccountBalance);
	}

	@Test
	public void accounts_can_not_be_withdrawn_to_negative() {
		checkingAccount.withdraw(500);
		double checkingBalance = checkingAccount.getBalance();

		assertEquals(0, checkingBalance);
	}

	@Test
	public void account_can_be_deposited_twice() {
		checkingAccount.deposit(100);
		double checkingBalanceAfterFirstDeposit = checkingAccount.getBalance();

		assertEquals(100, checkingBalanceAfterFirstDeposit);

		checkingAccount.deposit(200);
		double checkingBalanceAfterSecondDeposit = checkingAccount.getBalance();

		assertEquals(300, checkingBalanceAfterSecondDeposit);
	}

	@Test
	public void account_can_be_withdrawn_twice() {
		certificateOfDepositAccount.withdraw(50);
		double certificateOfDepositAfterFirstDeposit = certificateOfDepositAccount.getBalance();

		assertEquals(450, certificateOfDepositAfterFirstDeposit);

		certificateOfDepositAccount.withdraw(50);
		double certificateOfDepositAfterSecondDeposit = certificateOfDepositAccount.getBalance();

		assertEquals(400, certificateOfDepositAfterSecondDeposit);
	}
}
