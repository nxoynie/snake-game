package snakegame;

import model.Board;
import model.Directions;

import java.util.List;
import java.util.Random;

public class Logic {
    private final Board[][] board;

    private final List<Board> snake;

    public Logic(Board[][] board, List<Board> snake) {
        this.board = board;
        this.snake = snake;
    }
    public void changeDirection(Directions direction) {
        Board head = snake.get(0);
        if (head.getDirection() == direction.getOpposite()) return;

        head.setDirection(direction);
    }

    public boolean theNextMove() {
        int row = snake.get(0).getRow();
        int column = snake.get(0).getColumn();

        Board nextSquare = null;

        boolean eatenFood = false;

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
        if (nextSquare.isFood()) {
            nextSquare.setFood(false);
            eatenFood = true;
        }
        for (int i = 1; i < snake.size() - 1; i++) {
            nextSquare = switch (snake.get(i).getDirection()) {
                case UP -> board[snake.get(i).getRow() - 1][snake.get(i).getColumn()];
                case DOWN -> board[snake.get(i).getRow() + 1][snake.get(i).getColumn()];
                case LEFT -> board[snake.get(i).getRow()][snake.get(i).getColumn() + 1];
                case RIGHT -> board[snake.get(i).getRow()][snake.get(i).getColumn() - 1];
            };
            snake.set(i, nextSquare);
        }
        Board tail = snake.get(snake.size() - 1);
        nextSquare = switch (tail.getDirection()) {
            case UP -> board[tail.getRow() - 1][tail.getColumn()];
            case DOWN -> board[tail.getRow() + 1][tail.getColumn()];
            case RIGHT -> board[tail.getRow()][tail.getColumn() + 1];
            case LEFT -> board[tail.getRow()][tail.getColumn() - 1];
        };
        snake.set(snake.size() - 1, nextSquare);
        if (eatenFood) {
            snake.add(tail);

            Random random = new Random();
            int a, b;
            while (true) {
                a = random.nextInt(board.length);
                b = random.nextInt(board[0].length);
                if (!snake.contains(board[a][b])) {
                    board[a][b].setFood(true);
                    break;
                }
            }
        }
        return true;
    }
    public void newBoardSetUp() {
        for(Board[] row: board){
            for (Board square : row){
                if(square  == null ){
                    return;
                }
                if(snake.contains(square)){
                    square.setStyle("-fx-background-color: white;");
                    continue;
                }
                if (square.isFood()){
                    square.setStyle("-fx-background-color: red;");
                    continue;
                }
                square.setStyle("-fx-back-ground-color: black;");
            }
        }
    }
}

