import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static char[][] makeBoard(int n) {
        char[][] board = new char[n][n];
        for( int row = 0; row < board.length; row++ ) {
            for( int col = 0; col < board[0].length; col++ ) {
                board[row][col] = '.';
            }
        }
        return board;
    }

    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<char[][]> answers = new ArrayList<>();
        char[][] currBoard = makeBoard(n);
        boolean[] openCols = new boolean[n];
        for( int i = 0; i < openCols.length; i++ ) {
            openCols[i] = true;
        }
        solveNQueens(currBoard,0,openCols,answers);
        return answers;
    }

    public static void solveNQueens(char[][] board, int row, boolean[] openCols, List<char[][]> sols) {
        if( row >= board[0].length ) {
            sols.add(board);
        } else {
            for (int i = 0; i < openCols.length; i ++) {
                if(openCols[i]) {
                    char[][] newBoard = copyOf(board);
                    newBoard[row][i] = 'Q';
                    if(!checkDiagonal(newBoard,row,i)) {
                        openCols[i] = false;
                        solveNQueens(newBoard, row + 1, openCols, sols);
                        openCols[i] = true;
                    }
                }
            }
        }
    }

}
