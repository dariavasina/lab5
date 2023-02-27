package commands;

import collection.StudyGroupCollection;
import exceptions.KeyDoesNotExistException;

public class RemoveKeyCommand extends Command{
    private final Long key;
    public RemoveKeyCommand(StudyGroupCollection collection, Long key) {
        super(collection);
        this.key = key;
    }

    @Override
    public void execute() {
        try {
            getCollection().removeByKey(key);
        }
        catch (KeyDoesNotExistException e) {
            System.out.print(e.getMessage());
        }
    }
}
