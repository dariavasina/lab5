package commands;

import collection.StudyGroupCollection;

public abstract class Command {
    public StudyGroupCollection getCollection() {
        return collection;
    }

    public void setCollection(StudyGroupCollection collection) {
        this.collection = collection;
    }

    private StudyGroupCollection collection;

    public Command(StudyGroupCollection collection) {
        this.collection = collection;
    }

    public abstract void execute();
}
