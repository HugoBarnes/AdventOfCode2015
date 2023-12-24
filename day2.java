import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class day2 {
    // we need to get the dimensions 
    // for each dimension apply the formula
    // sum the output of the formula for every present
    public static void main(String[] args) {
        int counter=0;

        List<List<Integer>> dimensions = handleInput();
        for(int i=0; i<dimensions.size(); i++){
            int l=dimensions.get(i).get(0);
            int w=dimensions.get(i).get(1);
            int h=dimensions.get(i).get(2);
            counter+=wrappingSize(l, w, h);
        }
        System.out.println(counter);
    }

    // Read the file into an array of Strings
    public static List<List<Integer>> handleInput(){
        List<List<Integer>> dimensionsList = new ArrayList<>(); // create the new dimensions list of lists of integers

        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day2Input.txt"; // save the filepath to a string;

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){ // Try to open the file with the buffered reader
            String line; // buffered reader goes line by line
            while((line = reader.readLine()) != null){ // while the buffered reader isn't at the end of the file
                String[] dimensions = line.split("x"); // split the string into an array of dimensions the numbers are separated by x
                List<Integer> dimensionValues = new ArrayList<>(); // create a list of integers called dimension values

                for(String dimension: dimensions){
                    dimensionValues.add(Integer.parseInt(dimension)); // for every string dimension make a new a List of integers typecast into INTEGERS
                }

                dimensionsList.add(dimensionValues); // add the arrayList of dimensions to the entire arrayList now
            }
        } catch (IOException e){ // if the buffered reader fails
            e.printStackTrace();
        }
        return dimensionsList; // return the arrayList of Lists of integers
    }

    public static double wrappingSize(int length, int width, int height) {
        double wrappingPaper =0;
        double side1 = length*width;
        double side2 = length*height;
        double side3 = width*height;
        double surfaceArea = 2*(side1+side2+side3); // Area of a rectangular prism formula
        double smallestSide = Math.min(Math.min(side1,side2),side3); 
        wrappingPaper = surfaceArea+smallestSide; // problem specification
        return wrappingPaper;
    }
    
}
