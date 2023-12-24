import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class day3Part2 {

    // write the text file into a string
    // go through every character of the string
    // start santa and robo santa at (0,0) and the counter at 1
    // if the index is even santa's incrementers take the value
    // if the index is odd robo santa's incrementers take the value
    // add the new values to a List<List<Integer>> increment counter
    // return counter
    public static void main(String[] args) {
        String input = handleString();
        System.out.println(houses(input));
    }
    public static String handleString(){
        String inputString="";
        String fileString = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day3Input.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(fileString))){
            String line;
            while((line=reader.readLine())!=null){
                inputString+=line;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return inputString;
    }
    public static int houses(String input){
        int counter =1;
        int xSanta = 0;
        int ySanta = 0;
        int xRobo = 0;
        int yRobo = 0;
        List<List<Integer>> visited = new ArrayList<>();
        List<Integer> initial = new ArrayList<>();
        initial.add(0);
        initial.add(0);
        visited.add(initial);
        for(int i=0; i<input.length(); i++){
            char value = input.charAt(i);
            List<Integer> house = new ArrayList<>();
            if(i%2==0){
                if(value=='^'){ySanta++;}
                if(value=='v'){ySanta--;}
                if(value=='<'){xSanta--;}
                if(value=='>'){xSanta++;}
                house.add(xSanta);
                house.add(ySanta);
            }
            if(i%2==1){
                if(value=='^'){yRobo++;}
                if(value=='v'){yRobo--;}
                if(value=='<'){xRobo--;}
                if(value=='>'){xRobo++;}
                house.add(xRobo);
                house.add(yRobo);
            }
            if(!visited.contains(house)){
                visited.add(house);
                counter++;
            }
        }
        return counter;
    }
}
