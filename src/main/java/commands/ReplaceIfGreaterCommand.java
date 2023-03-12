package commands;

import collection.StudyGroupCollection;
import exceptions.KeyDoesNotExistException;

public class ReplaceIfGreaterCommand extends Command{
    public ReplaceIfGreaterCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().replaceIfGreater(getKey(), getValue());
    }
}
