package snakegame.model;
import javafx.scene.control.Label;
import snakegame.model.Direction;

/**
 * The Square class defines {@link Label} with the specified size.
 */
public class Square extends Label {
    /**
     * Stores the Square's location in the 2D array.
     */
    private final int row;
    private final int column;

    private final boolean color;
    /**
     * Determines if a square is an apple square or not.
     */
    private boolean apple;

    /**
     * The direction where the snake body should go it's on this particular square.
     */
    private Direction direction;

    /**
     * Constructor of the Square class.
     * @param row row
     * @param column column
     * @param color color
     */
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