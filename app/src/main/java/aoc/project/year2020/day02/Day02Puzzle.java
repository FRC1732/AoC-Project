package aoc.project.year2020.day02;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day02Puzzle {
    private final String REGEX = "(\\d*)-(\\d*) (.): (\\w*)";
    private Pattern pattern = Pattern.compile(REGEX);
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
        int valid = 0;
        for (String string : lines) {
            Matcher matcher = pattern.matcher(string);
            matcher.find();
            int groupOne = Integer.parseInt(matcher.group(1));
            int groupTwo = Integer.parseInt(matcher.group(2));
            char groupThree = matcher.group(3).charAt(0);
            String groupFour = matcher.group(4);
            int min = groupOne;
            int max = groupTwo;
            char key = groupThree;
            String password = groupFour;
            int occurences = 0;
            for (int i = 0; i < password.length(); i++) {
                if (key == password.charAt(i)) {
                    occurences++;
                }
            }
            if (occurences >= min && occurences <= max) {
                valid++;
            }
        }
        return valid;
    }

    public long doPart2(List<String> lines) {
        int valid = 0;
        for (String string : lines) {
            Matcher matcher = pattern.matcher(string);
            matcher.find();
            int groupOne = Integer.parseInt(matcher.group(1));
            int groupTwo = Integer.parseInt(matcher.group(2));
            char groupThree = matcher.group(3).charAt(0);
            String groupFour = matcher.group(4);
            int positionOne = groupOne;
            int positionTwo = groupTwo;
            char key = groupThree;
            String password = groupFour;
            if (password.charAt(positionOne-1) == key ^ password.charAt(positionTwo-1) == key) {
                valid++;
            }
        }        
        return valid;
    }

}
