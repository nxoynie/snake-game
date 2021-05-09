package model;

public enum Directions {
    UP,
    DOWN,
    RIGHT,
    LEFT;
    private Directions opposite;
    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        RIGHT.opposite = LEFT;
        LEFT.opposite = RIGHT;
    }

    public Directions getOpposite() {
        return opposite;
    }
}
