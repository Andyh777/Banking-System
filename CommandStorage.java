package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandStorage {

	private Bank bank;
	private List<String> invalidCommands;
	private Map<String, List<String>> validCommands = new HashMap<>();

	public CommandStorage(Bank bank) {
		invalidCommands = new ArrayList<>();
		validCommands = new HashMap<>();
		this.bank = bank;
	}

	public void storeValidCommand(String command) {
		String[] commandArguments = command.split(" ");
		if (commandArguments[0].equalsIgnoreCase("create") || commandArguments[0].equalsIgnoreCase("withdraw")
				|| commandArguments[0].equalsIgnoreCase("deposit")) {
			if (validCommands.get(commandArguments[1]) == null) {
				validCommands.put(commandArguments[1], new ArrayList<>());
				validCommands.get(commandArguments[1]).add(command);
			} else {
				validCommands.get(commandArguments[1]).add(command);
			}
		} else if (commandArguments[0].equalsIgnoreCase("transfer")) {
			validCommands.get(commandArguments[1]).add(command);
			validCommands.get(commandArguments[2]).add(command);
		}
	}

	public List<String> getOutput() {
		List<String> output = new ArrayList<>();
		for (String accountID : bank.getAccounts().keySet()) {
			output.add(formattedAccountStatus(accountID));
			if (validCommands.get((accountID)) != null) {
				output.addAll(validCommands.get(accountID));
			}
		}
		output.addAll(invalidCommands);
		return output;
	}

	public String formattedAccountStatus(String accountID) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.FLOOR);
		BankAccount account = bank.getAccounts().get(accountID);
		String newBalance = decimalFormat.format(account.getBalance());
		String newAPR = decimalFormat.format(account.getAPR());
		return (account.getAccountType() + " " + account.getID() + " " + newBalance + " " + newAPR);
	}

	public void storeInvalidCommand(String command) {
		invalidCommands.add(command);
	}

	public List<String> getInvalidCommands() {
		return invalidCommands;
	}
}
