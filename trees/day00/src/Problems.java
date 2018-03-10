import java.util.*;

public class Problems {


    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO

        BinarySearchTree<Integer> minBST = new BinarySearchTree<>();

        int mid = values.size() / 2;

        Collections.sort(values);

        if (values.size() > 0) {
            minBST.add(values.get(mid));
        }

        if (mid > 0) {
            buildBST(values.subList(0, mid - 1), minBST); // bst.add(left stuff)
            buildBST(values.subList(mid + 1, values.size()), minBST); // bst.add(right stuff)
        }

        return minBST;


    }

    public static void buildBST(List<Integer> small, BinarySearchTree<Integer> bst) {

        // Get value to set node to (midpoint)
        int mid = small.size() / 2;


        if (small.size() > 1) {

            int key_root = small.get(mid);
            bst.add(key_root);

            // Recurse on left side
            buildBST(small.subList(0, mid), bst);
            // Recurse on right side
            buildBST(small.subList(mid + 1, small.size()), bst);

        } else if (small.size() > 0 ) {

            bst.add(small.get(0));

        }


    }



    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
