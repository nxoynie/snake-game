package snakegame.controller;

import javafx.scene.control.Label;
import snakegame.model.Direction;

public class Square extends Label {

    private final int row;
    private final int column;

    private final boolean color;

    private boolean apple;


    private Direction direction;


    public Square(int row, int column, boolean color) {
        super();
        this.row = row;
        this.column = column;
        this.color = color;
        apple = false;
        direction = null;
        setMaxHeight(15);
        setMaxWidth(15);
        setMinWidth(15);
        setMinHeight(15);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isNewSquare() {
        return color;
    }

    public boolean isApple() {
        return apple;
    }

    public void setApple(boolean apple) {
        this.apple = apple;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}