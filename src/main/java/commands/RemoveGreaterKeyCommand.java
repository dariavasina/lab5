package commands;

import collection.StudyGroupCollection;
import data.StudyGroup;
import exceptions.KeyDoesNotExistException;

import java.util.Map;

public class RemoveGreaterKeyCommand extends Command{
    public RemoveGreaterKeyCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().removeGreaterKey(getKey());
    }
}
