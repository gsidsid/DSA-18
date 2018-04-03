import java.util.*;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        // TODO
        List<List<Integer>> permutations = new LinkedList<>();
        Set<Integer> unused = new HashSet<Integer>(A);
        LinkedList<Integer> curr = new LinkedList<Integer>();
        backtrack(curr, unused, permutations);
        return permutations;
    }

}
