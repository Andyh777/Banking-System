package banking;

public abstract class BankAccount {
	public String accountType;
	private String ID;
	private double APR;
	private double Balance;
	private int age = 0;

	public BankAccount(String ID, double APR, double Balance) {
		this.ID = ID;
		this.APR = APR;
		this.Balance = Balance;
	}

	public String getID() {
		return ID;
	}

	public double getAPR() {
		return APR;
	}

	public double getBalance() {
		return Balance;
	}

	public void deposit(double amountToDeposit) {
		Balance += amountToDeposit;
	}

	public void withdraw(double amountToWithdraw) {
		if (Balance < amountToWithdraw) {
			Balance = 0;
		} else {
			Balance -= amountToWithdraw;
		}
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String type) {
		this.accountType = type;
	}

	public void setWithdrawalStatus() {
	}

	public void passTimeAndAPR(int months) {

		if (accountType.equals("Cd")) {
			while (age < months) {
				Balance += ((APR / 100) / 12) * Balance;
				Balance += ((APR / 100) / 12) * Balance;
				Balance += ((APR / 100) / 12) * Balance;
				Balance += ((APR / 100) / 12) * Balance;
				age += 1;
			}
		} else {
			while (age < months) {
				Balance += ((APR / 100) / 12) * Balance;
				age += 1;
			}
		}
	}

	public int getAge() {
		return this.age;
	}

	public void changeWithdrawalStatus() {
	}
}
