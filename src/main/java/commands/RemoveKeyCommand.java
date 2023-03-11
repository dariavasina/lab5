package commands;

import collection.StudyGroupCollection;
import exceptions.KeyDoesNotExistException;

public class RemoveKeyCommand extends Command{
    public RemoveKeyCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        try {
            getCollection().removeByKey(getKey());
        }
        catch (KeyDoesNotExistException e) {
            System.out.print(e.getMessage());
        }
    }
}
