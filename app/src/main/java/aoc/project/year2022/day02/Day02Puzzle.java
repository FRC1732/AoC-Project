package aoc.project.year2022.day02;

import java.util.List;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day02Puzzle {
    public static void main(String[] args) {
        Day02Puzzle puzzle = new Day02Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2022, 2);

        puzzle.part1();

        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 02 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2022/day02Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 02 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2022/day02Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
        int totalScore = 0;
        for (String line : lines) {
            char opponent = (char) (line.charAt(0) - 64);
            char yours = (char) (line.charAt(2) - 23 - 64);
            if (opponent == yours) { // if draw
                totalScore += 3;
                totalScore += yours;
            }
            if (opponent - yours == -1 || opponent - yours == 2) { // if win
                totalScore += 6;
                totalScore += yours;
            }
            if (opponent - yours == -2 || opponent - yours == 1) { // if lose
                totalScore += 0;
                totalScore += yours;
            }
        }
        return totalScore;
    }

    public long doPart2(List<String> lines) {
        int totalScore = 0;
        for (String line : lines) {
            char opponent = (char) (line.charAt(0) - 64);
            char yours = (char) (line.charAt(2) - 23 - 64);
            if (opponent == 1 && yours == 1) {
                totalScore += 3;
            }
            if (opponent == 1 && yours == 2) {
                totalScore += 4;
            }
            if (opponent == 1 && yours == 3) {
                totalScore += 8;
            }
            if (opponent == 2 && yours == 1) {
                totalScore += 1;
            }
            if (opponent == 2 && yours == 2) {
                totalScore += 5;
            }
            if (opponent == 2 && yours == 3) {
                totalScore += 9;
            }
            if (opponent == 3 && yours == 1) {
                totalScore += 2;
            }
            if (opponent == 3 && yours == 2) {
                totalScore += 6;
            }
            if (opponent == 3 && yours == 3) {
                totalScore += 7;
            }
        }
        return totalScore;
    }

}
