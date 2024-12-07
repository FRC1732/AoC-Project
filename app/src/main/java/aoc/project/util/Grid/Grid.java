package aoc.project.util.Grid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Grid<T extends Object> {
    private LinkedList<LinkedList<T>> internalGrid;

    private int rows;
    private int columns;

    public Grid(int rows, int columns) {
        LinkedList<LinkedList<T>> list = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            LinkedList<T> row = new LinkedList<>();
            list.add(row);

            for (int j = 0; j < columns; j++) {
                row.add(null);
            }
        }

        this.rows = rows;
        this.columns = columns;

        internalGrid = list;
    }

    public int getRowSize() {
        return rows;
    }

    public int getColumnSize() {
        return columns;
    }

    public boolean isValidCord(int row, int column) {
        return row >= 0 && column >= 0 && row < getRowSize() && column < getColumnSize();
    }

    public T getElement(int row, int column) throws IndexOutOfBoundsException {
        if (isValidCord(row, column)) {
            return internalGrid.get(row).get(column);
        } else {
            throw(new IndexOutOfBoundsException());
        }
    }

    public T replaceElement(int row, int column, T replaceWith) {
        if (!isValidCord(row, column)) {
            return null;
        }

        T oldElement = internalGrid.get(row).set(column, replaceWith);
        return oldElement;
    }

    public ArrayList<T> getRow(int row) {
        ArrayList<T> newArr = new ArrayList<>();
        newArr.addAll(internalGrid.get(row));
        return newArr;
    }

    public ArrayList<T> getColumn(int col) {
        ArrayList<T> newArr = new ArrayList<>();
        for (int i = 0; i < internalGrid.size(); i++) {
            newArr.add(getElement(i, col));
        }

        return newArr;
    }

    public Coordinates findInstance(T lookFor) {
        for (int i = 0; i < internalGrid.size(); i++) {
            for (int j = 0; j < internalGrid.get(0).size(); j++) {
                // System.out.println(lookFor + "-" + getElement(i, j));
                if (lookFor.equals(getElement(i, j))) {
                    return new Coordinates(i, j);
                }
            }
        }

        return new Coordinates(-1, -1);
    }

    public T getElement(Coordinates coords) {
        return getElement(coords.row(), coords.column());
    }

    public void printGrid(Consumer<T> action) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                action.accept(getElement(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }
}
