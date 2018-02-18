import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        // TODO
        PriorityQueue<Integer> max = maxPQ();
        PriorityQueue<Integer> min = minPQ();

        if( inputStream.length > 0 ) {

            runningMedian[0] = inputStream[0];
            max.offer(inputStream[0]);
            int diff;

            for (int i = 1; i < inputStream.length; i++) {
                //update PQ's
                if(inputStream[i] > runningMedian[i-1]) {
                    min.add(inputStream[i]);
                } else {
                    max.add(inputStream[i]);
                }
                //balance PQ's and identify case
                diff = Math.abs(max.size() - min.size());
                if (diff > 2) {
                    if(max.size() > min.size()) {
                        min.offer(max.poll());
                    } else {
                        max.offer(min.poll());
                    }
                    diff = Math.abs(max.size() - min.size());
                }
                //get median depending on case
                if (diff == 0) {
                    runningMedian[i] = getMedian(Arrays.asList(max.peek(),min.peek()));
                } else if (diff == 1) {
                    if(max.size() > min.size()) {
                        runningMedian[i] = max.peek();
                    } else {
                        runningMedian[i] = min.peek();
                    }
                } else if (diff == 2) {
                    if(max.size() > min.size()) {
                        int top = max.poll();
                        int pen = max.peek();
                        max.offer(top);
                        runningMedian[i] = getMedian(Arrays.asList(top,pen));
                    } else {
                        int top = min.poll();
                        int pen = min.peek();
                        min.offer(top);
                        runningMedian[i] = getMedian(Arrays.asList(top,pen));
                    }
                }
            }
        }
        return runningMedian;
    }

}
