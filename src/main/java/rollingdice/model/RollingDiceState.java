package rollingdice.model;

import puzzle.State;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the states of the rolling dice puzzle.
 */
public class RollingDiceState implements State<Direction> {

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

    private Dice dice;
    private Position dicePosition;

    public RollingDiceState() {
        dice = new Dice();
        dicePosition = new Position(0, 0);
    }

    @Override
    public boolean isSolved() {
        return dicePosition.row() == ROWS - 1 && dicePosition.col() == COLS - 1;
    }

    @Override
    public boolean isLegalMove(Direction direction) {
        var newDicePosition = dicePosition.move(direction);
        return isOnBoard(newDicePosition)
                && (getBoardValue(newDicePosition) == dice.getValue(Dice.Side.TOP) || getBoardValue(newDicePosition) == 0);
    }

    @Override
    public void makeMove(Direction direction) {
        dice.roll(direction);
        dicePosition = dicePosition.move(direction);
    }

    /**
     * {@return the set of all moves that can be applied to the state}
     */
    @Override
    public Set<Direction> getLegalMoves() {
        return Arrays.stream(Direction.values())
                .filter(this::isLegalMove)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Direction.class)));
    }

    private boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < ROWS
                && 0 <= position.col() && position.col() < COLS;
    }

    private int getBoardValue(Position position) {
        return BOARD[position.row()][position.col()];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return (o instanceof RollingDiceState that)
                && (dice.equals(that.dice))
                && Objects.equals(dicePosition, that.dicePosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dice, dicePosition);
    }

    @Override
    public RollingDiceState clone() {
        RollingDiceState copy;
        try {
            copy = (RollingDiceState) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
        copy.dice = dice.clone();
        return copy;
    }

    @Override
    public String toString() {
        return String.format("RollingDiceState[dice=%s,dicePosition=%s]", dice, dicePosition);
    }

}
