package collection;
import data.StudyGroup;
import exceptions.KeyDoesNotExistException;

import java.util.Map;
import java.util.Objects;


public class StudyGroupCollection extends Collection<Long, StudyGroup>{
    public StudyGroupCollection() {
        super();
    }

    @Override
    public void updateByID(Long id, StudyGroup newStudyGroup) throws KeyDoesNotExistException {
        boolean idInCollection = false;
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            if (Objects.equals(collection.get(key).getId(), id)) {
                newStudyGroup.setId(id);
                collection.put(key, newStudyGroup);
                System.out.printf("Element with ID %s successfully updated", id);
                idInCollection = true;
            }
        }

        if (!idInCollection){
            throw new KeyDoesNotExistException(id.toString());
        }
    }

    @Override
    public void removeByKey(Long key) throws KeyDoesNotExistException {
        if (containsKey(key)) {
            getMap().remove(key);
            System.out.printf("Element with key %s has been successfully removed", key);
        }
        else {
            throw new KeyDoesNotExistException(key.toString());
        }
    }

    @Override
    public void show() {
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            System.out.println(key + "\n" + collection.get(key));
        }
    }

    public void info() {
        System.out.printf("Number of elements in the collection: %d", getMap().size());
    }

    @Override
    public void replaceValueByKey(Long key, StudyGroup value) throws KeyDoesNotExistException {
        if (getMap().containsKey(key)) {
            getMap().put(key, value);
        }
        else {
            throw new KeyDoesNotExistException(key.toString());
        }

    }

    public void replaceIfLower(Long keyToChange, StudyGroup value) {
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            if (Objects.equals(key, keyToChange)) {
                if (value.compareTo(collection.get(key)) < 0) {
                    try {
                        replaceValueByKey(key, value);
                    }
                    catch (KeyDoesNotExistException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
        }
    }

    public void replaceIfGreater(Long keyToChange, StudyGroup value) {
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            if (Objects.equals(key, keyToChange)) {
                if (value.compareTo(collection.get(key)) > 0) {
                    try {
                        replaceValueByKey(key, value);
                    }
                    catch (KeyDoesNotExistException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
        }
    }

    public int countByStudentsCount(Integer studentsCount) {
        int count = 0;
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            if (Objects.equals(collection.get(key).getStudentsCount(), studentsCount)) {
                count += 1;
            }
        }
        return count;
    }


}
