
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(N)
     * Worst-case runtime: O(N^2)
     * Average-case runtime: O(N^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO
        for(int i = 1; i < array.length; i ++ ) {
            int pos = i;
            int val = array[pos];
            while(pos > 0 && array[pos-1] > val) {
                swap(array,pos,pos-1);
                pos--;
            }
        }
        return array;
    }
}
