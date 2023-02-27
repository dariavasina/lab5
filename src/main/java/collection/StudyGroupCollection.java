package collection;
import data.StudyGroup;
import exceptions.KeyDoesNotExistException;

import java.util.Objects;


public class StudyGroupCollection extends Collection<Long, StudyGroup>{
    public StudyGroupCollection() {
        super();
    }
    @Override
    public Long getKey(StudyGroup group) {
        return group.getId();
    }

    @Override
    public void updateByID(Long id, StudyGroup newStudyGroup) {
        int idx = 0;
        getCollection().keySet().forEach(key -> {
            if (Objects.equals(getCollection().get(key).getId(), id)) {
                newStudyGroup.setId(id);
                getCollection().put(key, newStudyGroup);
                System.out.printf("Element with ID %s successfully updated", id);
                return;
            }
        });
    }

    @Override
    public void removeByKey(Long key) {
        if (containsKey(key)) {
            getCollection().remove(key);
            System.out.printf("Element with key %s has been successfully removed", key);
        }
        else {
            throw new KeyDoesNotExistException(key.toString());
        }
    }
}
