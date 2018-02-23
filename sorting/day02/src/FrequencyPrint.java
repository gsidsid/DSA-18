import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // TODO
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        String[] ss = s.split(" ");
        for(String sss : ss) {
            Integer freq = hm.get(sss);
            if(freq == null) {
                freq = 1;
            } else {
                freq++;
            }
            hm.put(sss,freq);
        }

        List<Map.Entry<String,Integer>> ar = new ArrayList<Map.Entry<String,Integer>>(hm.entrySet());
        ar.sort(Comparator.comparing(Map.Entry::getValue));

        String[] sf = new String[ar.size()];
        String fs = "";

        for(int i = 0; i < ar.size(); i++) {
            for(int j = 0; j < ar.get(i).getValue(); j++) {
                if(!fs.equals("")) {
                    fs += (" " + ar.get(i).getKey());
                } else {
                    fs += (ar.get(i).getKey());
                }

            }
        }
        System.out.println(fs);
        return fs;

    }

}
