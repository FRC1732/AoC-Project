package aoc.project.year2023.day21;

import java.util.LinkedList;
import java.util.List;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;
import aoc.project.util.Grid.Coordinates;
import aoc.project.util.Grid.Direction;
import aoc.project.util.Grid.Grid;
import aoc.project.util.Grid.GridPointer;
import aoc.project.util.Grid.GridUtils;

import org.apache.commons.lang3.time.StopWatch;

public class Day21Puzzle {
    public static void main(String[] args) {
        Day21Puzzle puzzle = new Day21Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2023, 21);

        puzzle.part1();
        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 21 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day21Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 21 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day21Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public boolean canMoveIntoTile(Grid<Character> grid, Coordinates coords) {
        // System.out.println(grid.getElement(coords));
        if (!grid.getElement(coords).equals('O')) {
            return true;
        }
        return false;
    }

    public void updateTileCheck(GridPointer<Character> pointer, LinkedList<Coordinates> newCoords, Grid<Character> grid,
            Direction direction) {
        pointer.setDirection(direction);
        pointer.moveAccordingToDirection(1);

        Coordinates coords = pointer.getCoordinates();
        if (grid.isValidCord(coords.row(), coords.column()) && canMoveIntoTile(grid, coords)) {
            grid.replaceElement(coords.row(), coords.column(), 'O');
            newCoords.add(coords);
        }
        pointer.returnToBenchmark();
    }

    public long doPart1(List<String> lines) {
        Grid<Character> grid = GridUtils.parseStringInput(lines);

        Coordinates startPos = grid.findInstance('S');
        GridPointer<Character> pointer = GridUtils.createPointer(grid, startPos.row(), startPos.column(),
                Direction.NORTH);


        LinkedList<Coordinates> currentValidTiles = new LinkedList<>();

        currentValidTiles.add(startPos);

        int STEP_AMOUNT = 64;
        for (int i = 0; i < STEP_AMOUNT; i++) {
            // grid.printGrid(System.out::print);
            LinkedList<Coordinates> newCoords = new LinkedList<>();

            for (Coordinates coord : currentValidTiles) {
                grid.replaceElement(coord.row(), coord.column(), '.');
            }

            for (Coordinates coord : currentValidTiles) {
                pointer.setPointerPosition(coord.row(), coord.column());
                pointer.setBenchmark();

                updateTileCheck(pointer, newCoords, grid, Direction.EAST);
                updateTileCheck(pointer, newCoords, grid, Direction.WEST);
                updateTileCheck(pointer, newCoords, grid, Direction.SOUTH);
                updateTileCheck(pointer, newCoords, grid, Direction.NORTH);
            }

            currentValidTiles = newCoords;
        }
        // Part 1 code goes here
        return currentValidTiles.size();
    }

    public long doPart2(List<String> lines) {
        Grid<Character> grid = GridUtils.parseStringInput(lines);

        Coordinates startPos = grid.findInstance('S');
        GridPointer<Character> pointer = GridUtils.createPointer(grid, startPos.row(), startPos.column(),
                Direction.NORTH);


        LinkedList<Coordinates> currentValidTiles = new LinkedList<>();

        currentValidTiles.add(startPos);

        int STEP_AMOUNT = 250;
        int last = 0;
        for (int i = 0; i < STEP_AMOUNT; i++) {
            // grid.printGrid(System.out::print);

            // System.out.println(currentValidTiles.size());
            System.out.println(i + 1 + ": " + currentValidTiles.size() + "-" + (i+1) * (i+1));

            // System.out.println(currentValidTiles.size() + last);
            last = currentValidTiles.size();

            LinkedList<Coordinates> newCoords = new LinkedList<>();

            for (Coordinates coord : currentValidTiles) {
                grid.replaceElement(coord.row(), coord.column(), '.');
            }

            for (Coordinates coord : currentValidTiles) {
                pointer.setPointerPosition(coord.row(), coord.column());
                pointer.setBenchmark();

                updateTileCheck(pointer, newCoords, grid, Direction.EAST);
                updateTileCheck(pointer, newCoords, grid, Direction.WEST);
                updateTileCheck(pointer, newCoords, grid, Direction.SOUTH);
                updateTileCheck(pointer, newCoords, grid, Direction.NORTH);
            }

            currentValidTiles = newCoords;
        }
        return currentValidTiles.size();
    }

}
