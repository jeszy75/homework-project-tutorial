package rollingdice.model;

import java.util.Objects;

public class Dice implements Cloneable {

    public enum Side {
        TOP,
        NORTH,
        EAST,
        WEST,
        SOUTH,
        BOTTOM
    }

    private int top;
    private int north;
    private int west;

    public Dice() {
        this(6, 3, 2);
    }

    public Dice(int top, int north, int west) {
        checkSides(top, north, west);
        this.top = top;
        this.north = north;
        this.west = west;
    }

    private void checkSides(int top, int north, int west) {
        if (!isDiceValue(top) || !isDiceValue(north) || !isDiceValue(west)
                || top == north || top == west || north == west
                || top + north == 7 || top + west == 7 || north + west == 7) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isDiceValue(int value) {
        return 1 <= value && value <= 6;
    }

    public int getValue(Side side) {
        return switch (side) {
            case TOP -> top;
            case NORTH -> north;
            case EAST -> 7 - west;
            case SOUTH -> 7 - north;
            case WEST -> west;
            case BOTTOM -> 7 - top;
        };
    }

    public void roll(Direction direction) {
        switch (direction) {
            case NORTH -> rollNorth();
            case EAST -> rollEast();
            case SOUTH -> rollSouth();
            case WEST -> rollWest();
        }
    }

    private void rollNorth() {
        var newNorth = top;
        top = getValue(Side.SOUTH);
        north = newNorth;
    }

    private void rollEast() {
        var newTop = west;
        west = getValue(Side.BOTTOM);
        top = newTop;
    }

    private void rollSouth() {
        var newTop = north;
        north = getValue(Side.BOTTOM);
        top = newTop;
    }

    private void rollWest() {
        var newTop = getValue(Side.EAST);
        west = top;
        top = newTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return (o instanceof Dice that) && top == that.top && north == that.north && west == that.west;
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, north, west);
    }

    @Override
    public Dice clone () {
        Dice copy;
        try {
            copy = (Dice) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
        return copy;
    }

    @Override
    public String toString() {
        return String.format("Dice[top=%s,north=%s,west=%s]", top, north, west);
    }

    public static void main(String[] args) {
        var dice = new Dice();
        System.out.println(dice);
        dice.roll(Direction.NORTH);
        System.out.println(dice);
        dice.roll(Direction.EAST);
        System.out.println(dice);
        dice.roll(Direction.SOUTH);
        System.out.println(dice);
        dice.roll(Direction.WEST);
        System.out.println(dice);
    }

}
