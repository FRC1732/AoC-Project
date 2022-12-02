package aoc.project.year2022.day01;

import java.util.ArrayList;
import java.util.List;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

public class Day01Puzzle {
    public static void main(String[] args) {
        Day01Puzzle puzzle = new Day01Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2022, 1);

        puzzle.part1();
        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 01 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2022/day01Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 01 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2022/day01Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
        ArrayList<Integer> calories = new ArrayList<Integer>();
        int count = 0;
        calories.add(0);
        for (int i = 0; i < lines.size(); i++) {
            if(StringUtils.isEmpty(lines.get(i))) {
                count++;
                calories.add(0);
            } else {
            calories.set(count, calories.get(count) + Integer.parseInt(lines.get(i)));
            }
        }
        int largest = calories.get(0);
        for (int i = 1; i < calories.size(); i++) {
            if (calories.get(i) > largest) {
                largest = calories.get(i);
            }
        }
        return largest;
    }

    public long doPart2(List<String> lines) {
        ArrayList<Integer> calories = new ArrayList<Integer>();
        int count = 0;
        calories.add(0);
        for (int i = 0; i < lines.size(); i++) {
            if(StringUtils.isEmpty(lines.get(i))) {
                count++;
                calories.add(0);
            } else {
            calories.set(count, calories.get(count) + Integer.parseInt(lines.get(i)));
            }
        }
        return searchAndRemove(calories) + searchAndRemove(calories) + searchAndRemove(calories);
        
    }

    public int searchAndRemove(List<Integer> calories) {
        int firstLargest = calories.get(0);
        int largestIndex = 0;
        for (int i = 1; i < calories.size(); i++) {
            if (calories.get(i) > firstLargest) {
                firstLargest = calories.get(i);
                largestIndex = i;
            }
        }
        calories.remove(largestIndex);
        return firstLargest;
    }

}
