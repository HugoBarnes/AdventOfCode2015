import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day2Part2 {
    // Get all of the data into a List<List<Integer>>
    // Each <List<Integer>> represents a present
    // Calculate the number of ribbon for a bow
    // Calculate the number of ribbon to wrap the present
    // Sum it all together
    public static void main(String[] args) {
        int counter = 0;
        List<List<Integer>> presentDimensions = handleInput();
        for(int i=0; i<presentDimensions.size(); i++){
            int l = presentDimensions.get(i).get(0);
            int w = presentDimensions.get(i).get(1);
            int h = presentDimensions.get(i).get(2);
            counter+= ribbon(l,w,h);
            counter+=bow(l,w,h);
        }
        System.out.println(counter);
    }
    public static List<List<Integer>> handleInput(){
        List<List<Integer>> presents = new ArrayList<>(); // create a list of lists of integers

        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day2Input.txt"; // establish the filepath

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){ // try to establish buffered reader
            String line; // go through file line by line
            while((line = reader.readLine()) != null){ // while there is still information to gather
                String[] dimensions = line.split("x"); // create an array of string numbers
                List<Integer> dimensionValues = new ArrayList<>(); // take that array make all elements ints and then put them into an array list
                for(String dimension: dimensions){
                    dimensionValues.add(Integer.parseInt(dimension));
                }

                presents.add(dimensionValues); // before going to the next line add this list to the list of list of integers
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return presents;
    }
    public static double ribbon(int l, int w, int h){
        double perimeter1 = 2*(l+w); 
        double perimeter2 = 2*(l+h);
        double perimeter3 = 2*(w+h);
        double smallestPerimeter = Math.min(Math.min(perimeter1,perimeter2),perimeter3); // calculate smallest perimeter
        return smallestPerimeter;
    }
    public static double bow(int l, int w, int h){
        return (l*w*h); // calculate cubic volume
    }
}
