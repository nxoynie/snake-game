package snakegame.controller;

import snakegame.model.Direction;
import snakegame.model.Square;

import java.util.List;
import java.util.Random;

import static snakegame.model.Direction.*;


public class GameController {


    private final Square[][] board;
    private final List<Square> snake;


    public GameController(Square[][] board, List<Square> snake) {
        this.board = board;
        this.snake = snake;
    }

    public void changeDirection(Direction direction) {
        Square head = snake.get(0);
        if ((head.getDirection() == UP && direction == DOWN) ||
                (head.getDirection() == DOWN && direction == UP) ||
                (head.getDirection() == Direction.RIGHT && direction == LEFT) ||
                (head.getDirection() == LEFT && direction == Direction.RIGHT)) return;

        head.setDirection(direction);
    }

    public boolean nextMove() {

        int row = snake.get(0).getRow();
        int column = snake.get(0).getColumn();

        Square nextSquare = null;

        boolean ateTheApple = false;

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

        if (nextSquare.isApple()) {

            nextSquare.setApple(false);

            ateTheApple = true;
        }

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

        snake.set(snake.size() - 1, nextSquare);

        if (ateTheApple) {

            snake.add(tail);

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

    public void paintBoard() {
        for (Square[] row : board) {
            for (Square square : row) {
                if (square == null) {
                    System.out.println("Square is null");
                    return;
                }
                if (snake.contains(square)) {
                    square.setStyle("-fx-background-color: green;");
                    continue;
                }
                if (square.isApple()) {
                    square.setStyle("-fx-background-color: red;");
                    continue;
                }
                square.setStyle("-fx-background-color: " + (square.isWhite() ? "rgb(200, 200, 200)" : "rgb(50, 50, 50)") + ";");
            }

        }
    }
}