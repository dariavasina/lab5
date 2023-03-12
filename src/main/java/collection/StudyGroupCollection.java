package collection;
import data.StudyGroup;
import exceptions.KeyDoesNotExistException;

import java.util.*;


public class StudyGroupCollection extends Collection<Long, StudyGroup>{
    public StudyGroupCollection(Map<Long, StudyGroup> collection) {
        super(collection);
    }

    @Override
    public void updateByID(Long id, StudyGroup newStudyGroup) throws KeyDoesNotExistException {
        Map<Long, StudyGroup> collection = getMap();
        if (idInCollection(id)) {
            for (Long key : collection.keySet()) {
                if (Objects.equals(collection.get(key).getId(), id)) {
                    newStudyGroup.setId(id);
                    collection.put(key, newStudyGroup);
                    System.out.printf("Element with ID %s successfully updated\n", id);
                }
            }
        }
        else {
            throw new KeyDoesNotExistException(id.toString());
        }

    }

    @Override
    public void removeByKey(Long key) {
        if (containsKey(key)) {
            getMap().remove(key);
            System.out.printf("Element with key %s has been successfully removed\n", key);
        }
    }

    @Override
    public void show() {
        Map<Long, StudyGroup> collection = getMap();
        if (collection.isEmpty()) {
            System.out.println("The collection is empty");
        }
        else {
            for (Long key : collection.keySet()) {
                System.out.println(key + "\n" + collection.get(key));
            }
        }
    }

    public void info() {
        System.out.println("Number of elements in the collection: " + getMap().size());
        System.out.println("Initialization date: " + getInitializationDate());
    }

    public void replaceIfLower(Long keyToChange, StudyGroup value) {
        boolean changed = false;
        Map<Long, StudyGroup> collection = getMap();
        if (collection.containsKey(keyToChange)) {
            for (Long key : collection.keySet()) {
                if (Objects.equals(key, keyToChange)) {
                    if (value.compareTo(collection.get(key)) < 0) {
                        getMap().put(key, value);
                        System.out.println("The value by the key " + key + " has been changed");
                        changed = true;
                    }
                }
            }
        }
        if (!changed) {
            System.out.println("The value by the key " + keyToChange + " was not changed");
        }
    }

    public void replaceIfGreater(Long keyToChange, StudyGroup value) {
        boolean changed = false;
        Map<Long, StudyGroup> collection = getMap();
        if (collection.containsKey(keyToChange)) {
            for (Long key : collection.keySet()) {
                if (Objects.equals(key, keyToChange)) {
                    if (value.compareTo(collection.get(key)) > 0) {
                        getMap().put(key, value);
                        System.out.println("The value by the key " + key + " has been changed");
                        changed = true;
                    }
                }
            }
        }
        if (!changed) {
            System.out.println("The value by the key " + keyToChange + " was not changed");
        }
    }

    public void countByStudentsCount(Integer studentsCount) {
        int count = 0;
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            if (Objects.equals(collection.get(key).getStudentsCount(), studentsCount)) {
                count += 1;
            }
        }
        System.out.println("There are " + count + " study groups in the collection with " + studentsCount + " students");
    }

    public boolean idInCollection(Long id) {
        Map<Long, StudyGroup> collection = getMap();
        for (Long key : collection.keySet()) {
            if (Objects.equals(collection.get(key).getId(), id)) {
                return true;
            }
        }
        return false;
    }

    public void removeGreaterKey(Long key) {
        boolean flag = false;
        Map<Long, StudyGroup> collection = getMap();
        for (Long k : collection.keySet()) {
            if (k > key) {
                removeByKey(k);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("There are no elements in the collection with key greater than " + key);
        }
    }

    public void filterByShouldBeExpelled (Integer shouldBeExpelled) {
        Map<Long, StudyGroup> collection = getMap();
        boolean found = false;
        for (Long key : collection.keySet()) {
            StudyGroup group = collection.get(key);
            if (Objects.equals(group.getShouldBeExpelled(), shouldBeExpelled)) {
                System.out.print(group);
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no elements in the collection with " + shouldBeExpelled + " shouldBeExpelled");
        }
    }

    public void printFieldDescendingStudentsCount () {
        HashMap<Long, StudyGroup> collection = getMap();
        ArrayList<StudyGroup> list = new ArrayList<>();
        for (Map.Entry<Long, StudyGroup> entry : collection.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<StudyGroup>() {
            @Override
            public int compare(StudyGroup g1, StudyGroup g2) {
                return -(g1.compareTo(g2));
            }
        });
        for (StudyGroup g : list) {
            System.out.println(g.getStudentsCount());
        }
    }


}
