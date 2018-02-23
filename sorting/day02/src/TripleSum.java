import java.util.ArrayList;
import java.util.Arrays;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        // TODO
        ArrayList<int[]> tripleSums = new ArrayList<int[]>();
        Arrays.sort(arr);
        int fc,bc,currSum;

        for(int i = 0; i < arr.length-2; i ++) {
            fc = i + 1;
            bc = arr.length - 1;
            while( fc < bc ) {
                currSum = arr[i] + arr[fc] + arr[bc];
                if(currSum == sum) {
                    System.out.println("sum " + sum + " found as " + currSum + " with " + arr[i] + " " + arr[fc] + " " + arr[bc]);
                    tripleSums.add(new int[] {arr[i],arr[fc],arr[bc]});
                    fc++;
                    bc--;
                } else if (currSum < sum) {
                    fc++;
                } else {
                    bc--;
                }
            }
        }
        return tripleSums.size();
    }

}
