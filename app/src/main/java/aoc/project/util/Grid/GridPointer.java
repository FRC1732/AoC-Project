package aoc.project.util.Grid;

public class GridPointer<T extends Object> {
    private int pointerRow;
    private int pointerColumn;
    private Direction direction;

    private Grid<T> pointGrid;

    public GridPointer(Grid<T> pointGrid, int row, int col, Direction direction) {
        this.pointGrid = pointGrid;
        pointerRow = row;
        pointerColumn = col;
        this.direction = direction;
    }

    public void setDirection(Direction set) {
        direction = set;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setPointerPosition(int row, int col) {
        pointerRow = row;
        pointerColumn = col;
    }

    public T getItemAtPointer() {
        return pointGrid.getElement(pointerRow, pointerColumn);
    }

    public T replaceElementAtPointer(T replace) {
        return pointGrid.replaceElement(pointerRow, pointerColumn, replace);
    }

    public void shiftPointerPosition(int rowMove, int colMove) {
        pointerRow += rowMove;
        pointerRow += colMove;
    }

    public T shiftAndGetElement(int rowMove, int colMove) {
        shiftPointerPosition(rowMove, colMove);
        return getItemAtPointer();
    }

    public T getLocalElement(int rowAdjust, int colAdjust) {
        return pointGrid.getElement(pointerRow + rowAdjust, pointerColumn + colAdjust);
    }

    public void moveUp(int magnitude) {
        shiftPointerPosition(-magnitude, 0);
    }

    public void moveDown(int magnitude) {
        shiftPointerPosition(magnitude, 0);
    }

    public void moveRight(int magnitude) {
        shiftPointerPosition(0, magnitude);
    }

    public void moveLeft(int magnitude) {
        shiftPointerPosition(0, -magnitude);
    }

    public void moveAccordingToDirection(int magnitude) {
        switch (direction) {
            case EAST:
                moveRight(magnitude);
                break;
            case NORTH:
                moveUp(magnitude);
                break;
            case NORTHEAST:
                moveRight(magnitude);
                moveUp(magnitude);
                break;
            case NORTHWEST:
                moveLeft(magnitude);
                moveUp(magnitude);
                break;
            case SOUTH:
                moveDown(magnitude);
                break;
            case SOUTHEAST:
                moveRight(magnitude);
                moveDown(magnitude);
                break;
            case SOUTHWEST:
                moveLeft(magnitude);
                moveDown(magnitude);
                break;
            case WEST:
                moveLeft(magnitude);
                break;
            default:
                break;
        }
    }
}
