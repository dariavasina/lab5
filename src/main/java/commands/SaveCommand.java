package commands;

import collection.StudyGroupCollection;
import file.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveCommand extends Command{
    public SaveCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        try {
            getFileManager().saveToJson(getCollection());
        } catch (IOException e) {
            System.out.println("Please provide an existing filename");
        }

    }
}
