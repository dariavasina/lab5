import collection.StudyGroupCollection;
import commands.*;
import data.StudyGroup;
import exceptions.InvalidInputException;
import reader.StudyGroupReader;

import java.util.Scanner;

public class CommandParser {
    private StudyGroupCollection collection;
    private final String filename;

    public CommandParser (StudyGroupCollection collection){
        this.collection = collection;
        this.filename = System.getenv("save_filename");
    }

    public static Long checkKey(String input) throws InvalidInputException {
        long key;
        if (input.split(" ").length != 2) {
            throw new InvalidInputException("Please enter a key for the new element");
        }
        else {
            String s = input.split(" ")[1];
            try {
                key = Long.parseLong(s);
            }
            catch (NumberFormatException e) {
                throw new InvalidInputException("Please enter a valid key");
            }
        }
        return key;
    }

    public Command selectCommand(Scanner scanner) throws InvalidInputException {
        StudyGroupReader sgr = new StudyGroupReader();
        String input = scanner.nextLine();
        Command command;
        String commandName = input.split(" ")[0];
        Long key;
        StudyGroup studyGroup;
        switch (commandName) {
            case "help":
                command = new HelpCommand(collection);
            case "info":
                command = new InfoCommand(collection);
            case "show":
                command = new ShowCommand(collection);
            case "insert":
                key = checkKey(input);
                studyGroup = sgr.readStudyGroup(scanner);
                command = new InsertCommand(collection, key, studyGroup);
            case "update":
                Long id = checkKey(input);
                studyGroup = sgr.readStudyGroup(scanner);
                command = new UpdateCommand(collection, key, studyGroup);
            case "remove_key":
                key = checkKey(input);
                command = new RemoveKeyCommand(collection, key);
            case "clear":
                command = new ClearCommand(collection, scanner);
            case "save":
                command = new SaveCommand(collection);
            case "execute_script":
                command = new ExecuteScriptCommand(collection, fileName);
            case "exit":
                command = new ExitCommand(collection);
            case "replace_if_greater":
                command = new ReplaceIfGreaterCommand(collection);
            case "replace_if_lower":
                command = new ReplaceIfLowerCommand(collection);
            case "remove_greater_key":
                command = new RemoveGreaterKeyCommand(collection);
            case "count_by_students_count":
                command = new CountByStudentsCountCommand(collection);
            case "filter_by_should_be_expelled":
                command = new FilterByShouldBeExpelledCommand(collection);
            default:
                throw new InvalidInputException("The command does not exist, please try again");
        }
    }
}
