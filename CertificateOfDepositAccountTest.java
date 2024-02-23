package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CertificateOfDepositAccountTest {
	public static final String CERTIFICATE_OF_DEPOSIT_ID = "10000000";
	public static final int CERTIFICATE_OF_DEPOSIT_APR = 1;
	public static final int CERTIFICATE_OF_DEPOSIT_START_BALANCE = 200;
	CertificateOfDepositAccount certificateOfDepositAccount;

	@BeforeEach
	public void setUp() {
		certificateOfDepositAccount = new CertificateOfDepositAccount(CERTIFICATE_OF_DEPOSIT_ID,
				CERTIFICATE_OF_DEPOSIT_APR, CERTIFICATE_OF_DEPOSIT_START_BALANCE);
	}

	@Test
	public void certificate_of_deposit_account_created_with_0_balance() {
		double actual = certificateOfDepositAccount.getBalance();

		assertEquals(CERTIFICATE_OF_DEPOSIT_START_BALANCE, actual);
	}
}