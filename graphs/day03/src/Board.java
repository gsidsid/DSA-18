import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        this.tiles = b;
        this.n = b[0].length;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        // TODO: Your code here
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        // TODO: Your code here
        int sum = 0;
        int val;
        int target_row;
        int target_col;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val = tiles[i][j];
                if (val != 0) {
                    target_row = (val-1)%n;
                    target_col = (val-1)/n;
                    sum = sum + (Math.abs(j-target_row) + Math.abs(i-target_col));
                }
            }
        }

        return sum;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        // TODO: Your code here
        return Arrays.deepEquals(tiles, goal);
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */

    /*
      * Helper function to get number of inversions in board state
    */

    private int inversions() {
        for (int i = 0; i < size()*size() - 1; i++) {
            for (int j = 0; j < size()*size() - 1; j++) {

            }

        }
    }

    public boolean solvable() {
        // TODO: Your code here
        return false;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here
        return null;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
