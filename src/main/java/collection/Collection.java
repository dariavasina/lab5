package collection;

import data.StudyGroup;
import exceptions.KeyDoesNotExistException;
import exceptions.KeyAlreadyExistsException;
import reader.ConfirmationReader;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Collection<K, V> {

    private LinkedHashMap<K, V> collection;
    private java.time.LocalDate initializationDate;

    public LinkedHashMap<K, V> getMap() {
        return collection;
    }

    public Collection() {
        this.collection = new LinkedHashMap<K, V>();
        this.initializationDate = java.time.LocalDate.now();
    }

    public Collection(Map<K, V> collection) {
        this.collection = (LinkedHashMap<K, V>) collection;
        this.initializationDate = java.time.LocalDate.now();
    }

    public java.time.LocalDate getInitializationDate() {
        return initializationDate;
    }

    public boolean containsKey(K key) {
        return collection.containsKey(key);
    }


    public int size() {
        return collection.size();
    }

    public void insert(K key, V value) {
        collection.put(key, value);
    }

    public void clear() {
        collection.clear();
        System.out.println("The collection has been successfully cleared");
    }

    public abstract void updateByID(K key, V value) throws KeyDoesNotExistException;

    public abstract void removeByKey(K key) throws KeyDoesNotExistException;

    public abstract void show();
}
