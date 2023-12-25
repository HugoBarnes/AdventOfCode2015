import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class day5Part1 {
    public static void main(String[] args) {
        List<List<String>> inputString = handleInput(); // get the data into a List<List<String>>
        int numNice = numNice(inputString); // get the size of the nice list
        System.out.println(numNice); // return the size of the nice list
    }
    public static List<List<String>> handleInput() { // get the input data
        List<List<String>> list = new ArrayList<>();
        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day5Input.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> row = new ArrayList<>();
                row.add(line);
                list.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int numNice(List<List<String>> input) {
        return (int) input.stream()
                          .flatMap(List::stream) // Flatten to stream of strings
                          .filter(day5Part1::hasEnoughVowels) // filter if it doesnt have enough vowels
                          .filter(day5Part1::hasDuplicate) // filter if it doesnt have duplicates
                          .filter(day5Part1::doesNotContainDisallowedStrings) // does not contain smaller strings
                          .count(); // return the size
    }
    private static boolean hasEnoughVowels(String str) {
        long vowelCount = str.chars()
                             .mapToObj(c -> (char) c) // make each string a character
                             .filter(c -> "aeiou".indexOf(c) != -1) // if it doesn't contain vowel remove
                             .count(); // count the number of vowels
        return vowelCount >= 3; // return number of strings with 3 or more vowels
    }
    private static boolean hasDuplicate(String str) {
        for (int i = 0; i < str.length() - 1; i++) { // go through string
            if (str.charAt(i) == str.charAt(i + 1)) { // see if the character equals character next to it
                return true; // return true if it does
            }
        }
        return false;
    }
    private static boolean doesNotContainDisallowedStrings(String str) {
        return !str.contains("ab") && !str.contains("cd") && !str.contains("pq") && !str.contains("xy"); // if contains any of these remove
    }
}
