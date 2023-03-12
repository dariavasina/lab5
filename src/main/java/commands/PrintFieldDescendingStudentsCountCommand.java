package commands;

import collection.StudyGroupCollection;

public class PrintFieldDescendingStudentsCountCommand extends Command{
    public PrintFieldDescendingStudentsCountCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().printFieldDescendingStudentsCount();
    }
}
