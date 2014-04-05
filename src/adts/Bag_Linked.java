package adts;public class Bag_Linked<T> implements BagInterface<T> {    private Node firstNode;       // reference to first node    private int numberOfEntries;    public Bag_Linked() {        firstNode = null;        numberOfEntries = 0;    }    /*     * Sees whether this bag is empty.     */    @Override    public boolean isEmpty() {        return numberOfEntries == 0;    }    /*     * Adds a new entry to the beginning of chain:     * OutOfMemoryError possible     */    @Override    public boolean add(T newEntry) {        if (newEntry == null) {            return false;        } else {            Node newNode = new Node(newEntry);            newNode.setNext(firstNode);            // make new node reference rest of chain             // (firstNode is null if chain is empty)                    firstNode = newNode;            // new node is at beginning of chain            numberOfEntries++;            return true;        }    }    /*     * Gets the number of entries currently in this bag.     */    @Override    public int getSize() {        return numberOfEntries;    }    /**     * Sees whether this bag is full. Note: Linked List bag is never full.     *     * @return     */    @Override    public boolean isFull() {        return false;    }    /*     * Removes one unspecified entry     * It is always the node pointed to by the variable firstNode.     */    @Override    public T remove() {        T result = null;        if (firstNode != null) {            result = (T) firstNode.getData();            // result now point to first element on the list            firstNode = firstNode.getNext();            // firstNode points away from the result to move,             // now points to the second item            numberOfEntries--;        }        return result;    }    /**     * Remove one occurrence of an entry. Needs to locate the reference then     * delete. Uses getReferenceTo(), remove()     *     * @param entry     * @return     */    @Override    public boolean remove(T entry) {        Node removeThis = getReferenceTo(entry);        if (removeThis != null) {            // copy the data in the first node over the node we want to remove.            removeThis.setData(firstNode.getData());            // removeThis.setNext(firstNode.getNext());            // we don't disrupt the order of the chain.            // the firstNode is now a duplicate we can simply remove.            remove();                        return true;        }        else {            return false;        }    }    /**     * Locate the reference to a particular item. Set the beginning ref to the     * first node, then traverse with a while loop until we find the value or     * return null.     */    private Node getReferenceTo(T entry) {        boolean found = false;        Node currentNode = firstNode;        // while loop to traverse the list        while (!found && (currentNode != null)) {            if (entry.equals(currentNode.getData())) {                found = true;            } else {                currentNode = currentNode.getNext();            }        }        return currentNode;    }    /*     * Removes all entries from this bag.     */    @Override    public void clear() {        numberOfEntries = 0;        // Why do we set firstNode to null?        firstNode = null;    }    /**     * Counts the number of times a given entry appears in this bag.     *     * @param anEntry the entry to be counted     * @return the number of times anEntry appears in the bag     */    @Override    public int getFrequencyOf(T anEntry) {        int frequency = 0;        int counter = 0;        Node currentNode = firstNode;        while ((counter < numberOfEntries) && (currentNode != null)) {            if (anEntry.equals(currentNode.getData())) {                frequency++;            }            counter++;            currentNode = currentNode.getNext();        }        return frequency;    }    /*     * Tests whether this bag contains a given entry.     */    @Override    public boolean contains(T anEntry) {        if (anEntry == null) {            return false;        }        boolean found = false;        Node currentNode = firstNode;        while (!found && (currentNode != null)) {            if (anEntry.equals(currentNode.getData())) {                found = true;            } else {                currentNode = currentNode.getNext();            }        }        return found;    }    /*     * Retrieves all entries that are in this bag.     */    @Override    public T[] toArray() {        // the cast is safe because the new array contains null entries        @SuppressWarnings("unchecked")        T[] result = (T[]) new Object[numberOfEntries]; // unchecked cast        int index = 0;        Node currentNode = firstNode;        while ((index < numberOfEntries) && (currentNode != null)) {            result[index] = (T) currentNode.getData();            index++;            currentNode = currentNode.getNext();        }        return result;    }}