import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class day6Part2 {

    public static int[][] lightArray = new int[1000][1000];

    public static void main(String args[]) {
        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day6Input.txt";

        List<String> input = handleInput(filePath);

        System.out.println(calculateBrightness(input));

    }

    public static List<String> handleInput(String filePath) {
        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static int calculateBrightness(List<String> input) {

        // calculate the rectangle of lights
        List<List<Integer>> box = rectangle(input);

        // take in the rectangle of information, and the input and do either toggle, on,
        // or off:

        procedure(input, box);

        // count the brightness of the lights in the array
        int brightness = sumBrightness();
        return brightness;
    }

    public static List<List<Integer>> rectangle(List<String> input) {
        List<List<Integer>> numbers = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+");

        for (String instruction : input) {
            Matcher matcher = pattern.matcher(instruction);
            List<Integer> temp = new ArrayList<>();

            while (matcher.find()) {
                temp.add(Integer.parseInt(matcher.group()));
            }
            if (!temp.isEmpty()) {
                numbers.add(temp);
            }
        }
        return numbers;
    }

    public static void procedure(List<String> input, List<List<Integer>> box) {
        for (int i = 0; i < input.size(); i++) {
            String current = input.get(i);
            List<Integer> rect = box.get(i);

            if (current.contains("toggle")) {
                toggle(rect);
            }
            if (current.contains("on")) {
                on(rect);
            }
            if (current.contains("off")) {
                off(rect);
            }
        }
    }

    public static void toggle(List<Integer> rect) {
        int topCornerx = rect.get(0);
        int topCornery = rect.get(1);
        int bottomCornerx = rect.get(2);
        int bottomCornery = rect.get(3);

        for (int i = topCornerx; i <= bottomCornerx; i++) {
            for (int j = topCornery; j <= bottomCornery; j++) {
                lightArray[i][j] += 2;
            }
        }
    }

    public static void off(List<Integer> rect) {
        int topCornerx = rect.get(0);
        int topCornery = rect.get(1);
        int bottomCornerx = rect.get(2);
        int bottomCornery = rect.get(3);

        for (int i = topCornerx; i <= bottomCornerx; i++) {
            for (int j = topCornery; j <= bottomCornery; j++) {
                if (lightArray[i][j] > 0) {
                    lightArray[i][j] -= 1;
                }
            }
        }
    }

    public static void on(List<Integer> rect) {
        int topCornerx = rect.get(0);
        int topCornery = rect.get(1);
        int bottomCornerx = rect.get(2);
        int bottomCornery = rect.get(3);

        for (int i = topCornerx; i <= bottomCornerx; i++) {
            for (int j = topCornery; j <= bottomCornery; j++) {
                lightArray[i][j] += 1;
            }
        }
    }

    public static int sumBrightness(){
        int counter =0;
        for(int i=0; i<1000; i++){
            for(int j=0; j<1000; j++){
                counter = counter+ lightArray[i][j];
            }
        }
        return counter;
    }

}