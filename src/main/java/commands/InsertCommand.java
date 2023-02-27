package commands;
import collection.Collection;
import collection.StudyGroupCollection;
import data.StudyGroup;
import exceptions.KeyAlreadyExistsException;

import java.util.Map;

public class InsertCommand extends Command {
    private final Long key;
    private StudyGroupCollection collection;
    private final StudyGroup value;


    public InsertCommand(StudyGroupCollection collection, Long key, StudyGroup value) {
        super(collection);
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute() {
        try {
            collection.insert(key, value);
            System.out.println("Element with key " + key.toString() + " added to collection");
        } catch (KeyAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }
}
