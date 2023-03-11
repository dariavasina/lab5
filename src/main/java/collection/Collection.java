package collection;

import data.StudyGroup;
import exceptions.KeyDoesNotExistException;
import exceptions.KeyAlreadyExistsException;
import reader.ConfirmationReader;


import java.util.LinkedHashMap;
import java.util.Scanner;

public abstract class Collection<K, V> {

    private LinkedHashMap<K, V> collection;

    public LinkedHashMap<K, V> getMap() {
        collection.keySet().forEach(key -> {
            System.out.println(key);
            System.out.println(collection.get(key));
        });
        return collection;
    }

    public Collection() {
        this.collection = new LinkedHashMap<K, V>();
    }

    //public abstract K getKey(V value);

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

    public void clear() {
        collection.clear();
        System.out.print("The collection has been successfully cleared");
    }

    public abstract void updateByID(K key, V value) throws KeyDoesNotExistException;

    public abstract void removeByKey(K key) throws KeyDoesNotExistException;

    public abstract void show();

    public abstract void replaceValueByKey(K key, V value) throws KeyDoesNotExistException;
}
