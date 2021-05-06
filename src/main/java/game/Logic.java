package game;

import java.util.List;

public class Logic {
    private final Board[][] board;

    private final List<Board> snake;

    public Logic(Board[][] board, List<Board> snake) {
        this.board = board;
        this.snake = snake;
    }
    public void changeDirection(Directions direction) {
        Board head = snake.get(0);
        if (head.getDirection() == direction.getOpposite());

        head.setDirection(direction);
    }
}

