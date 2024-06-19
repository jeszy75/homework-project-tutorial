package rollingdice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    private Dice dice;

    private void assertDice(int expectedTop, int expectedNorth, int expectedWest, Dice dice) {
        assertAll("dice",
                () -> assertEquals(expectedTop, dice.getValue(Dice.Side.TOP)),
                () -> assertEquals(expectedNorth, dice.getValue(Dice.Side.NORTH)),
                () -> assertEquals(expectedWest, dice.getValue(Dice.Side.WEST))
        );
    }

    @BeforeEach
    void setUp() {
        dice = new Dice();
    }

    @Test
    void Dice_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Dice(0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Dice(1, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> new Dice(1, 6, 3));
    }

    @Test
    void getValue() {
        assertEquals(6, dice.getValue(Dice.Side.TOP));
        assertEquals(3, dice.getValue(Dice.Side.NORTH));
        assertEquals(5, dice.getValue(Dice.Side.EAST));
        assertEquals(4, dice.getValue(Dice.Side.SOUTH));
        assertEquals(2, dice.getValue(Dice.Side.WEST));
        assertEquals(1, dice.getValue(Dice.Side.BOTTOM));
    }

    @Test
    void roll_north() {
        dice.roll(Direction.NORTH);
        assertDice(4, 6, 2, dice);
    }

    @Test
    void roll_east() {
        dice.roll(Direction.EAST);
        assertDice(2, 3, 1, dice);
    }

    @Test
    void roll_south() {
        dice.roll(Direction.SOUTH);
        assertDice(3, 1, 2, dice);
    }

    @Test
    void roll_west() {
        dice.roll(Direction.WEST);
        assertDice(5, 3, 6, dice);
    }

    @Test
    void testEquals() {
        assertTrue(dice.equals(dice));
        assertTrue(dice.equals(new Dice()));
        assertFalse(dice.equals(null));
        assertFalse(dice.equals("Hello, World!"));
        var otherDice = dice.clone();
        otherDice.roll(Direction.NORTH);
        assertFalse(dice.equals(otherDice));
    }

    @Test
    void testHashCode() {
        assertTrue(dice.hashCode() == dice.hashCode());
        assertTrue(dice.hashCode() == dice.clone().hashCode());
    }

    @Test
    void testClone() {
        var clone = dice.clone();
        assertTrue(clone.equals(dice));
        assertNotSame(clone, dice);
    }

    @Test
    void testToString() {
        assertEquals("Dice[top=6,north=3,west=2]", dice.toString());
    }

}