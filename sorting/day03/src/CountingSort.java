import java.util.stream.IntStream;

public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(N+k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = IntStream.of(A).max().getAsInt();
        int[] counts = new int[k+1];
        int i = 0;

        for(int elem : A) {
            counts[elem] = counts[elem]+1;
        }

        for(int j = 0; j <= k; j++) {
            while(counts[j] > 0) {
                counts[j] = counts[j]-1;
                A[i] = j;
                i++;
            }
        }
    }

}
