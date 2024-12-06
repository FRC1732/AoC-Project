package aoc.project.util.Grid;

import java.util.List;

public class GridUtils {

    public static Grid<Character> parseStringInput(List<String> lines) {
        Grid<Character> newGrid =  new Grid<>(lines.size(), lines.get(0).length());

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                newGrid.replaceElement(i, j, lines.get(i).charAt(j));
            }
        }

        return newGrid;
    }

    
    public <T> GridPointer<T> createPointer(Grid<T> grid, int row, int col, Direction dir) {
        return new GridPointer<T>(grid, row, col, dir);
    }
}
