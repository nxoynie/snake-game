package snakegame.model;

import javafx.scene.control.Label;

public class Square extends Label {

    private final int row;
    private final int column;

    private final boolean white;

    private boolean apple;


    private Direction direction;


    public Square(int row, int column, boolean white) {
        super();
        this.row = row;
        this.column = column;
        this.white = white;
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

    public boolean isWhite() {
        return white;
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