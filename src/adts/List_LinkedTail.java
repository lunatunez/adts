package adts;

/**
 *
 * @author lunatic007
 * @param <T>
 */
public class List_LinkedTail<T> implements ListInterface<T> {
// revised  class private data fields. 

    private Node firstNode; 		// head reference to first node 
    private Node lastNode; 		// tail reference to last node 
    private int numberOfEntries; 	// number of entries in list

    public List_LinkedTail() {
        clear();
    } // end default constructor

    // note the final method
    @Override
    public final void clear() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    } // end clear

    /**
     *
     * @param newEntry
     */
    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.setNextNode(newNode);
        }
        lastNode = newNode;
        numberOfEntries++;
    } // end add

    /**
     *
     * @param newPosition
     * @param newEntry
     * @return
     */
    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);
            if (isEmpty()) {
                firstNode = newNode;
                lastNode = newNode;
            } else if (newPosition == 1) {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else if (newPosition == numberOfEntries + 1) {
                lastNode.setNextNode(newNode);
                lastNode = newNode;
            } else {
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            } // end if 
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    } // end add

    /**
     *
     * @return
     */
    @Override
    public T[] toArray() {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }
        return result;
    }

    /**
     *
     * @param givenPosition
     * @return
     */
    @Override
    public T remove(int givenPosition) {
        T result = null;
        // return value 
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            if (givenPosition == 1) {
                // case 1: remove first entry {
                result = firstNode.getData();
                // save entry to be removed 
                firstNode = firstNode.getNextNode();
                if (numberOfEntries == 1) {
                    lastNode = null;
                }
                // solitary entry was removed 
            } else {
                // case 2: not first entry  
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
                result = nodeToRemove.getData();
                // save entry to be removed 
                if (givenPosition == numberOfEntries) {
                    lastNode = nodeBefore;
                }
                // last node was removed 
            } // end if 
            numberOfEntries--;
        } // end if 
        return result;
	// return removed entry, or 
        // null if operation fails 
    } // end remove

    /**
     *
     * @param givenPosition
     * @param newEntry
     * @return
     */
    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            Node desiredNode = getNodeAt(givenPosition);
            desiredNode.setData(newEntry);
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    /**
     *
     * @param givenPosition
     * @return
     */
    @Override
    public T getEntry(int givenPosition) {
        // result to return
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            result = getNodeAt(givenPosition).getData();
        }
        return result;
    }

    /**
     *
     * @param anEntry
     * @return
     */
    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }
        return found;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        boolean result;

        if (numberOfEntries == 0) // or getLength() == 0
        {
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        }

        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public int getLength() {
        return numberOfEntries;
    }

    // Returns a reference to the node at a given position.
    // Precondition: List is not empty;
    //               1 <= givenPosition <= numberOfEntries	
    private Node getNodeAt(int givenPosition) {
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;

        // traverse the list to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.getNextNode();
        }

        assert currentNode != null;

        return currentNode;
    }

    /**
     *
     */
    private class Node {

        private T data;         // entry in bag
        private Node next;      // link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }
}
