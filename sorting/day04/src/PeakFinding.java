import java.util.ArrayList;
import java.util.Arrays;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        // TODO

        int mid = nums.length/2;

        if(nums.length > 2) {

            if(nums[mid] < nums[mid-1]) {

                int[] half1 = new int[mid];
                System.arraycopy(nums,0,half1,0,mid);
                return findOneDPeak(half1);

            } else if (nums[mid] < nums[mid+1]) {

                int[] half2 = new int[nums.length - mid];
                System.arraycopy(nums, mid, half2, 0, nums.length - mid);
                return mid + findOneDPeak(half2);

            }

        }

        return mid;

    }

    public static boolean scan_i = true;


    public static int[] getColumn(int[][] two, int col) {
        int[] column = new int[two.length]; // Here I assume a rectangular 2D array!
        for(int i=0; i<column.length; i++){
            column[i] = two[i][col];
        }
        return column;
    }

    public static void arrayCopy2D(int[][] src, int[][] dest, int lo_row, int hi_row, int lo_col, int hi_col) {
        //System.out.println("Copying region of dimensions " + (hi_row-lo_row) + " X " + (hi_col-lo_col) + " to array of " + dest.length + " X " + dest[0].length);
        for(int i = lo_row; i < hi_row; i ++) {
            for(int j = lo_col; j < hi_col; j ++) {
                dest[i-lo_row][j-lo_col] = src[i][j];
            }
        }
    }




    public static int[] findTwoDPeak(int[][] nums) {
        // TODO

        int middle_row = nums.length/2;
        int middle_col = nums[0].length/2;

        if( scan_i ) {

            int peak_index = findOneDPeak(nums[middle_row]);
            if(nums.length > 2) {

                if (nums[middle_row][peak_index] >= nums[middle_row + 1][peak_index] && nums[middle_row][peak_index] >= nums[middle_row - 1][peak_index]) {
                    return new int[]{middle_row, peak_index}; // BASE

                } else if (nums[middle_row][peak_index] < nums[middle_row + 1][peak_index]) {
                    scan_i = false;
                    int[][] bottom = new int[nums.length - middle_row][nums[0].length];
                    arrayCopy2D(nums, bottom, middle_row, nums.length, 0, nums[0].length); // DIVIDE
                    int[] sol = findTwoDPeak(bottom);
                    return new int[] { sol[0] + middle_row, sol[1] }; // COMBINE
                } else if (nums[middle_row][peak_index] < nums[middle_row - 1][peak_index]) {
                    scan_i = false;
                    int[][] top = new int[middle_row][nums[0].length];
                    arrayCopy2D(nums, top, 0, middle_row, 0, nums[0].length);
                    return findTwoDPeak(top);
                }
            }

        } else {

            if(nums[0].length > 2) {
                int[] col = getColumn(nums, middle_col);
                int peak_index = findOneDPeak(col);

                if (nums[peak_index][middle_col] >= nums[peak_index][middle_col+1] && nums[peak_index][middle_col] >= nums[peak_index][middle_col-1]) {
                    return new int[] {peak_index, middle_col};

                } else if( nums[peak_index][middle_col] < nums[peak_index][middle_col+1] ) {
                    scan_i = true;
                    int[][] right = new int[nums.length][nums[0].length-middle_col];
                    arrayCopy2D(nums,right,0,nums.length,middle_col,nums[0].length);
                    int[] sol = findTwoDPeak(right);
                    return new int[] { sol[0], middle_col + sol[1] };
                } else if ( nums[peak_index][middle_col] < nums[peak_index][middle_col-1] ) {
                    scan_i = true;
                    int[][] left = new int[nums.length][middle_col];
                    arrayCopy2D(nums,left,0,nums.length,0,middle_col);
                    return findTwoDPeak(left);
                }
            }
        }

        return new int[] {1, 1};
    }



}
