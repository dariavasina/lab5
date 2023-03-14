import collection.StudyGroupCollectionManager;
import data.*;
import exceptions.CommandDoesNotExistException;
import exceptions.InvalidInputException;
import exceptions.KeyAlreadyExistsException;
import exceptions.KeyDoesNotExistException;
import file.FileManager;
import reader.CommandParser;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        String filename = System.getenv("save_filename");
        FileManager fileManager = new FileManager(filename, "C:\\Users\\Da\\IdeaProjects\\lab5\\src\\main\\java\\out.json");
        try {
            Map<Long, StudyGroup> collection = fileManager.readFromJson();
            StudyGroupCollectionManager sgc = new StudyGroupCollectionManager(collection);

            CommandParser cp = new CommandParser(sgc, fileManager);

            while (true) {
                try {
                    System.out.print("Enter a command: ");
                    cp.readCommand(cp.getCommandFromConsole(scanner), scanner, false);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                } catch (KeyDoesNotExistException | KeyAlreadyExistsException | CommandDoesNotExistException e) {
                    //
                }
            }
        } catch (IOException e) {
            System.out.println("Please provide an existing filename");
        }
    }
    //todo check before exit, check after each command if the collection was changed
    //todo save file automatically to a tmp file
    //todo something with command args

}
