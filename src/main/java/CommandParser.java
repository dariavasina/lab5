import collection.StudyGroupCollection;
import data.StudyGroup;
import exceptions.*;
import file.FileManager;
import reader.StudyGroupReader;

import java.util.*;

public class CommandParser {
    private final StudyGroupCollection collection;
    private final String filename;
    private FileManager fileManager;


    public CommandParser (StudyGroupCollection collection, FileManager fileManager) {
        this.collection = collection;
        this.filename = System.getenv("save_filename");
        this.fileManager = fileManager;
    }

    public static Long readKey(String input) throws InvalidInputException {
        long key;
        if (input.split(" ").length != 2) {
            throw new InvalidInputException("Please enter a key for the element\n");
        }
        else {
            String s = input.split(" ")[1];
            try {
                return Long.parseLong(s);
            }
            catch (NumberFormatException e) {
                throw new InvalidInputException("Please enter a valid key\n");
            }
        }
    }

    public Long readId(String input) throws InvalidInputException, IdDoesNotExistException {
        long id;
        if (input.split(" ").length != 2) {
            throw new InvalidInputException("Please enter an id for the element\n");
        }
        else {
            String s = input.split(" ")[1];
            try {
                id = Long.parseLong(s);
            }
            catch (NumberFormatException e) {
                throw new InvalidInputException("Please enter a valid id\n");
            }
            if (collection.idInCollection(id)) {
                return id;
            } else {
                throw new IdDoesNotExistException("This id does not exist, please try again\n");
            }
        }
    }

    public static Integer readInteger(String input, String valueName) throws InvalidInputException {
        if (input.split(" ").length != 2) {
            throw new InvalidInputException("Please enter the value of " + valueName + "\n");
        }
        else {
            String s = input.split(" ")[1];
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(valueName + " is a number, please try to enter again\n");
            }
        }
    }

    public void readCommand(Scanner scanner) throws CommandDoesNotExistException, InvalidInputException, KeyDoesNotExistException, KeyAlreadyExistsException {
        String input = scanner.nextLine();
        List<String> commands = Arrays.asList("help", "info", "show", "insert", "update", "remove_key",
                "clear", "save", "execute_script", "exit", "replace_if_greater", "replace_if_lower", "remove_greater_key",
                "count_by_students_count", "filter_by_should_be_expelled", "print_field_descending_students_count");

        List<String> commandsWithKey = Arrays.asList("insert", "replace_if_greater", "replace_if_lower", "remove_key",
                                        "remove_greater_key");

        List<String> commandsWithStudyGroup = Arrays.asList("insert", "replace_if_greater", "replace_if_lower");

        List<String> commandsWithKeyInCollection = Arrays.asList("remove_key", "replace_if_greater", "replace_if_lower");

        input = input.trim().replaceAll(" +", " ");
        String commandName = input.split(" ")[0];
        CommandExecutor commandExecutor = new CommandExecutor(collection, commandName);

        StudyGroupReader sgr = new StudyGroupReader();

        boolean flag = false;

        if (commandsWithKey.contains(commandName)) {
            Long key = readKey(input);
            commandExecutor.setKey(key);

            if (commandsWithKeyInCollection.contains(commandName)) {
                if (!collection.containsKey(key)) {
                    throw new KeyDoesNotExistException(key.toString());
                }
            }
            else if (commandName.equals("insert")) {
                if (collection.containsKey(key)) {
                    throw new KeyAlreadyExistsException(key.toString());
                }
            }
        }

        if (commandsWithStudyGroup.contains(commandName)) {
            StudyGroup studyGroup = sgr.readStudyGroup(scanner);
            commandExecutor.setValue(studyGroup);
        }

        if (commandName.equals("update")) {
            try {
                Long id = readId(input);
                StudyGroup newStudyGroup = sgr.readStudyGroup(scanner);
                commandExecutor.setId(id);
                commandExecutor.setValue(newStudyGroup);
            } catch (IdDoesNotExistException e) {
                System.out.print(e.getMessage());
                flag = true;
            };
        }

        if (commandName.equals("count_by_students_count")) {
            try {
                Integer studentsCount = readInteger(input, "studentsCount");
                commandExecutor.setStudentsCount(studentsCount);
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
                flag = true;
            }
        }

        if (commandName.equals("filter_by_should_be_expelled")) {
            try {
                Integer shouldBeExpelled = readInteger(input, "shouldBeExpelled");
                commandExecutor.setShouldBeExpelled(shouldBeExpelled);
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
                flag = true;
            }
        }

        if (commandName.equals("save")) {
            commandExecutor.setFileManager(fileManager);
        }

        if (commandName.equals("exit")) {
            commandExecutor.setScanner(scanner);
        }

        if (!commands.contains(commandName)) {
            throw new CommandDoesNotExistException(commandName);
        }
        else if (!flag) {
            commandExecutor.execute();
        }

        if (commandName.equals("save")) {
            commandExecutor.setFileManager(fileManager);
        }
    }
}
