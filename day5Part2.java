import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class day5Part2 {
    public static void main(String[] args) {
        String fileString = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day5Input.txt";
        List<String> niceList = handleInput(fileString);
        Long niceListSize = niceList.stream()
                                    .filter(day5Part2::twoSets)
                                    .filter(day5Part2:: repeat)
                                    .count();
        System.out.println(niceListSize);
    }

    private static List<String> handleInput(String fileString){
        List<String> niceList = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileString))){
            String line;
            while((line = reader.readLine()) != null){
                niceList.add(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return niceList;
    }

    public static boolean twoSets(String str){
        for(int i=1; i<str.length(); i++){
            String duplicate = str.substring(i-1, i+1);
            if(str.substring(i+1,str.length()).contains(duplicate)){
                return true;
            }
        }
        return false;
    }

    public static boolean repeat(String str){
        for(int i=2; i<str.length(); i++){
            char a = str.charAt(i-2);
            char b = str.charAt(i);
            if(a==b){ // characters are primitive
                return true; 
            }
        }
        return false;
    }
}