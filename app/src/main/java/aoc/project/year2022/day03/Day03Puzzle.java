package aoc.project.year2022.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        puzzle.part2();
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
        
        for (int i = 0; i < lines.size(); i++) {
            boolean breaks = false;
            String upper = lines.get(i).substring(0, lines.get(i).length()/2);
            String lower = lines.get(i).substring(lines.get(i).length()/2);
            for (int j = 0; j < upper.length(); j++) {
                for (int k = 0; k < upper.length(); k++) {
                    if (lower.charAt(k) == upper.charAt(j)) {
                        if (upper.charAt(j) < 95) {
                            sum += upper.charAt(j)-38;
                        } else {
                            sum += upper.charAt(j) - 96;
                        }
                        breaks = true;
                        break;
                    }
                    if (breaks) {
                        break;
                    }
                }
                if (breaks) {
                    break;
                }
            }
        }
        return sum;
    }

    public long doPart2(List<String> lines) {
        int sum = 0;
        ArrayList<String[]> groups = new ArrayList<String[]>();
        for (int i = 0; i < lines.size(); i += 3) {
            String[] strs = {lines.get(i), lines.get(i+1), lines.get(i+2)};
            groups.add(strs);
        }
        for(int i = 0; i < groups.size(); i++) {
            Arrays.sort(groups.get(i), Comparator.comparing(String::length));
            boolean done = false;
            char c = '.';
            for(int k = 0; k < groups.get(i)[0].length(); k++) {
                for (int j = 0; j < groups.get(i)[1].length(); j++) {
                    if (groups.get(i)[0].charAt(k) == groups.get(i)[1].charAt(j)) {
                        for(int l = 0; l < groups.get(i)[2].length(); l++) {
                            if (groups.get(i)[0].charAt(k) == groups.get(i)[2].charAt(l)) {
                                done = true;
                                c = groups.get(i)[0].charAt(k);
                                break;
                            }
                        }
                    }
                    if(done) {
                        break;
                    }
                }
                if (done) {
                    break;
                }
            }
            if (c < 95) {
                sum += c-38;
            } else {
                sum += c-96;
            } 
        }
        return sum;
    }

}
