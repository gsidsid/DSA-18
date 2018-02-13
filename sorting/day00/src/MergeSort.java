import java.util.Arrays;
import java.util.Collections;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        // TODO
        if(array.length > 1) {
            int r = array.length/2;

            int[] a;
            int[] b;

            if(array.length % 2 == 0) {
                a = new int[r];
                b = new int[r];
                System.arraycopy(array,0,a,0,r);
                System.arraycopy(array,r,b,0,r);
            } else {
                a = new int[r];
                b = new int[r+1];
                System.arraycopy(array,0,a,0,r);
                System.arraycopy(array,r,b,0,r+1);
            }

            sort(a);
            sort(b);
            array = merge(a,b);
        }


        return array;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        // TODO

        int[] sorted = new int[a.length+b.length];
        System.arraycopy(a,0,sorted,0,a.length);
        System.arraycopy(b,0,sorted,a.length,b.length);
        
            for(int i = 1; i < sorted.length; i ++ ) {
                int pos = i;
                int val = sorted[pos];
                while(pos > 0 && sorted[pos-1] > val) {
                    swap(sorted,pos,pos-1);
                    pos--;
                }
            }

        return sorted;
    }

}
