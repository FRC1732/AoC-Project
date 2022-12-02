package aoc.project.year2020.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day04Puzzle {
    public static void main(String[] args) {
        Day04Puzzle puzzle = new Day04Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2020, 4);

        puzzle.part1();
        //puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 04 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2020/day04Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 04 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2020/day04Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
        List<String> passport = new ArrayList<String>();
        int j = 0;
        int i = 0;
        boolean done = false;
        while (!done) {
            boolean done2 = false;
            String entry = "";
            while(!done2) {
                if(lines.get(j).isEmpty()) {
                    done2 = true;
                } else {
                    entry = entry + " " +lines.get(j);
                }
                if (lines.indexOf(lines.get(j))==lines.size()-1) {
                    done2 = true;
                    done = true;
                }
                j++;
            }
            passport.add(i,entry);
            i++;
        }

        //regex time
        int count = 0;
        Pattern p = Pattern.compile(" (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*)");
        Pattern f = Pattern.compile(" (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*) (\\w*):(\\S*)");
        for (int k = 0; k < passport.size(); k++) {
            Matcher m1 = p.matcher(passport.get(k));
            Matcher m2 = f.matcher(passport.get(k));  
            if(m2.find()||m1.find()&& passport.get(k).indexOf("cid") != -1) {
                count++;
            }
        }
        return count;
    }

    public long doPart2(List<String> lines) {
        // Part 2 code goes here
        return -1;
    }

}
