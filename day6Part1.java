import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class day6Part1 {

    public static int[][] lightArray = new int[1000][1000];

    public static void main(String[] args) {

        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day6Input.txt";
        List<String> lights = handleInput(filePath);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                lightArray[i][j] = 0;
            }
        }
        System.out.println(followInstruction(lights));
    }

    public static List<String> handleInput(String filePath) {
        List<String> lights = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lights.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lights;
    }

    public static int followInstruction(List<String> input) {
        // access the array of data specified in the instructions
        List<List<Integer>> rectangle = rectangle(input);
        // apply the rule to the array of data (toggle, on or off)

        procedure(input, rectangle);

        // if toggle on: call toggle on

        // if on: call on

        // if off call off:
        // loop through the array and count how many lights are toggled on or off
        int numLights = countLights();
        return numLights;
    }

    // access the array of data specified in the instructions:
    // breaks the instructions up into an array of the numbers

    public static List<List<Integer>> rectangle(List<String> input) {
        List<List<Integer>> rectangle = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");

        for(String current : input) {
            Matcher matcher = pattern.matcher(current);
            List<Integer> temp = new ArrayList<>();
            
            while(matcher.find()){
                temp.add(Integer.parseInt(matcher.group()));
            }

            if(!temp.isEmpty()){
                rectangle.add(temp);
            }
        }
        return rectangle;
    }

    public static void procedure(List<String> calls, List<List<Integer>> rectangle) {
        for (int i = 0; i < calls.size(); i++) {
            String current = calls.get(i);
            if (current.contains("toggle")) {
                toggle(i, rectangle);
            } else if (current.contains("on")) {
                on(i, rectangle);
            } else if (current.contains("off")) {
                off(i, rectangle);
            }
        }
    }

    public static void toggle(int i, List<List<Integer>> rectangle) {
        int topCornerx = rectangle.get(i).get(0);
        int topCornery = rectangle.get(i).get(1);
        int bottomCornerx = rectangle.get(i).get(2);
        int bottomCornery = rectangle.get(i).get(3);

        //System.out.println("index " + i + " topCornerx " + topCornerx + " topCornery " + topCornery + " bottomCornerx "
               // + bottomCornerx + " bottomCornery " + bottomCornery);

        for (int j = topCornerx; j <= bottomCornerx; j++) {
            for (int k = topCornery; k <= bottomCornery; k++) {
                if (lightArray[j][k] == 1) {
                    lightArray[j][k] = 0;
                } else if (lightArray[j][k] == 0) {
                    lightArray[j][k] = 1;
                }
            }
        }
    }

    public static void on(int i, List<List<Integer>> rectangle) {
       int topCornerx = rectangle.get(i).get(0);
        int topCornery = rectangle.get(i).get(1);
        int bottomCornerx = rectangle.get(i).get(2);
        int bottomCornery = rectangle.get(i).get(3);

        for (int j = topCornerx; j <= bottomCornerx; j++) {
            for (int k = topCornery; k <= bottomCornery; k++) {
                if (lightArray[j][k] == 0) {
                    lightArray[j][k] = 1;
                }
            }
        }
    }

    public static void off(int i, List<List<Integer>> rectangle) {
       int topCornerx = rectangle.get(i).get(0);
        int topCornery = rectangle.get(i).get(1);
        int bottomCornerx = rectangle.get(i).get(2);
        int bottomCornery = rectangle.get(i).get(3);

        for (int j = topCornerx; j <= bottomCornerx; j++) {
            for (int k = topCornery; k <= bottomCornery; k++) {
                if (lightArray[j][k] == 1) {
                    lightArray[j][k] = 0;
                }
            }
        }
    }

    public static int countLights() {
        int counter = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (lightArray[i][j] == 1) {
                    counter++;
                }
            }
        }
        return counter;
    }

}
