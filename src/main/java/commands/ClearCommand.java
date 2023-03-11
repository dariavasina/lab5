package commands;

import collection.StudyGroupCollection;


public class ClearCommand extends Command{
    public ClearCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().clear();
    }
}
