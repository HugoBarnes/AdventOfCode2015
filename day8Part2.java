import java.util.*;
import java.io.*;


public class day8Part2 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hugos\\AdventOfCode2015\\AdventOfCode2015\\inputs\\day8Input.txt";
        List<String> niceList = handleInput(filePath);
        int memory = storedChar(niceList);
        int literal = literalChar(niceList);

        System.out.println(literal-memory);
    }

    public static List<String> handleInput(String filePath){
        List<String> niceList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line; 
            while((line = reader.readLine()) != null){
                niceList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            }
            return niceList;
    }
    // haven't looked at problem specs yet. 
    public static int literalChar(List<String> niceList){
        int literal = 0; 

        for(int i=0; i<niceList.size(); i++){
            String current = niceList.get(i);
            literal+=current.length()+2;
        }
        return literal;
    }
}
