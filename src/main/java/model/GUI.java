package model;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import model.Board;
import model.Directions;
import snakegame.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUI {
    public static Scene getGameScene() {
        GridPane gridPane = new GridPane();
        Board[][] board = new Board[30][30];

        List<Board> snake = new ArrayList<>();

        int count = 0, row, col;
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board.length; col++) {
                board[row][col] = new Board(row, col, (count + col) % 2 == 0);
                count++;
                gridPane.add(board[row][col], row, col);

                if (row == 10 && col >= 10 && col <= 12) {
                    board[col][row].setDirection(Directions.UP);
                    snake.add(0, board[row][col]);
                }
            }
        }
        Random random = new Random();
        int a, b;
        while (true) {
            a = random.nextInt(30);
            b = random.nextInt(30);
            if (!snake.contains(board[a][b])) {
                board[a][b].setFood(true);
                break;
            }
        }
        Logic game = new Logic(board, snake);
        game.newBoardSetUp();
        Scene scene = new Scene(gridPane);

        List<String> input = new ArrayList<>();

        scene.setOnKeyPressed(keyEvent -> {
            String code = keyEvent.getCode().toString();
            if (input.isEmpty()) {
                input.add(code);
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            String code = keyEvent.getCode().toString();
            input.remove(code);
        });
        final long[] last = {System.nanoTime()};

        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                if (!input.isEmpty()) {
                    game.changeDirection(Directions.valueOf(input.get(0)));
                }
                double neednames = (currentTime - last[0]) / 1000000000.0;

                if (neednames >= 0.2) {
                    last[0] = System.nanoTime();
                    boolean move = game.theNextMove();
                    game.newBoardSetUp();

                    if (!move) {
                        gridPane.setDisable(true);
                        stop();
                    }
                }


            }
        }.start();

        return scene;
    }
}