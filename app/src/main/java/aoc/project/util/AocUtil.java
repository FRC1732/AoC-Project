package aoc.project.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import aoc.project.Constants;

public class AocUtil {

    public static List<String> readFile(String filePathAndName) {
        List<String> lines = null;
        try {
            lines = Files
            		.lines(Paths.get(Constants.PATH_TO_PROJECT, filePathAndName))
            		.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
    
}
