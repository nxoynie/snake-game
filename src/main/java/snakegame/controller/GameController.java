package snakegame.controller;

import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;
import snakegame.model.Direction;
import snakegame.model.Square;

import javax.inject.Inject;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import static snakegame.model.Direction.*;

/**
 * This class control's the game and contains the game logic.
 */
@Slf4j
public class GameController {
    /**
     * The game board.
     */
    private final Square[][] board;
    /**
     * List of squares where the snake is currently at. The square at index 0 is always the head, and snake.size()-1 is always the tail.
     */
    private final List<Square> snake;

    /**
     * Constructor of this class.
     * @param board board
     * @param snake snake
     */
    public GameController(Square[][] board, List<Square> snake) {
        this.board = board;
        this.snake = snake;
    }

    /**
     * Changes the direction where the head of the snake should move.
     * @param direction direction
     */
    public void changeDirection(Direction direction) {
        Square head = snake.get(0);
        if ((head.getDirection() == UP && direction == DOWN) ||
                (head.getDirection() == DOWN && direction == UP) ||
                (head.getDirection() == Direction.RIGHT && direction == LEFT) ||
                (head.getDirection() == LEFT && direction == Direction.RIGHT)) return;

        head.setDirection(direction);
    }

    /**
     * This method increments the games by performing the next move.
     * @return Return true is the move was correct, otherwise gameover.
     */
    public boolean nextMove() {
        /**
         * Identifies the row and column of the snake's head.
         */
        int row = snake.get(0).getRow();
        int column = snake.get(0).getColumn();

        Square nextSquare = null;

        boolean ateTheApple = false;
        /**
         * Determines in each case where the snake should move, if the snake is trying to move off the board or into
         * itself then returns false and game over, otherwise instantiates the nextSquare and sets is to the the head.
         */
        switch (snake.get(0).getDirection()) {
            case UP -> {
                if (row == 0 || snake.contains(board[row - 1][column])) return false;
                nextSquare = board[row - 1][column];
                nextSquare.setDirection(snake.get(0).getDirection());
                snake.set(0, nextSquare);
            }
            case DOWN -> {
                if (row == board.length - 1 || snake.contains(board[row + 1][column])) return false;
                nextSquare = board[row + 1][column];
                nextSquare.setDirection(snake.get(0).getDirection());
                snake.set(0, nextSquare);
            }
            case RIGHT -> {
                if (column == board[0].length - 1 || snake.contains(board[row][column + 1])) return false;
                nextSquare = board[row][column + 1];
                nextSquare.setDirection(snake.get(0).getDirection());
                snake.set(0, nextSquare);
            }
            case LEFT -> {
                if (column == 0 || snake.contains(board[row][column - 1])) return false;
                nextSquare = board[row][column - 1];
                nextSquare.setDirection(snake.get(0).getDirection());
                snake.set(0, nextSquare);
            }
        }
        /**
         * If the nextSquare is an apple the snake ate it so it sets the ateTheApple boolean to be true.
         */
        if (nextSquare.isApple()) {

            nextSquare.setApple(false);

            ateTheApple = true;
        }
        /**
         * Loops through the body of the snake except the tail and moves i body part to the nextSquare.
         */
        for (int i = 1; i < snake.size() - 1; i++) {
            switch (snake.get(i).getDirection()) {
                case UP:
                    nextSquare = board[snake.get(i).getRow() - 1][snake.get(i).getColumn()];
                    break;
                case DOWN:
                    nextSquare = board[snake.get(i).getRow() + 1][snake.get(i).getColumn()];
                    break;
                case RIGHT:
                    nextSquare = board[snake.get(i).getRow()][snake.get(i).getColumn() + 1];
                    break;
                case LEFT:
                    nextSquare = board[snake.get(i).getRow()][snake.get(i).getColumn() - 1];
                    break;
            }

            snake.set(i, nextSquare);
        }

        Square tail = snake.get(snake.size() - 1);

        switch (tail.getDirection()) {
            case UP:
                nextSquare = board[tail.getRow() - 1][tail.getColumn()];
                break;
            case DOWN:
                nextSquare = board[tail.getRow() + 1][tail.getColumn()];
                break;
            case RIGHT:
                nextSquare = board[tail.getRow()][tail.getColumn() + 1];
                break;
            case LEFT:
                nextSquare = board[tail.getRow()][tail.getColumn() - 1];
                break;
        }
        /**
         * Moves the  tail.
         */
        snake.set(snake.size() - 1, nextSquare);
        /**
         * If the snake has eaten then increases the length of the snake by adding to it.
         */
        if (ateTheApple) {
            snake.add(tail);
            /**
             * Finds a random place for a new apple.
             */
            Random random = new Random();
            int r, c;
            while (true) {
                r = random.nextInt(board.length);
                c = random.nextInt(board[0].length);
                if (!snake.contains(board[r][c])) {
                    board[r][c].setApple(true);
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Repaints the board to represent the current state of the game.
     */
    public void paintBoard() {
        for (Square[] row : board) {
            for (Square square : row) {
                if (square == null) {
                    System.out.println("Square is null");
                    return;
                }
                if (snake.contains(square)) {
                    square.setStyle("-fx-background-color: black;");
                    continue;
                }
                if (square.isApple()) {
                    square.setStyle("-fx-background-color: red;");
                    continue;
                }
                square.setStyle("-fx-background-color: " + (square.isNewSquare() ? "rgb(0, 176, 88)" : "rgb(0, 176, 88)") + ";");
            }


        }
    }
}