public class Problems {

    public static int leastSum(int[] A) {
        //TODO
        int[] bins = new int[10];
        boolean open = true;

        int first = 0;
        int second = 0;

        for(int i = 0; i < A.length; i ++) {
            bins[A[i]]++;
        }

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < bins[i]; j++) {
                if(open) {
                    first = first*10+i;
                } else {
                    second = second*10+i;
                }
                open = !open;
            }
        }

        return first + second;
    }
}
