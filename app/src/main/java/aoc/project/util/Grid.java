package aoc.project.util;

public class Grid<T extends Object> {
    private T[][] internalGrid;

    public Grid(int rows, int columns) {
        internalGrid = new T[rows][columns];
    }
}
