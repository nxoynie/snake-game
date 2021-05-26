package snakegame.controller;

import javafx.application.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import snakegame.SnakeGameApplication;
import snakegame.model.Direction;
import snakegame.model.Square;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static snakegame.model.Direction.*;

class GameControllerTest extends ApplicationTest {

    GameController gameController;

    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        // Initialise Java FX

        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(SnakeGameApplication.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
        Thread.sleep(500);
    }

    @BeforeEach
    public void initialize(){
        Square[][] board = new Square[40][40];
        List<Square> snake = new ArrayList<>();

        int count = 0, i, j;
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, (i + count) % 2 == 0);
                count++;

                if (i == 10 && j >= 10 && j <= 12) {
                    board[i][j].setDirection(Direction.RIGHT);
                    snake.add(0, board[i][j]);
                }
            }
        }

        gameController = new GameController(board, snake);
    }

    @Test
    public void testGameController(){
        assertNotNull(gameController);
    }

    @Test
    public void testBoard(){
        assertNotNull(gameController.board);
    }

    @Test
    public void testSnake(){
        assertNotNull(gameController.snake);
    }

    @Test
    public void testGetScore(){
        assertEquals(0, gameController.getScore() );
    }

    @Test
    public void testSetScore(){
        gameController.setScore(10);
        assertEquals(10, gameController.getScore());
    }

    @Test
    public void testChangeDirectionDown(){
        gameController.snake.add(new Square(3,3, true));
        gameController.changeDirection(DOWN);
        assertEquals(DOWN, gameController.snake.get(0).getDirection());
    }
    @Test
    public void testChangeDirectionUp(){
        gameController.snake.add(new Square(3,3, true));
        gameController.changeDirection(UP);
        assertEquals(UP, gameController.snake.get(0).getDirection());
    }
    @Test
    public void testChangeDirectionRight(){
        gameController.snake.add(new Square(3,3, true));
        gameController.changeDirection(RIGHT);
        assertEquals(RIGHT, gameController.snake.get(0).getDirection());
    }
    @Test
    public void testChangeDirectionLeft(){
        gameController.snake.add(new Square(3,3, true));
        gameController.changeDirection(LEFT);
        assertNotEquals(LEFT, gameController.snake.get(0).getDirection());
    }


    @Test
    public void testNoNextMove() {
        gameController.snake.clear();
        gameController.snake.add(new Square(0,0,true, LEFT));
        gameController.snake.add(new Square(0,1,true, LEFT));
        gameController.changeDirection(LEFT);
        assertEquals(false, gameController.nextMove());
    }

    @Test
    public void testHasNextMove(){
        gameController.snake.clear();
        gameController.snake.add(new Square(20, 20,true, RIGHT));
        gameController.snake.add(new Square(20,19,true, RIGHT));
        gameController.snake.add(new Square(20,18,true, RIGHT));
        assertEquals(true, gameController.nextMove());
    }


}