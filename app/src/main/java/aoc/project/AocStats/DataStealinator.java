package aoc.project.AocStats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

import aoc.project.Constants;

public class DataStealinator {

    public static void main(String[] args) throws Exception {
        URL aocStats = new URL("https://adventofcode.com/2024/stats");

        BufferedReader in = new BufferedReader(new InputStreamReader(aocStats.openStream()));
        String inputLine;

        ArrayList<String> toParse = new ArrayList<>();
        while ((inputLine = in.readLine()) != null) {
            toParse.add(inputLine);
            System.out.println(inputLine);
        }
        in.close();

        for (String line : toParse) {
            if (line.contains("<a href=\"/2024/day/")) {
                int day = line.charAt(19) - 48;

                int fullCompletionsIndex = line.indexOf("<span class=\"stats-both\">");
                int fullCompletionsEndIndex = line.indexOf("</span>");
                int fullStars = Integer.parseInt(line.substring(fullCompletionsIndex + 27, fullCompletionsEndIndex));

                int halfCompletionsIndex = line.indexOf("<span class=\"stats-firstonly\">");
                int halfCompletionsEndIndex = line.indexOf("</span>", fullCompletionsEndIndex + 1);
                int halfStars = Integer.parseInt(line.substring(halfCompletionsIndex + 31, halfCompletionsEndIndex));

                // System.out.println(day + ": " + fullStars + "-" + halfStars);

                File ourFile = new File(
                        Constants.PATH_TO_PROJECT + "\\app\\src\\main\\java\\aoc\\project\\AocStats\\store\\" + day);
                if (!ourFile.exists()) {
                    ourFile.createNewFile();
                }

                FileWriter newWriter = new FileWriter(ourFile);
                newWriter.write(LocalDateTime.now().getDayOfMonth() + ": " + fullStars + "-" + halfStars + "\n");
                newWriter.close();

            }
        }

    }
}
