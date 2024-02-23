package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
	public static final String CHECKING_ID = "10000000";
	public static final String SAVINGS_ID = "10000001";
	public static final int CHECKING_APR = 2;
	public static final int SAVINGS_APR = 1;
	public static final String CERTIFICATE_OF_DEPOSIT_ID = "10000003";
	public static final int CERTIFICATE_OF_DEPOSIT_APR = 0;
	public static final int CERTIFICATE_OF_DEPOSIT_START_BALANCE = 600;
	Bank bank;
	CheckingAccount checkingAccount;
	SavingsAccount SavingsAccount;
	CertificateOfDepositAccount certificateOfDepositAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		checkingAccount = new CheckingAccount(CHECKING_ID, CHECKING_APR);
		SavingsAccount = new SavingsAccount(SAVINGS_ID, SAVINGS_APR);
		certificateOfDepositAccount = new CertificateOfDepositAccount(CERTIFICATE_OF_DEPOSIT_ID,
				CERTIFICATE_OF_DEPOSIT_APR, CERTIFICATE_OF_DEPOSIT_START_BALANCE);
	}

	@Test
	void bank_has_no_accounts_initially() {
		assertTrue(bank.getAccounts().isEmpty());
	}

	@Test
	void add_one_account_to_bank() {
		bank.addAccount(checkingAccount);
		assertEquals(CHECKING_ID, bank.getAccounts().get(CHECKING_ID).getID());
	}

	@Test
	void add_two_accounts_to_bank() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		assertEquals(CHECKING_ID, bank.getAccounts().get(CHECKING_ID).getID());
		assertEquals(SAVINGS_ID, bank.getAccounts().get(SAVINGS_ID).getID());
	}

	@Test
	void retrieve_account_from_bank() {
		bank.addAccount(checkingAccount);
		bank.addAccount(SavingsAccount);
		assertEquals(CHECKING_ID, bank.getAccounts().get(CHECKING_ID).getID());
		assertEquals(CHECKING_APR, bank.getAccounts().get(CHECKING_ID).getAPR());
	}

	@Test
	void deposit_to_account_through_bank() {
		bank.addAccount(checkingAccount);
		bank.deposit(CHECKING_ID, 500);

		assertEquals(500, bank.getAccounts().get(CHECKING_ID).getBalance());
	}

	@Test
	void deposit_twice_to_account_through_bank() {
		bank.addAccount(checkingAccount);
		bank.deposit(CHECKING_ID, 500);

		assertEquals(500, bank.getAccounts().get(CHECKING_ID).getBalance());

		bank.deposit(CHECKING_ID, 500);

		assertEquals(1000, bank.getAccounts().get(CHECKING_ID).getBalance());
	}

	@Test
	void withdraw_from_account_from_bank() {
		bank.addAccount(certificateOfDepositAccount);
		bank.withdraw(CERTIFICATE_OF_DEPOSIT_ID, 100);

		assertEquals(500, bank.getAccounts().get(CERTIFICATE_OF_DEPOSIT_ID).getBalance());
	}

	@Test
	void withdraw_twice_from_account_from_bank() {
		bank.addAccount(certificateOfDepositAccount);
		bank.withdraw(CERTIFICATE_OF_DEPOSIT_ID, 100);

		assertEquals(500, bank.getAccounts().get(CERTIFICATE_OF_DEPOSIT_ID).getBalance());

		bank.withdraw(CERTIFICATE_OF_DEPOSIT_ID, 100);

		assertEquals(400, bank.getAccounts().get(CERTIFICATE_OF_DEPOSIT_ID).getBalance());
	}

	@Test
	void transfer_between_two_accounts() {
		bank.addAccount(checkingAccount);
		bank.deposit(CHECKING_ID, 500);
		bank.addAccount(SavingsAccount);
		bank.transfer(CHECKING_ID, SAVINGS_ID, 100);

		assertEquals(100, bank.getAccounts().get(SAVINGS_ID).getBalance());
	}

	@Test
	void transfer_between_two_accounts_with_too_big_amount() {
		bank.addAccount(checkingAccount);
		bank.deposit(CHECKING_ID, 100);
		bank.addAccount(SavingsAccount);
		bank.transfer(CHECKING_ID, SAVINGS_ID, 200);

		assertEquals(100, bank.getAccounts().get(SAVINGS_ID).getBalance());
	}

}
