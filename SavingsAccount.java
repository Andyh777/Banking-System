package banking;

public class SavingsAccount extends BankAccount {

	private boolean alreadyWithdrawnThisMonth = false;

	public SavingsAccount(String ID, double APR) {
		super(ID, APR, 0);
		super.accountType = "Savings";
	}

	@Override
	public void changeWithdrawalStatus() {
		alreadyWithdrawnThisMonth = !alreadyWithdrawnThisMonth;
	}

	@Override
	public void setWithdrawalStatus() {
		alreadyWithdrawnThisMonth = false;
	}

	public boolean getWithdrawalStatus() {
		return alreadyWithdrawnThisMonth;
	}

}
