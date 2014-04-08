package sorting;

/**
 * File: Description:
 *
 * @author lunatunez
 */
public class SelectionSort_Recursive implements Sorter {

    public static <T extends Comparable<? super T>>
            void selectionSort(T[] a, int n) {
        selectionSort(a, 0, n - 1); // invoke recursive method
    } // end selectionSort

}
