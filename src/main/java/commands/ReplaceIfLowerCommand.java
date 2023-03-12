package commands;

import collection.StudyGroupCollection;
import data.StudyGroup;
import exceptions.KeyDoesNotExistException;

import java.util.Map;
import java.util.Objects;

public class ReplaceIfLowerCommand extends Command{
    public ReplaceIfLowerCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().replaceIfLower(getKey(), getValue());
    }
}
