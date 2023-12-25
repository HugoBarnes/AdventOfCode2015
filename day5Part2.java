import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class day5Part2 {
    public static void main(String[] args){
        List<String> input = handleInput();
        long NiceListSize = input.stream().filter(day5Part2::isNice).count();
        System.out.println(NiceListSize);
    }

    public static List<String> handleInput(){
        List<String> outputList = new ArrayList<>();

        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day5Input.txt";

        try(BufferedReader reader = new BufferedReader (new FileReader(filePath))){
            String line;
            while((line = reader.readLine())!= null){
                outputList.add(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return outputList;
    }

    public static boolean isNice(String str) {
        return hasRepeatingPairWithoutOverlap(str) && hasRepeatingLetterWithOneBetween(str);
    }

    private static boolean hasRepeatingPairWithoutOverlap(String str) {
        for (int i = 0; i < str.length() - 2; i++) {
            String pair = str.substring(i, i + 2);
            if (str.substring(i + 2).contains(pair)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRepeatingLetterWithOneBetween(String str) {
        for (int i = 0; i < str.length() - 2; i++) {
            if (str.charAt(i) == str.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }
    
}
