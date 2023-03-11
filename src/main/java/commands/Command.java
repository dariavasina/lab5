package commands;

import collection.StudyGroupCollection;
import data.StudyGroup;

public abstract class Command {
    private Long key;
    private StudyGroup value;
    private Integer studentsCount;
    private Integer shouldBeExpelled;

    public StudyGroupCollection getCollection() {
        return collection;
    }

    public void setCollection(StudyGroupCollection collection) {
        this.collection = collection;
    }

    private StudyGroupCollection collection;

    public Command() {};

    public Command(StudyGroupCollection collection) {
        this.collection = collection;
    }



    public abstract void execute();

    public void setKey(Long key) {
        this.key = key;
    }

    public void setValue(StudyGroup value) {
        this.value = value;
    }

    public void setStudentsCount(Integer field) {
        this.studentsCount = field;
    }

    public Integer getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    public Long getKey() {
        return key;
    }

    public StudyGroup getValue() {
        return value;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }
}
