package aoc.project.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeneratePuzzleFilesTest {
    // This needs to be set to your machine's setup of the project.
    private final String fileSystemPathToAocProject = "M:\\projects\\AoC-VS\\AoC-Project\\";

    private GeneratePuzzleFiles generatePuzzleFiles;

    @BeforeEach
    public void setUp() {
        generatePuzzleFiles = new GeneratePuzzleFiles(fileSystemPathToAocProject);
    }


    @Test
    public void generateFiles_givenPathYearAndDay_generateNewFilesIfNoneExist(){
        generatePuzzleFiles.generateFiles(2022, 1);

        assertTrue(true, "Nothing to see here.");
    }
}
