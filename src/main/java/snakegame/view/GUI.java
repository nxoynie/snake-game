package snakegame.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import lombok.SneakyThrows;
import snakegame.Dao.HighScoreDao;
import snakegame.Dao.Score;
import snakegame.controller.GameController;
import snakegame.model.Direction;
import snakegame.model.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is the Graphical User Interface Class responsible for building the game board for the user.
 */
public class  GUI {
    /**
     * Getter for the GameScene.
     * @param playername playername
     * @return scene
     */
    @SneakyThrows
    public static Scene getGameScene(String playername) {
        /**
         * The board stored in GridPane and a 2D array.
         */
        GridPane grid = new GridPane();
        Square[][] board = new Square[40][40];


        /**
         * Identifying the squares on which the snake is at.
         */
        List<Square> snake = new ArrayList<>();

        /**
         * Loops through the board and initializes the squares.
         */
        int count = 0, i, j;
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, (i + count) % 2 == 0);
                count++;
                grid.add(board[i][j], j, i);

                /**
                 * Sets the snakes starting direction to be RIGHT.
                 */
                if (i == 10 && j >= 10 && j <= 12) {
                    board[i][j].setDirection(Direction.RIGHT);
                    snake.add(0, board[i][j]);
                }
            }
        }

        /**
         * Place the apple somewhere random on the board.
         */
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

        /**
         * Creates an instance of the GameController and passes it the board and the list of the snake body parts.
         */
        GameController snakeGame = new GameController(board, snake);


        snakeGame.paintBoard();


        Scene scene = new Scene(grid);


        List<String> input = new ArrayList<>();

        /**
         * Gets the inputs to store from the game scene.
         */
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

        /**
         * The game loop.
         */
        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {

                /**
                 * Changes the direction if the user requested it through a key press.
                 */
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
                        System.out.println(playername +" scored: " + snakeGame.getScore());
                        Score newScore = new Score(playername, String.valueOf(snakeGame.getScore()));
                        HighScoreDao highScoreDao = new HighScoreDao();
                        highScoreDao.addScore(newScore);
                        stop();
                    }
                }
            }
        }.start();

        return scene;

    }


}
