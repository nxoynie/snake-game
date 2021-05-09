package model;

import javafx.scene.control.Label;

public class Board extends Label {
    private final int row;
    private final int column;
    private boolean food;
    private Directions direction;

    public Board(int row, int column, boolean b) {
        super();
        this.row = row;
        this.column = column;
        food = false;
        direction = null;
        setMaxSize(20,20);
        setMinSize(20,20);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Directions getDirection() {
        return direction;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }
}
