package collection;

import data.StudyGroup;
import exceptions.KeyDoesNotExistException;
import reader.ConfirmationReader;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Scanner;

public abstract class Collection<K, V> {

    private LinkedHashMap<K, V> collection;

    public LinkedHashMap<K, V> getCollection() {
        return collection;
    }

    public Collection() {
        this.collection = new LinkedHashMap<K, V>();
    }

    public abstract K getKey(V value);

    public boolean containsKey(K key) {
        return collection.containsKey(key);
    }


    public int size() {
        return collection.size();
    }

    public void insert(K key, V value) throws KeyAlreadyExistsException {
        if (!collection.containsKey(key)) {
            collection.put(key, value);
        }
        else {
            throw new KeyAlreadyExistsException(key.toString());
        }
    }


    public void clear(Scanner scanner) {
        boolean answer = ConfirmationReader.checkTheDesireToClear(scanner);
        if (answer) {
            collection.clear();
            System.out.print("The collection has been successfully cleared");
        }

    }

    public abstract void updateByID(K key, V value);

    public abstract void removeByKey(K key);



}
