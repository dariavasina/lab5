package commands;

import collection.StudyGroupCollection;

public class CountByStudentsCountCommand extends Command{
    public CountByStudentsCountCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        getCollection().countByStudentsCount(getStudentsCount());
    }
}
