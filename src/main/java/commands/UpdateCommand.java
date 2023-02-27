package commands;

import collection.StudyGroupCollection;

public class UpdateCommand extends Command {

    private int key;
    private int value

    public UpdateCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        System.out.println("Update collection");
    }
}
