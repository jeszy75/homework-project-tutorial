package rollingdice.model;

/**
 * Represents the states of the rolling dice puzzle.
 */
public class RollingDiceState {

    /**
     * Represents the game board. Empty squares are represented by 0s.
     */
    public static final int[][] BOARD = {
            {4, 6, 2, 5, 1},
            {5, 2, 1, 0, 4},
            {6, 2, 3, 1, 5},
            {6, 4, 6, 3, 6},
            {5, 3, 4, 5, 1},
            {3, 6, 0, 3, 3}
    };

    /**
     * The number of rows in the game board.
     */
    public static final int ROWS = BOARD.length;

    /**
     * The number of columns in the game board.
     */
    public static final int COLS = BOARD[0].length;

}
