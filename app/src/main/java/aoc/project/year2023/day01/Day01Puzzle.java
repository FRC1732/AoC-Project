package aoc.project.year2023.day01;

import java.util.LinkedList;
import java.util.List;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day01Puzzle {
    public static void main(String[] args) {
        Day01Puzzle puzzle = new Day01Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2023, 1);

        puzzle.part1();
        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 01 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day01Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 01 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day01Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
        int result = 0;
        for (int i = 0; i < lines.size(); i++) {
            String current = lines.get(i);
            LinkedList<Integer> nums = new LinkedList<Integer>();
            for(int j = 0; j < current.length(); j++) {
                if(Character.isDigit(current.charAt(j))) {
                    nums.add(Character.getNumericValue(current.charAt(j)));
                }
            }
            result += nums.getFirst()*10 + nums.getLast();
        }
        return result;
    }

    public long doPart2(List<String> lines) {
        int result = 0;
        String[] ints = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i = 0; i < lines.size(); i++) {
            String current = lines.get(i).toLowerCase();
            LinkedList<Integer> nums = new LinkedList<Integer>();
            for(int j = 0; j < current.length(); j++) {
                if(Character.isDigit(current.charAt(j))) {
                    nums.add(Character.getNumericValue(current.charAt(j)));
                } else {
                    for(int r = 1; r < 10; r++) {
                        if(current.substring(j).length() >= ints[r].length() && current.substring(j, j + ints[r].length()).equals(ints[r])) {
                            nums.add(r);
                        }
                    }
                }
            }
            result += nums.getFirst()*10 + nums.getLast();
        }
        return result;
    }
}


