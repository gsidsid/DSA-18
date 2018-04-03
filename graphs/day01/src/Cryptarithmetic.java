import java.util.*;

public class Cryptarithmetic {

    // Do not modify this function (though feel free to use it)
    public static boolean validSolution(String S1, String S2, String S3, Map<Character, Integer> assignments) {
        return (stringToInt(S1, assignments) + stringToInt(S2, assignments) == stringToInt(S3, assignments))
                && assignments.get(S1.charAt(0)) != 0
                && assignments.get(S2.charAt(0)) != 0
                && assignments.get(S3.charAt(0)) != 0;
    }


    private static Iterable<Integer> randomOrder() {
        List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(l);
        return l;
    }

    private static int stringToInt(String s, Map<Character, Integer> assignments) {
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            i *= 10;
            i += assignments.get(s.charAt(j));
        }
        return i;
    }

    public static Map<Character, Integer> solvePuzzle(String S1, String S2, String S3) {
        // TODO
        Map<Character, Integer> assignments = new HashMap<>();

        String cat = S1 + S2 + S3;
        HashSet<Character> uq = new HashSet<>();
        for (int i = 0; i < cat.length(); i ++) {
            uq.add(cat.charAt(i));
        }
        ArrayList<Character> uqs = new ArrayList<Character>(uq);

        tryMap(uqs, assignments, S1, S2, S3);
        System.out.println(assignments);
        return assignments;
    }

    public static boolean tryMap(ArrayList<Character> unassigned, Map<Character, Integer> assignments, String S1, String S2, String S3) {
        if(unassigned.size() == 0) {
            return validSolution(S1, S2, S3, assignments);
        } else {
            for(int i : randomOrder()) {
                char c = unassigned.remove(unassigned.size()-1);
                assignments.put(c, i);
                if(tryMap(unassigned, assignments, S1, S2, S3)) {
                    return true;
                }
                unassigned.add(c);
                if(!assignments.isEmpty()) {
                    assignments.remove(c);
                } else {
                    return false;
                }

            }
        }
        return false;
    }
}
