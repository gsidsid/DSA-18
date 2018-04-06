import java.util.ArrayList;
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
        return 0;
    }

    public boolean solvable() {
        return false;
    }

    public Board swap(int r, int c, String dir) {
        int temp;
        int[][] newtiles = this.tiles.clone();
        Board b_new = new Board(newtiles);

        if (dir.equals("r")) {
            temp = b_new.tiles[r][c];
            b_new.tiles[r][c] = b_new.tiles[r][c+1];
            b_new.tiles[r][c+1] = temp;
        } else if (dir.equals("l")) {
            temp = b_new.tiles[r][c];
            b_new.tiles[r][c] = b_new.tiles[r][c-1];
            b_new.tiles[r][c-1] = temp;
        } else if (dir.equals("u")) {
            temp = b_new.tiles[r][c];
            b_new.tiles[r][c] = b_new.tiles[r-1][c];
            b_new.tiles[r-1][c] = temp;
        } else if (dir.equals("d")) {
            temp = b_new.tiles[r][c];
            b_new.tiles[r][c] = b_new.tiles[r+1][c];
            b_new.tiles[r+1][c] = temp;
        }
        return b_new;
    }

    private int[][] deepCopy2D(int[][] curr) {
        int[][] next = new int[curr.length][curr[0].length];
        for (int x = 0; x < curr.length; x++) {
            for (int y = 0; y < curr[0].length; y++) {
                if (curr[x][y] != 0)//write only when necessary
                {
                    next[x][y] = curr[x][y];
                }
            }
        }
        return next;
    }

    public List<Board> trySwaps(int erow, int ecol, String[] dirs) {
        List<Board> steps = new ArrayList<>();
        for (String dir : dirs) {
            if (dir.equals("u")) {
                this.swap(erow, ecol, "u");
                steps.add(new Board(deepCopy2D(this.tiles)));
                this.swap(erow-1, ecol, "d");
            } else if (dir.equals("d")) {
                this.swap(erow, ecol, "d");
                steps.add(new Board(deepCopy2D(this.tiles)));
                this.swap(erow+1, ecol, "u");
            } else if (dir.equals("l")) {
                this.swap(erow, ecol, "l");
                steps.add(new Board(deepCopy2D(this.tiles)));
                this.swap(erow, ecol-1, "r");
            } else {
                this.swap(erow, ecol, "r");
                steps.add(new Board(deepCopy2D(this.tiles)));
                this.swap(erow, ecol+1, "l");
            }
        }
        return steps;
    }
    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here
        List<Board> neighbors = new ArrayList<>();

        int erow = 0;
        int ecol = 0;
        for(int i = 0; i < tiles.length; i ++) {
            for(int j = 0; j < tiles[0].length; j ++) {
                if(tiles[i][j] == 0) {
                    erow = i;
                    ecol = j;
                }
            }
        }

        String[] makeSwaps;

        if((erow==0 || erow==(n-1)) && (ecol==0 || ecol==(n-1))) { //CORNER

            if (erow == 0 && ecol == 0) {
                makeSwaps = new String[]{"r", "d"};
            } else if (erow == 0 && ecol == (n-1)) {
                makeSwaps = new String[]{"l", "d"};
            } else if (erow == (n-1) && ecol == 0) {
                makeSwaps = new String[]{"r", "u"};
            } else {
                makeSwaps = new String[]{"l", "u"};
            }

            neighbors.addAll(trySwaps(erow, ecol, makeSwaps));

        } else if ((erow==0 || erow==(n-1)) || (ecol==0 || ecol==(n-1))) { //EDGE

            if ( erow == 0 ) {
                makeSwaps = new String[]{"d", "l", "r"};
            } else if ( erow == (n-1) ){
                makeSwaps = new String[]{"u", "l", "r"};
            } else if ( ecol == 0) {
                makeSwaps = new String[]{"u", "d", "r"};
            } else {
                makeSwaps = new String[]{"u", "d", "l"};
            }

            neighbors.addAll(trySwaps(erow, ecol, makeSwaps));

        } else { //MIDDLE

            makeSwaps = new String[]{"u", "d", "l", "r"};
            neighbors.addAll(trySwaps(erow, ecol, makeSwaps));

        }

        return neighbors;
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
        int[][] initState = {{0, 2, 3}, {1, 4, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");

        Iterable<Board> it = board.neighbors();
        for (Board b : it) {
            System.out.println(Arrays.deepToString(b.tiles));
        }
    }
}
