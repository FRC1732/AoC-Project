package aoc.project.util.Grid;

import java.util.ArrayList;
import java.util.LinkedList;

public class Grid<T extends Object> {
    private LinkedList<LinkedList<T>> internalGrid;

    public Grid(int rows, int columns) {
        LinkedList<LinkedList<T>> list = new LinkedList<>();

        for (int i = 0; i < columns; i++) {
            list.add(new LinkedList<>());
        }

        internalGrid = list;
    }

    public int getRowSize() {
        return internalGrid.size();
    }

    public int getColumnSize() {
        return internalGrid.get(0).size();
    }

    public boolean isValidCord(int row, int column) {
        return row >= 0 && column >= 0 && row < getRowSize() && column < getColumnSize();
    }

    public T getElement(int row, int column) throws IndexOutOfBoundsException {
        if (isValidCord(row, column)) {
            return internalGrid.get(row).get(column);
        } else {
            throw (new IndexOutOfBoundsException());
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
}
