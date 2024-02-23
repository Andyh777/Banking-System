package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
	private Map<String, BankAccount> accounts;

	Bank() {
		accounts = new HashMap<>();
	}

	public Map<String, BankAccount> getAccounts() {
		return accounts;
	}

	public void addAccount(BankAccount accountToAdd) {
		accounts.put(accountToAdd.getID(), accountToAdd);
	}

	public void deposit(String accountID, double amountToDeposit) {
		accounts.get(accountID).deposit(amountToDeposit);
	}

	public void withdraw(String accountID, double amountToWithdraw) {
		accounts.get(accountID).withdraw(amountToWithdraw);
	}

	public void transfer(String fromAccount, String toAccount, double amountToTransfer) {
		if (amountToTransfer > accounts.get(fromAccount).getBalance()) {
			double oldBalance = accounts.get(fromAccount).getBalance();
			accounts.get(fromAccount).withdraw(amountToTransfer);
			accounts.get(toAccount).deposit(oldBalance);
		} else {
			accounts.get(fromAccount).withdraw(amountToTransfer);
			accounts.get(toAccount).deposit(amountToTransfer);
		}
	}

	public void passTime(int months) {
		List<String> removeAccounts = new ArrayList<>();
		for (String accountID : accounts.keySet()) {
			BankAccount account = accounts.get(accountID);
			if (account.getBalance() == 0) {
				removeAccounts.add(accountID);
			}
			if (account.getBalance() < 100) {
				account.withdraw(25);
			}
			account.passTimeAndAPR(months);
			if (account.getAccountType().equalsIgnoreCase("Savings")) {
				account.setWithdrawalStatus();
			}
		}
		for (String accountID : removeAccounts) {
			accounts.remove(accountID);

		}
	}
}
