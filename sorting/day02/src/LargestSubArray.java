import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // TODO
        HashMap<Integer,Integer> hm = new HashMap<>();
        int[] sum = new int[nums.length + 1];
        int mx = 0;
        int end = 0;
        int current;
        sum[0] = 0;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                sum[i+1] = sum[i]+1;
            } else {
                sum[i+1] = sum[i]-1;
            }
        }

        for(int i = 0; i < sum.length; i++) {
            if(hm.containsKey(sum[i])) {

                current = hm.get(sum[i]);
                if(i - current > mx) {
                    mx = i - current;
                    end = i;
                }
            } else {
                hm.put(sum[i],i);
            }
        }
        return new int[]{end-mx, end-1};
    }
}
