package aoc.project.year2022.day03;

import java.util.List;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day03Puzzle {
    public static void main(String[] args) {
        Day03Puzzle puzzle = new Day03Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2022, 3);

        puzzle.part1();
        //puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 03 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2022/day03Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 03 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2022/day03Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
        int sum = 0;
        for (int i = 1; i < 2; i++) {
            String upper = lines.get(i).substring(0, lines.get(i).length()/2);
            String lower = lines.get(i).substring(lines.get(i).length()/2);
            for (int j = 0; j < upper.length(); j++) {
                for (int k = 0; k < upper.length(); k++) {
                    if (lower.charAt(k) == upper.charAt(j)) {
                        if (upper.charAt(j) < 95) {
                            sum += upper.charAt(j)-38;
                            System.out.println(lower.charAt(k) + " " + sum + " " + upper.charAt(j));
                        } else {
                            sum += upper.charAt(j) - 96;
                            System.out.println(lower.charAt(k) + " " + sum + " " + upper.charAt(j));
                        }
                    }
                }
            }
        }
        return sum;
    }

    public long doPart2(List<String> lines) {
        // Part 2 code goes here
        return -1;
    }

}
