import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class day3Part3 {
   // Read the day 3 input into a string
   // loop through every character in the string
   // for each character in the string create a set of visited houses
   // if a character takes santa to a new house, increase a counter
   // print the counter 
    public static void main(String[] args) {
        int counter = 0;
        String input =handleString();
        System.out.println(newHouses(input));

    }
    public static String handleString(){
        String inputString ="";
        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day3Input.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                inputString+=line;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return inputString;
    }
    public static int newHouses(String input){
        int counter = 1; // start santa at the first hosue
        int x=0; 
        int y=0;
        List<List<Integer>> visited = new ArrayList<>();
        List<Integer> initial = new ArrayList<>(); // first position
        initial.add(0);
        initial.add(0);
        visited.add(initial);
        for(int i=0; i<input.length(); i++){
            char symbol = input.charAt(i); // increase the visited set according to where santa is
            if(symbol == '^'){y++;}
            if(symbol == 'v'){y--;}
            if(symbol == '>'){x++;}
            if(symbol == '<'){x--;}

            List<Integer> house = new ArrayList<>(); // make the new house
            house.add(x);
            house.add(y);

            if(!visited.contains(house)){ // see if the house is new or not
                counter++;
                visited.add(house);
            }
        }
        return counter;
    }

}
