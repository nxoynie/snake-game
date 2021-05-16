package snakegame.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import snakegame.controller.GameController;
import snakegame.model.Direction;
import snakegame.controller.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUI {
    public static Scene getGameScene() {

        GridPane grid = new GridPane();
        Square[][] board = new Square[30][30];


        List<Square> snake = new ArrayList<>();


        int count = 0, i, j;
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, (i + count) % 2 == 0);
                count++;
                grid.add(board[i][j], j, i);


                if (i == 10 && j >= 10 && j <= 12) {
                    board[i][j].setDirection(Direction.RIGHT);
                    snake.add(0, board[i][j]);
                }
            }
        }


        Random random = new Random();
        int r, c;
        while (true) {
            r = random.nextInt(30);
            c = random.nextInt(30);
            if (!snake.contains(board[r][c])) {
                board[r][c].setApple(true);
                break;
            }
        }


        GameController snakeGame = new GameController(board, snake);


        snakeGame.paintBoard();


        Scene scene = new Scene(grid);


        List<String> input = new ArrayList<>();


        scene.setOnKeyPressed(keyEvent -> {
            String code = keyEvent.getCode().toString();
            if (input.size() == 0) {
                input.add(code);
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            String code = keyEvent.getCode().toString();
            input.remove(code);
        });


        final long[] lastTime = {System.nanoTime()};


        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {


                if (input.size() != 0) {
                    snakeGame.changeDirection(Direction.valueOf(input.get(0)));
                }

                double elapsedTime = (currentTime - lastTime[0]) / 1000000000.0;


                if (elapsedTime >= 0.2) {


                    lastTime[0] = System.nanoTime();


                    boolean move = snakeGame.nextMove();


                    snakeGame.paintBoard();


                    if (!move) {
                        grid.setDisable(true);
                        stop();
                    }
                }
            }
        }.start();

        return scene;

    }


}
