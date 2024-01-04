import java.util.*;
import java.io.*;

public class day8Part2 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hugos\\AdventOfCode2015\\AdventOfCode2015\\inputs\\day8Input.txt";
        List<String> niceList = handleInput(filePath);
        int memory = encodedChar(niceList);
        int literal = literalChar(niceList);
        System.out.println(memory - literal);

        List<String> test = new ArrayList<>();
        test.add("\"\"");
        System.out.println("\"\"");
        test.add("\"abc\"");
        System.out.println("\"abc\"");
        test.add("\"aaa\\\"aaa\"");
        System.out.println("\"aaa\\\"aaa\"");
        test.add("\"\\x27\"");
        System.out.println("\"\\x27\"");

        int testTotal = literalChar(test);
        int testMemory  = encodedChar(test);

        System.out.println(testMemory - testTotal);


    }

    public static List<String> handleInput(String filePath) {
        List<String> niceList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                niceList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return niceList;
    }

    public static int literalChar(List<String> niceList) {
        int literal = 0;

        for (int i = 0; i < niceList.size(); i++) {
            String current = niceList.get(i);
            literal += current.length();
        }
        return literal;
    }

    public static int encodedChar(List<String> niceList) {
        int memory = 0;
        for (String s : niceList) {
            int temp = s.length() + 2;
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isLetter(s.charAt(i))) {
                    temp += 1;
                }
                if(Character.isDigit(s.charAt(i))){
                    temp -=1;
                }
            }
            memory+=temp;
        }
        return memory;
    }
}
