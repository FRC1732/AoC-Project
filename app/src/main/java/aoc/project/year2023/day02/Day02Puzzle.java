package aoc.project.year2023.day02;

import java.util.List;

import aoc.project.Constants;
import aoc.project.util.AocUtil;
import aoc.project.util.FetchPuzzleInput;

import org.apache.commons.lang3.time.StopWatch;

public class Day02Puzzle { 
    public static void main(String[] args) {
        Day02Puzzle puzzle = new Day02Puzzle();
        FetchPuzzleInput fetchPuzzleInput = new FetchPuzzleInput(Constants.PATH_TO_PROJECT);
        fetchPuzzleInput.fetchPuzzleInput(2023, 2);

        puzzle.part1();
        puzzle.part2();
    }

    public void part1() {
        System.out.println("Executing Day 02 Part 1...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day02Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart1(lines);
        stopWatch.stop();

        System.out.println("  Part 1 result: " + result);
        System.out.println("  Part 1 completed in " + stopWatch.getTime() + " ms.");
    }

    public void part2() {
        System.out.println("Executing Day 02 Part 2...");
        List<String> lines = AocUtil
                .readFile("app/src/main/resources/puzzleInputs/year2023/day02Puzzle.txt");

        StopWatch stopWatch = StopWatch.createStarted();
        long result = doPart2(lines);
        stopWatch.stop();

        System.out.println("  Part 2 result: " + result);
        System.out.println("  Part 2 completed in " + stopWatch.getTime() + " ms.");
    }

    public long doPart1(List<String> lines) {
        int result = 0;
        String[] colors = {"red", "green", "blue"};
        for(int i = 0; i < lines.size(); i++) { //TODO: fix bounds
            boolean working = true;
            boolean possible = true;
            int[] cubeColors = {0,0,0}; //red, green, blue   
            String current = lines.get(i);
            int id = Integer.parseInt(current.substring(5, current.indexOf(":" )));
            current = current.substring(current.indexOf(":") + 2);
            while (working) {
                int numCubes = Character.getNumericValue(current.charAt(0));
                if(Character.isDigit(current.charAt(1))) {
                    current = current.substring(1);
                    numCubes = numCubes*10 + Character.getNumericValue(current.charAt(0));
                }
                current = current.substring(2);
                if(current.indexOf(";") < 10 && current.indexOf(";") > 0 ) {
                    boolean searching = true;
                    for(int j = 0; j < colors.length && searching; j++) {
                        if(current.substring(0, current.indexOf(";")).equals(colors[j])) {
                            cubeColors[j] += numCubes;
                            current = current.substring(current.indexOf(";")+2);
                            searching = false;
                        }
                    }
                    if (cubeColors[0] > 12 || cubeColors[1] > 13 || cubeColors[2] > 14) {
                        possible = false;
                    }
                    cubeColors[0] = 0;
                    cubeColors[1] = 0;
                    cubeColors[2] = 0;
                } else if (current.indexOf(",") > -1){
                    boolean searching = true;
                    for(int j = 0; j < colors.length && searching; j++) {
                        if(current.substring(0, current.indexOf(",")).equals(colors[j])) {
                            cubeColors[j] += numCubes;
                            current = current.substring(current.indexOf(", ") + 2);
                            searching = false; 
                        }
                    }
                } else {
                    for(int j = 0; j < colors.length; j++) {
                        if(current.equals(colors[j])) {
                            cubeColors[j] += numCubes;
                        }
                    }
                    if (cubeColors[0] > 12 || cubeColors[1] > 13 || cubeColors[2] > 14) {
                        possible = false;
                    }
                    working = false;
                }
            }
            if(possible) {
                result += id;
            }
        }
        return result;
    }

    public long doPart2(List<String> lines) {
        int result = 0;
        String[] colors = {"red", "green", "blue"};
        for(int i = 0; i < lines.size(); i++) {
            boolean working = true;
            int[] numColors = {0,0,0};
            int[] cubeColors = {0,0,0}; //red, green, blue   
            String current = lines.get(i);
            current = current.substring(current.indexOf(":") + 2);
            while (working) {
                int numCubes = Character.getNumericValue(current.charAt(0));
                if(Character.isDigit(current.charAt(1))) {
                    current = current.substring(1);
                    numCubes = numCubes*10 + Character.getNumericValue(current.charAt(0));
                }
                current = current.substring(2);
                if(current.indexOf(";") < 10 && current.indexOf(";") > 0 ) {
                    boolean searching = true;
                    for(int j = 0; j < colors.length && searching; j++) {
                        if(current.substring(0, current.indexOf(";")).equals(colors[j])) {
                            cubeColors[j] += numCubes;
                            current = current.substring(current.indexOf(";")+2);
                            searching = false;
                        }
                    }
                    if(numColors[0] < cubeColors[0]) {
                        numColors[0] = cubeColors[0];
                    }
                    if(numColors[1] < cubeColors[1]) {
                        numColors[1] = cubeColors[1];
                    }
                    if(numColors[2] < cubeColors[2]) {
                        numColors[2] = cubeColors[2];
                    }
                    cubeColors[0] = 0;
                    cubeColors[1] = 0;
                    cubeColors[2] = 0;
                } else if (current.indexOf(",") > -1){
                    boolean searching = true;
                    for(int j = 0; j < colors.length && searching; j++) {
                        if(current.substring(0, current.indexOf(",")).equals(colors[j])) {
                            cubeColors[j] += numCubes;
                            current = current.substring(current.indexOf(", ") + 2);
                            searching = false; 
                        }
                    }
                } else {
                    for(int j = 0; j < colors.length; j++) {
                        if(current.equals(colors[j])) {
                            cubeColors[j] += numCubes;
                        }
                    }
                    working = false;
                    if(numColors[0] < cubeColors[0]) {
                        numColors[0] = cubeColors[0];
                    }
                    if(numColors[1] < cubeColors[1]) {
                        numColors[1] = cubeColors[1];
                    }
                    if(numColors[2] < cubeColors[2]) {
                        numColors[2] = cubeColors[2];
                    }
                }
            }
            result += numColors[0]*numColors[1]*numColors[2];
        }
        return result;
    }

}
