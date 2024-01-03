import java.util.*;
import java.io.*;

public class day8Part1 {

    public static void main(String[] args) {
        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day8Input.txt";
        List<String> santaList = handleInput(filePath);
        int totalCodeChars = calculateCodeCharacters(santaList);
        int totalMemoryChars = calculateMemoryCharacters(santaList);
        System.out.println("Total characters of string code minus characters in memory: " + (totalCodeChars - totalMemoryChars));
    }

    private static List<String> handleInput(String filePath) {
        List<String> santaList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                santaList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return santaList;
    }

    private static int calculateCodeCharacters(List<String> santaList) {
        int total = 0;
        for (String s : santaList) {
            // Add 2 for the surrounding double quotes in the code
            total += s.length() + 2;
        }
        return total;
    }

    private static int calculateMemoryCharacters(List<String> santaList) {
        int total = 0;
        for (String s : santaList) {
            int memoryChars = s.length();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '\\' && i + 1 < s.length()) {
                    char nextChar = s.charAt(i + 1);
                    if (nextChar == '\\' || nextChar == '"') {
                        memoryChars--;
                        i++; // Skip the next character
                    } else if (nextChar == 'x' && i + 3 < s.length()) {
                        memoryChars -= 3;
                        i += 3; // Skip the next three characters
                    }
                }
            }
            total += memoryChars;
        }
        return total;
    }
}
