package commands;

import collection.StudyGroupCollection;

import java.util.Scanner;

public class ClearCommand extends Command{
    private final Scanner scanner;
    public ClearCommand(StudyGroupCollection collection, Scanner scanner) {
        super(collection);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        getCollection().clear(scanner);
    }
}
