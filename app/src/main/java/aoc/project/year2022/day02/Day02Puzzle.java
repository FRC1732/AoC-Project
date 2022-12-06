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
        int score = 0;
        for(int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            split[1] = Character.toString((char)(split[1].charAt(0)-23));
            score += split[1].charAt(0)-64;
            if(split[1].equals(split[0])){
                score += 3;
            } else if (split[0].charAt(0)-split[1].charAt(0) == -1 || split[0].charAt(0)-split[1].charAt(0) == 2){
                score += 6;
            } 
        }
        return score;
    }

    public long doPart2(List<String> lines) {
        int score = 0;
        for(int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            if(split[1].equals("X")) {
                score += 0;
            } else if(split[1].equals("Y")) {
                score += 3;
            } else {
                score += 6;
            }
            if (split[0].equals("A")) {
                if (split[1].equals("X")) {
                    score += 3;
                } else if (split[1].equals("Y")) {
                    score += 1;
                } else {
                    score += 2;
                }
            } else if (split[0].equals("B")) {
                if (split[1].equals("X")) {
                    score += 1;
                } else if (split[1].equals("Y")) {
                    score += 2;
                } else {
                    score += 3;
                }
            } else {
                if (split[1].equals("X")) {
                    score += 2;
                } else if (split[1].equals("Y")) {
                    score += 3;
                } else {
                    score += 1;
                }
            }
        }
            return score;
    }

}
