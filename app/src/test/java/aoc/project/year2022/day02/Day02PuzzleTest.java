package aoc.project.year2022.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import aoc.project.util.AocUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day02PuzzleTest {
    private Day02Puzzle day02Puzzle;

    @BeforeEach
    public void setUp() {
        day02Puzzle = new Day02Puzzle();
    }

    @Test
    public void doPart1_givenExample1_assertNotNull() {
        List<String> lines = AocUtil.readFile("app/src/test/resources/puzzleInputs/year2022/day02Example1.txt");
        assertNotNull(lines);
        assertEquals(3, lines.size());
        assertEquals(15, day02Puzzle.doPart1(lines));
    }

    @Test
    public void doPart2_givenChallenge_assertNotNull() {
        List<String> lines = AocUtil.readFile("app/src/test/resources/puzzleInputs/year2022/day02Example1.txt");
        assertNotNull(lines);
        assertEquals(3, lines.size());
        assertEquals(12, day02Puzzle.doPart2(lines));
    }

}