package adts;

import java.util.Arrays; 
/** 
A class that implements a list of objects by using an array. 
The list is never full. 
*/ 
public class List_Array<T> implements ListInterface<T> { 
private T[] list; 
// array of list entries 
private int numberOfEntries; 
private static final int DEFAULT_INITIAL_CAPACITY = 25; 

public List_Array() { 
	this(DEFAULT_INITIAL_CAPACITY); // call next constructor 
} // end default constructor 

public List_Array(int initialCapacity) { 
	numberOfEntries = 0; 
	// the cast is safe because the new array contains null entries 
	@SuppressWarnings("unchecked") 
	T[] tempList = (T[])new Object[initialCapacity]; 
	list = tempList; 
} // end constructor 

public void add(T newEntry) { 
	ensureCapacity(); 
	list[numberOfEntries] = newEntry; 
	numberOfEntries++; 
}

public boolean add(int newPosition, T newEntry)  { 
	boolean isSuccessful = true; 
	if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))  { 
		ensureCapacity(); 
		makeRoom(newPosition); 
		list[newPosition - 1] = newEntry; 
		numberOfEntries++; 
	} 
	else 
		isSuccessful = false; 
		return isSuccessful; 
	}

public void clear() {
	// < Implementation deferred > 
} // end clear 

public T remove(int givenPosition) { 
	T result = null; // return value 
	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) { 
		assert !isEmpty(); 
		result = list[givenPosition - 1]; // get entry to be removed 
		// move subsequent entries toward entry to be removed, 
		// unless it is last in list 
	if (givenPosition < numberOfEntries) 
		removeGap(givenPosition); 
		numberOfEntries--; 
	} // end if 
	return result; 
	// return reference to removed entry, or 
	// null if either list is empty or givenPosition 
	// is invalid 
} // end remove

public boolean replace(int givenPosition, T newEntry) { 
	boolean isSuccessful = true; 
	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) { 
		assert !isEmpty(); 
		list[givenPosition - 1] = newEntry; 
	}
	else 
		isSuccessful = false; 
	return isSuccessful; 
} // end replace 

public T getEntry(int givenPosition) { 
	T result = null; // result to return 
	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) { 
		assert !isEmpty(); 
		result = list[givenPosition - 1]; 
	} // end if 
	return result; 
} // end getEntry
/**
 * method getEntry locates by going directly to array element.
 * Begins at index 0, examines each entry until it locates or
 * reaches end use a local boolean variable to terminate loop 
 */
public boolean contains(T anEntry) { 
	boolean found = false; 
	for (int index = 0; !found && (index < numberOfEntries); index++) { 
		if (anEntry.equals(list[index])) 
		found = true; 
	} // end for 
	return found; 
} // end contains
public int getLength()  { 
	return numberOfEntries; 
} 

public boolean isEmpty() { 
	return numberOfEntries == 0; // or getLength() == 0 
} 

public T[] toArray() { 
	// the cast is safe because the new array contains null entries 
	@SuppressWarnings("unchecked") 
	T[] result = (T[])new Object[numberOfEntries]; 
	for (int index = 0; index < numberOfEntries; index++) { 
		result[index] = list[index]; 
	}
	return result; 
}

// Doubles the size of the array list if it is full. 
private void ensureCapacity() { 
	if (numberOfEntries == list.length) 
	list = Arrays.copyOf(list, 2 * list.length); 
}

/**
 * Typically, method shifts entries toward end, beginning w/ last entry.
 * However, if newPosition is numberOfEntries + 1, addition is at end 
 * of list so no shift is necessary.  In this case, makeRoom does nothing, 
 * since for stmt exits immediately. Makes room for a new entry at newPosition. 
// Precondition: 1 <= newPosition <= numberOfEntries+1; 
// numberOfEntries is list’s length before addition. 
 */
private void makeRoom(int newPosition) { 
assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
	int newIndex = newPosition - 1; 
	int lastIndex = numberOfEntries - 1; 
	// move each entry to next higher index, starting at end of 
	// list and continuing until the entry at newIndex is moved 
	for (int index = lastIndex; index >= newIndex; index--) 
		list[index + 1] = list[index]; 
}

/**shifts list entries within the array beginning w/ entry after one to remove.
 * and,	continues until end of list, moves each entry to next lower position. 
 * Shifts entries that are beyond the entry to be removed to the 
 * next lower position. Precondition: 1 <= givenPosition < numberOfEntries; 
 * numberOfEntries is list’s length before removal. 
 */
private void removeGap(int givenPosition) { 
	assert (givenPosition >= 1) && (givenPosition < numberOfEntries); 
	int removedIndex = givenPosition - 1; 
	int lastIndex = numberOfEntries - 1; 
	for (int index = removedIndex; index < lastIndex; index++) 
		list[index] = list[index + 1]; 
}

}
