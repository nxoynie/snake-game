package snakegame.controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.testfx.framework.junit.ApplicationTest;
import snakegame.SnakeGameApplication;
import snakegame.model.Direction;
import snakegame.model.Square;
import javax.swing.*;
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
        assertEquals(LEFT, gameController.snake.get(0).getDirection());
    }


   /* @Test
    public void testNoNextMove() {
        gameController.snake.add(new Square(0,3,true));
        gameController.snake.add(new Square(1,3,true));
        gameController.changeDirection(LEFT);
        assertEquals(false, gameController.nextMove());
    }

    @Test
    public void testHasNextMove(){
        gameController.snake.add(new Square(3,3,true));
        gameController.snake.add(new Square(3,4,true));
        gameController.changeDirection(DOWN);
        assertEquals(true, gameController.nextMove());
    }*/


}