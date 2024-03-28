package mower;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mower.model.Mower;
import org.mower.model.Position;
import org.mower.service.MowerService;

public class MowerServiceTest {
    private MowerService mowerService;
    private Position currentPosition;

    @Before
    public void setUp() {
        mowerService = new MowerService();
        currentPosition = new Position(3, 3);
    }

    @Test
    public void testMoveForwardNorth() {
        char orientation = 'N';
        Position newPosition = mowerService.moveForward(currentPosition, orientation);
        assertEquals(3, newPosition.getX());
        assertEquals(4, newPosition.getY());
    }

    @Test
    public void testMoveForwardEast() {
        char orientation = 'E';
        Position newPosition = mowerService.moveForward(currentPosition, orientation);
        assertEquals(4, newPosition.getX());
        assertEquals(3, newPosition.getY());
    }

    @Test
    public void testMoveForwardSouth() {
        char orientation = 'S';
        Position newPosition = mowerService.moveForward(currentPosition, orientation);
        assertEquals(3, newPosition.getX());
        assertEquals(2, newPosition.getY());
    }

    @Test
    public void testMoveForwardWest() {
        char orientation = 'W';
        Position newPosition = mowerService.moveForward(currentPosition, orientation);
        assertEquals(2, newPosition.getX());
        assertEquals(3, newPosition.getY());
    }

    @Test
    public void testIsValidPosition() {
        int maxX = 5;
        int maxY = 5;
        assertTrue(mowerService.isValidPosition(currentPosition, maxX, maxY));
    }

    @Test
    public void testIsInvalidPosition() {
        int maxX = 5;
        int maxY = 5;
        Position invalidPosition = new Position(6, 6);
        assertFalse(mowerService.isValidPosition(invalidPosition, maxX, maxY));
    }

    @Test
    public void testMoveWithValidPosition() {
        Position currentPosition = new Position(1, 2);
        Mower mower = new Mower(currentPosition, 'N');
        mowerService.move(mower, "AAA", 5, 5);
        assertEquals(1, mower.getPosition().getX());
        assertEquals(5, mower.getPosition().getY());
        assertEquals('N', mower.getOrientation());
    }

    @Test
    public void testMoveWithInvalidPosition() {
        Position currentPosition = new Position(3, 3);
        Mower mower = new Mower(currentPosition, 'N');
        mowerService.move(mower, "AAAAA", 5, 5); // This instruction will move the mower out of bounds
        assertEquals(3, mower.getPosition().getX());
        assertEquals(5, mower.getPosition().getY()); // The mower stays in the previous position
        assertEquals('N', mower.getOrientation());
    }
}