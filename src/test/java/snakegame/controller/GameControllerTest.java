package snakegame.controller;

import org.junit.Before;
import org.junit.Test;
import snakegame.model.Direction;
import snakegame.model.Square;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class GameControllerTest {

    private GameController gameControllerUnderTest;

    @Before
    public void setUp() {
        gameControllerUnderTest = new GameController(new Square[][]{{new Square(40, 40, false)}}, List.of(new Square(40, 40, false)));
    }

    @Test
    public void testChangeDirection() {
        // Setup

        // Run the test
        gameControllerUnderTest.changeDirection(Direction.UP);

        // Verify the results
    }

    @Test
    public void testPaintBoard() {
        // Setup

        // Run the test
        gameControllerUnderTest.paintBoard();

        // Verify the results
    }
}
