package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {

	private final String INVALID_COMMAND = "create checking 00001111 11";
	private final String SECOND_INVALID_COMMAND = "create Savings 1234567 2";
	private CommandStorage commandStorage;

	@BeforeEach
	void setUp() {
		commandStorage = new CommandStorage(new Bank());
	}

	@Test
	void invalid_command_storage_is_created_empty() {
		assertEquals(0, commandStorage.getInvalidCommands().size());
	}

	@Test
	void store_one_invalid_command() {
		commandStorage.storeInvalidCommand(INVALID_COMMAND);
		assertEquals(INVALID_COMMAND, commandStorage.getInvalidCommands().get(0));
	}

	@Test
	void store_two_invalid_commands() {
		commandStorage.storeInvalidCommand(INVALID_COMMAND);
		commandStorage.storeInvalidCommand(SECOND_INVALID_COMMAND);
		assertEquals(INVALID_COMMAND, commandStorage.getInvalidCommands().get(0));
		assertEquals(SECOND_INVALID_COMMAND, commandStorage.getInvalidCommands().get(1));
	}
}
