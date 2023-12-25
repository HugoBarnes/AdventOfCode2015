import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class day5Part2 {
    public static void main(String[] args){
        String fileString = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day5Input.txt";
        List<String> niceList = handleInput(fileString);
        System.out.println(niceList);
        Long niceListSize = niceList.stream()
                                    .filter(day5Part2:: twoPair) // two pair twice in reality*
                                    .filter(day5Part2:: repeat) // repeat with a letter in between: x_x or aaa
                                    .count();
        System.out.println(niceListSize);
    }
    public static List<String> handleInput(String fileString){
        List<String> niceList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileString))){
            String line;

            while((line=reader.readLine())!=null){
                niceList.add(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return niceList;
    }
    public static boolean twoPair(String str){
        for(int i=1; i<str.length(); i++){
                String duplicate = str.substring(i-1, i+1);
                if(str.substring(i+1, str.length()).contains(duplicate)){
                    return true;
                }
        }
        return false;
    }
    public static boolean repeat(String str){
        for(int i=2; i<str.length(); i++){
            if(str.charAt(i-2)==str.charAt(i)){
                return true;
            }
        }
        return false;
    }

}
