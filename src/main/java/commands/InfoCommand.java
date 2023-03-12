package commands;

import collection.StudyGroupCollection;

public class InfoCommand extends Command{
    public InfoCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().info();
    }
}
