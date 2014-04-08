package adts;

import java.util.ArrayList;

/**
 * File:        
 * Description: 
 * @author lunatunez
 * @param <T>
 */

public class List_ArrayList<T> implements ListInterface<T> {
    private ArrayList<T> arrayList;

    // *************************************************************************
    // *** STATIC METHODS ******************************************************

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public T get(int index) {
        return arrayList.get(index);
    }

    @Override
    public boolean contains(T anEntry) {
        return arrayList.contains(anEntry);
    }

    @Override
    public T[] toArray() {
        return (T[]) arrayList.toArray();
    }
    
    // *************************************************************************
    // *** MUTATOR METHODS ******************************************************


    @Override
    public boolean add(T newEntry) {
        return arrayList.add(newEntry);
    }
    
 

    @Override
    public boolean insert(int index, T newEntry) {
        arrayList.add(index, newEntry);
        return true;
    }

    @Override
    public T remove() {
        T temp = arrayList.remove(arrayList.size()-1);
        return temp;
        
    }

    @Override
    public T remove(int index) {
        return arrayList.remove(index);
    }

    @Override
    public boolean replace(int index, T newEntry) {
        if (arrayList.set(index, newEntry) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        arrayList.clear();
    }    
}