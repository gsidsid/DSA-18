import java.util.LinkedList;
import java.util.stream.IntStream;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // TODO
        int shift = IntStream.of(A).min().getAsInt();

        for(int i = 0; i < A.length; i++) {
            A[i] = A[i] + Math.abs(shift);
        }
        CountingSort.countingSort(A);

        for(int i = 0; i < A.length; i++) {
            A[i] = A[i] - Math.abs(shift);
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // TODO
        LinkedList<String>[] L = new LinkedList[26]; //We can assume the alphabet to be base 26
        for (int i = 0; i < 26; i++)
            L[i] = new LinkedList<>();
        for (String i : A) {
            int digit = getNthCharacter(i,n);
            L[digit].addLast(i); //Adds element from array A to correct list based on digit index
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            for(int i = 0; i < list.size(); i ++) {
                A[j] = list.get(i);
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // TODO
        int w = S[0].length(); // All the strings are the same size, so no calculation is necessary
        // TODO: Perform radix sort
        for(int nb = 0; nb < w; nb++) {
            countingSortByCharacter(S,nb);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
