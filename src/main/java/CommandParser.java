import collection.StudyGroupCollection;
import commands.*;
import data.Person;
import data.StudyGroup;
import exceptions.CommandDoesNotExistException;
import exceptions.InvalidInputException;
import reader.PersonReader;
import reader.StudyGroupReader;

import java.util.*;

public class CommandParser {
    private final StudyGroupCollection collection;
    private final String filename;


    public CommandParser (StudyGroupCollection collection){
        this.collection = collection;
        this.filename = System.getenv("save_filename");
    }

    public static Long readKey(String input) throws InvalidInputException {
        long key;
        if (input.split(" ").length != 2) {
            throw new InvalidInputException("Please enter a key for the element");
        }
        else {
            String s = input.split(" ")[1];
            try {
                return Long.parseLong(s);
            }
            catch (NumberFormatException e) {
                throw new InvalidInputException("Please enter a valid key");
            }
        }
    }

    public static Integer checkStudentsCount(String input) throws InvalidInputException {
        if (input.split(" ").length != 2) {
            throw new InvalidInputException("Please enter the value of studentsCount");
        }
        else {
            String s = input.split(" ")[1];
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Students count is a number, please try to enter again");
            }
        }
    }

    public void getCommand(Scanner scanner) throws CommandDoesNotExistException {
        String input = scanner.nextLine();
        List<String> commands = Arrays.asList("help", "info", "show", "insert", "update", "remove_key",
                "clear", "save", "execute_script", "exit", "replace_if_greater", "replace_if_lower", "remove_greater_key",
                "count_by_students_count", "filter_by_should_be_expelled", "print_field_descending_students_count");

        List<String> commandsWithKey = Arrays.asList("insert", "replace_if_greater", "replace_if_lower", "remove_key",
                                        "remove_greater_key");

        String commandName = input.split(" ")[0];
        CommandExecutor commandExecutor = new CommandExecutor(collection, commandName);

        StudyGroupReader sgr = new StudyGroupReader();

        if (commandsWithKey.contains(commandName)) {
            try {
                Long key = readKey(input);
                if (!commandName.equals("remove_key") && !commandName.equals("remove_greater_key")) {
                    StudyGroup studyGroup = sgr.readStudyGroup(scanner);
                    commandExecutor.setKey(key);
                    commandExecutor.setValue(studyGroup);
                }
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }

        if (commandName.contains("students_count")) {
            try {
                Integer studentsCount = checkStudentsCount(input);
                commandExecutor.setStudentsCount(studentsCount);
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }

        if (!commands.contains(commandName)) {
            throw new CommandDoesNotExistException(commandName);
        }
        else {
            commandExecutor.execute();
        }
    }
}
