import java.util.Collection;
import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        // TODO
        HashMap<Double,Integer> hm = new HashMap<Double,Integer>();
        int equidistant_points = 0;
        int count;

        for( int i = 0; i < points.length; i++) {

            for( int j = 0; j < points.length; j++) {

                if( j != i) {
                    int[] a = points[i];
                    int[] b = points[j];

                    double e_dist_x = Math.sqrt(Math.pow(b[0]-a[0],2)+Math.pow(b[1]-a[1],2));

                    if(hm.containsKey(e_dist_x))
                    {
                        count = hm.get(e_dist_x) + 1;
                        hm.put(e_dist_x,count);
                    }
                    else
                    {
                        hm.put(e_dist_x,1);
                    }

                }
            }

            Collection<Integer> vals = hm.values();
            for(int val: vals) {
                equidistant_points += val * (val-1);
            }

            hm.clear();
        }

        return equidistant_points;
    }
}
