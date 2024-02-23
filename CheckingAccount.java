package banking;

public class CheckingAccount extends BankAccount {

	public CheckingAccount(String ID, double APR) {
		super(ID, APR, 0);
		super.accountType = "Checking";
	}
}
