package commands;

import collection.StudyGroupCollection;
import data.StudyGroup;
import exceptions.KeyDoesNotExistException;

public class UpdateCommand extends Command {
    private Long key;
    private StudyGroup value;

    public void setKey(Long key) {
        this.key = key;
    }

    public void setValue(StudyGroup value) {
        this.value = value;
    }


    public UpdateCommand(StudyGroupCollection collection) {
        super(collection);
    }
    @Override
    public void execute() {
        try {
            getCollection().updateByID(key, value);
        } catch (KeyDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }


}
