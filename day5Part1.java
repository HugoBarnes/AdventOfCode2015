import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class day5Part1 {
    public static void main(String[] args){
        String fileString = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day5Input.txt";
        List<String> niceList = handleInput(fileString);
        //System.out.println(niceList);
        long NiceListSize = niceList.stream()
                                    .filter(day5Part1:: threeVowels)
                                    .filter(day5Part1:: duplicates)
                                    .filter(day5Part1:: doesNotContain)
                                    .count();
        System.out.println(NiceListSize);
    }

    public static List<String> handleInput(String fileName){
        List<String> niceList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                niceList.add(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return niceList;
    }

    public static boolean threeVowels(String str){
        int vowels=0;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(c=='a' || c== 'e' || c=='i' || c=='o' || c=='u'){
                vowels++;
            }
            if(vowels>=3){
                return true;
            }
        }
        return false;
    }
    public static boolean duplicates(String str){
        for(int i=1; i<str.length(); i++){
            char c = str.charAt(i);
            char b = str.charAt(i-1);
            if(c == b){
                return true;
            }
        }
        return false;
    }

    public static boolean doesNotContain(String str){
        if(str.contains("ab") || str.contains("cd") || str.contains("pq") || str.contains("xy")){
            return false;
        }
        return true;
    }
}
