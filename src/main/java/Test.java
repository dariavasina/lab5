import collection.StudyGroupCollection;
import data.*;
import exceptions.CommandDoesNotExistException;
import exceptions.InvalidInputException;
import exceptions.KeyAlreadyExistsException;
import exceptions.KeyDoesNotExistException;
import file.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        String filename = System.getenv("save_filename");
        FileManager fileManager = new FileManager(filename, "C:\\Users\\Da\\IdeaProjects\\lab5\\src\\main\\java\\out.json");
        try {
            Map<Long, StudyGroup> collection = fileManager.readFromFile();
            StudyGroupCollection sgc = new StudyGroupCollection(collection);

            CommandParser cp = new CommandParser(sgc, fileManager);

            while (true) {
                try {
                    cp.readCommand(scanner);
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                } catch (KeyDoesNotExistException | KeyAlreadyExistsException | CommandDoesNotExistException e) {
                    //
                }
            }
        } catch (IOException e) {
            System.out.println("Please provide an existing filename");
        }



    }

}
