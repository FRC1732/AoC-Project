package aoc.project.year2020.day03;

import java.util.List;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day03Puzzle {
    public static void main(String[] args) {
        Day03Puzzle puzzle = new Day03Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2020, 3);

        puzzle.part1();
        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 03 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2020/day03Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 03 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2020/day03Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
        int count = 0;
        int x =0;
        for (int y= 0; y < lines.size(); y++) {
            if(lines.get(y).charAt(x)== '#') {
                count++;
            }
            x=x+3;
            if(x>=31) {
               x = x-31;
            }
        }
        return count;
    }

    public long doPart2(List<String> lines) {
        int count = 0;
        int total=0;
        int x = 0;
        int[] xV = {1,3,5,7,1};
        for (int j = 0; j <5; j++) {
           int  yV = 1;
            if (j == 5) {
                yV = 2;
            }
            for (int y= 0; y < lines.size(); y = y + yV) {
              if(lines.get(y).charAt(x)== '#') {
                   count++;
                }
              x = x + xV[j];
             if(x>=31) {
                   x = x-31;
               }
            }
            x=0;
            if (j == 0) {
                total = count;
            } else {
                total = total * count;
            }
            count = 0;
            }
        return total;
    }

}
