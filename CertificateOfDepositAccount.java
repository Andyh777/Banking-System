package banking;

public class CertificateOfDepositAccount extends BankAccount {
	public CertificateOfDepositAccount(String ID, double APR, double Balance) {
		super(ID, APR, Balance);
		super.accountType = "Cd";
	}
}
