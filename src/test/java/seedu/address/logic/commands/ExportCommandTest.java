package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ExportCommandTest {

    private Path testOutput = Paths.get("src/test/data/ExportTest/testOutput.json");
    private Path expectedOutput = Paths.get("src/test/data/ExportTest/expectedOutput.json");
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_exportSuccessful() throws IOException {

        String expectedMessage = String.format(ExportCommand.MESSAGE_SUCCESS + " to " + testOutput);
        assertCommandSuccess(new ExportCommand(testOutput), model, expectedMessage, expectedModel);

        List<String> file1 = Files.readAllLines(testOutput);
        List<String> file2 = Files.readAllLines(expectedOutput);

        assertEquals(file1.size(), file2.size());

        for (int i = 0; i < file2.size() - 1; i++) {
            System.out.println("Comparing line: " + i);
            assertEquals(file1.get(i), file2.get(i));
        }
    }
}