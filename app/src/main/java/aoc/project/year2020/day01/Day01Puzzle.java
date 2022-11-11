package aoc.project.year2020.day01;

import java.util.List;
import java.util.stream.Collectors;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day01Puzzle {
    public static void main(String[] args) {
        Day01Puzzle puzzle = new Day01Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2020, 1);

        puzzle.part1();
        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 01 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2020/day01Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 01 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2020/day01Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
        List<Integer> numbers = lines.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
       
        int num1, num2;
        for(int i = 0; i < numbers.size() -1; i++) {
            num1 = numbers.get(i);
            
            for (int j = i + 1; j < numbers.size(); j++) {
                num2 = numbers.get(j);
            
                if(num1+num2 == 2020) {
                    return num1 * num2; 
                }
   
            }
        }
        return -1;
    }

    public long doPart2(List<String> lines) {
        List<Integer> numbers = lines.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
       
        int num1, num2, num3;
        for(int i = 0; i < numbers.size() -1; i++) {
            num1 = numbers.get(i);
            
            for (int j = i + 1; j < numbers.size(); j++) {
                num2 = numbers.get(j);
            
                    for (int k = j+1; k < numbers.size(); k++) {
                        num3 = numbers.get(k); 
                        
                        if(num1+num2+num3 == 2020) {
                            return num1 * num2 * num3; 
                        }
                    }
            }
        }
        return -1;
    }

}
