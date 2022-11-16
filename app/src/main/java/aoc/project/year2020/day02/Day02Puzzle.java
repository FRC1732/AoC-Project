package aoc.project.year2020.day02;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day02Puzzle {
    public static void main(String[] args) {
        Day02Puzzle puzzle = new Day02Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2020, 2);

        puzzle.part1();
        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 02 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2020/day02Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 02 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2020/day02Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
       int count = 13;
       for (int i = 0; i < lines.size(); i++) {
            int charStart, low, high;
            if (lines.get(i).charAt(1) != 45) {
               charStart = 2;
                low = lines.get(i).charAt(0)-48;
                low = low*10 + lines.get(i).charAt(1)-48;
                high = lines.get(i).charAt(3)-48;
                high = high*10 + lines.get(i).charAt(4)-48;
            } else if(lines.get(i).charAt(3) != ' '){
                charStart = 1;
                low = lines.get(i).charAt(0)-48;
                high = lines.get(i).charAt(2)-48;
                high = high*10 + lines.get(i).charAt(2)-48;
            } else {
                charStart = 0;
                low = lines.get(i).charAt(0)-48;
                high = lines.get(i).charAt(2)-48;
            }
            int charCount = 0;
            char c = lines.get(i).charAt(4+charStart);
            for (int j = 0; j < lines.get(i).substring(7+charStart).length(); j++) {
                if (lines.get(i).substring(7+charStart).charAt(j) == c) {
                    charCount++;
                }
            }
            if(low <= charCount && charCount <= high) {
                count++;
            }
         }
        return count;
    }

    public long doPart2(List<String> lines) {
        int count = 0;
        Pattern p = Pattern.compile("(\\d*)-(\\d*) (\\w*): (\\w*)$");
        for (int i = 0; i < lines.size(); i++) {
            Matcher m = p.matcher(lines.get(i));
            m.find();
            int low = Integer.parseInt(m.group(1));
            int high = Integer.parseInt(m.group(2));
            char c = m.group(3).charAt(0);
            if(m.group(3).charAt(0) == m.group(4).charAt(low-1) == !(m.group(3).charAt(0) == m.group(4).charAt(high-1))) {
                count++;
            }
        }
        return count;
    }

}
