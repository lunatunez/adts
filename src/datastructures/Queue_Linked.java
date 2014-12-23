package datastructures;

import java.util.Arrays;
import java.util.Objects;

/**
 * A dynamic size Queue using a linked chain of Nodes.
 *
 * @author Erik Lunna
 * @param <T>
 */
public class Queue_Linked<T> implements QueueInterface<T> {

    private Node<T> frontNode;
    private Node<T> backNode;

    @Override
    public int size() {
        int length = 0;
        Node node = frontNode;
        if (node == null) {
            return length;
        } else {
            length++;
        }

        while (node.getNext() != null) {
            length++;
            node = node.getNext();
        }
        return length;
    }

    @Override
    public boolean isFull() {
        return false; // Linked Queue is never full.
    }

    @Override
    public boolean isEmpty() {
        boolean result;
        if (size() == 0) {
            assert frontNode == null && backNode == null;
            result = true;
        } else {
            assert frontNode != null : "numberOfEntries is not 0 but frontNode is null";
            assert backNode != null : "numberOfEntries is not 0 but backNode is null";
            result = false;
        }
        return result;
    }

    @Override
    public T getFront() {
        T front = null;
        if (!isEmpty()) {
            front = frontNode.getData();
        }
        return front;
    }

    @Override
    public boolean enqueue(Object newEntry) {
        if (newEntry == null) {
            return false;
        }
        Node newNode = new Node(newEntry, null);

        if (isEmpty()) {
            frontNode = newNode;
        } else {
            backNode.setNext(newNode);
        }
        backNode = newNode;
        return true;
    }

    @Override
    public T dequeue() {
        T front = null;
        if (!isEmpty()) {
            front = frontNode.getData();
            frontNode = frontNode.getNext();
            if (frontNode == null) {
                backNode = null;
            }
        }
        return front;
    }

    @Override
    public T[] toArray() {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings(value = "unchecked")
        T[] result = (T[]) new Object[size()]; // unchecked cast
        int index = size() - 1;

        Node currentNode = backNode.getNext();
        while ((index < size()) && (currentNode != null)) {
            result[index] = (T) currentNode.getData();
            index--;
            currentNode = currentNode.getNext();
        }
        return result;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    @Override
    public boolean equals(Object aThat) {
        if (this == aThat) {
            return true;
        }
        if (!(aThat instanceof Queue_Linked)) {
            return false;
        }
        Queue_Linked that = (Queue_Linked) aThat;
        return Arrays.equals(this.toArray(), that.toArray()); //array!
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.frontNode);
        hash = 19 * hash + Objects.hashCode(this.backNode);
        return hash;
    }
}

//    @Override
//    public void display() {
//        displayChain(frontNode);
//    }
//
//    private void displayChain(Node nodeOne) {
//        if (nodeOne != null) {
//            System.out.println(nodeOne.getData()); // display first node
//            displayChain(nodeOne.getNext());
//        }
//    }