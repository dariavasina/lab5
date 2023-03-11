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
        Map<Long, StudyGroup> collection = getCollection().getMap();
        for (Long key : collection.keySet()) {
            if (key > getKey()) {
                try {
                    getCollection().removeByKey(key);
                } catch (KeyDoesNotExistException e) {
                    System.out.print(e.getMessage());
                }
            }
        }
    }
}
