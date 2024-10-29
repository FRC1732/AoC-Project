package aoc.project.year2023.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

import com.google.common.math.LinearTransformation;

public class Day11Puzzle {
    public static void main(String[] args) {
        Day11Puzzle puzzle = new Day11Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2023, 11);

        puzzle.part1();
        //puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 11 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day11Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 11 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day11Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public char[][] getStarArray(List<String> lines) {
        ArrayList<String> newList = new ArrayList<>();
        String testString = ".".repeat(lines.get(0).length());
        for (int i = 0; i < lines.size(); i++) {
            String lineAt = lines.get(i);
            if (lineAt.equals(testString)) {
                newList.add(lineAt);
            } 
            newList.add(lineAt);
        }

        char[][] starArray = new char[newList.size()][newList.get(0).length()];
        for (int i = 0; i < newList.size(); i++) {
            String lineAt = newList.get(i);
            for (int j = 0; j < lineAt.length(); j++) {
                starArray[i][j] = lineAt.charAt(j);
            }
        }

        ArrayList<Integer> jumpPoints = new ArrayList<>();
        for (int j = 0; j < starArray[0].length; j++) {
            boolean allDots = true;
            for (int i = 0; i < starArray.length; i++) {
                if (starArray[i][j] != '.') {
                    allDots = false;
                }
            }
            
            if (allDots) {
                jumpPoints.add(j);
            }
        }

        // for (int i = 0; i < starArray.length; i++) {
        //     for (int j = 0; j < starArray[0].length; j++) {
        //         System.out.print(starArray[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // jumpPoints.forEach(System.out::println);

        char[][] updatedStarArray = new char[starArray.length][starArray[0].length + jumpPoints.size()];
        int offset = 0;
        for (int i = 0; i < updatedStarArray.length; i++) {
            offset = 0;
            boolean nextZero = false;
            for (int j = 0; j < updatedStarArray[0].length; j++) {  
                if (nextZero) {
                    nextZero = false;
                    updatedStarArray[i][j] = '.';
                } else if (jumpPoints.indexOf(j - offset) != -1) {
                    updatedStarArray[i][j] = '.';
                    nextZero = true;
                    offset++;
                } else {
                    updatedStarArray[i][j] = starArray[i][j - offset];
                }

                // for (int a = 0; a < updatedStarArray[0].length; a++) {
                //     System.out.print(updatedStarArray[i][a]);
                // }
                // System.out.println();
            }



        }


        // for (int i = 0; i < updatedStarArray.length; i++) {
        //     for (int j = 0; j < updatedStarArray[0].length; j++) {
        //         System.out.print(updatedStarArray[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return updatedStarArray;
    }

    public long doPart1(List<String> lines) {
        // Part 1 code goes here
        char[][] starArray = getStarArray(lines);

        HashMap<Integer, int[]> pairings = new HashMap<Integer, int[]>();

        int topStar = 1;
        for (int i = 0; i < starArray.length; i++) {
            for (int j = 0; j < starArray[0].length; j++) {
                if (starArray[i][j] == '#') {
                    int[] newCords = new int[2];
                    newCords[1] = i; // y            
                    newCords[0] = j; // x
                    pairings.put(topStar, newCords);
                    topStar++;
                }
            }
        }
        topStar--;

        pairings.forEach((key, value) -> {System.out.println("key: " + key + " - " + "x: " + value[0] + " y: " + value[1]);});

        int sum = 0;
        for (int i = 1; i < topStar; i++) {
            int[] firstStarCords = pairings.get(i);
            for (int j = i + 1; j <= topStar; j++) {
                int[] secondStarCords = pairings.get(j);
                int addSum = Math.abs(firstStarCords[0] - secondStarCords[0]) + Math.abs(firstStarCords[1] - secondStarCords[1]);
                sum += addSum;
            }

        }
        return sum;
    }

    public long doPart2(List<String> lines) {
        // Part 2 code goes here
        return -1;
    }

}
