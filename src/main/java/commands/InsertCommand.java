package commands;
import collection.Collection;
import collection.StudyGroupCollection;
import data.StudyGroup;
import exceptions.KeyAlreadyExistsException;
import exceptions.KeyDoesNotExistException;

import java.util.Map;

public class InsertCommand extends Command {
    public InsertCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        try {
            getCollection().insert(getKey(), getValue());
            System.out.println("Element with key " + getKey().toString() + " added to collection");
        }
        catch (KeyAlreadyExistsException e) {
            //System.out.print(e.getMessage());
        }

    }
}
