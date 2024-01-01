import java.io.*;
import java.util.*;
import java.util.function.*;

public class day7Part2 {
    private static Map<String, Integer> wirevalues = new HashMap<>();
    private static Map<String, String> instructionsMap = new HashMap<>();

    public static void main(String[] args) {
        String fileString = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day7Input.txt";

        List<String> originalInstructions = handleInput(fileString);

        // First calculation for wire 'a'
        int signalForA = followInstructions(new ArrayList<>(originalInstructions), false);
        System.out.println("Initial signal for wire 'a': " + signalForA);

        // Override wire 'b' with this signal and reset other wires and instructions
        wirevalues.clear();
        instructionsMap.clear();

        // Recalculate signal for wire 'a' with overridden value for 'b'
        int newSignalForA = followInstructions(new ArrayList<>(originalInstructions), true, signalForA);
        System.out.println("New signal for wire 'a': " + newSignalForA);
    }

    public static List<String> handleInput(String fileString) {
        List<String> instructions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileString))) {
            String line;
            while ((line = reader.readLine()) != null) {
                instructions.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instructions;
    }

    public static int followInstructions(List<String> instructions, boolean overrideB, int... newBValue) {
        for (String instruction : instructions) {
            String[] parts = instruction.split("->");
            String key = parts[1].trim();
            String value = parts[0].trim();

            if (overrideB && key.equals("b")) {
                value = Integer.toString(newBValue[0]);
            }

            instructionsMap.put(key, value);
        }
        return getSignal("a");
    }

    public static int getSignal(String wire) {
        if (wirevalues.containsKey(wire)) {
            return wirevalues.get(wire);
        }

        String instruction = instructionsMap.get(wire);
        int value;

        if (instruction == null) {
            try {
                value = Integer.parseInt(wire);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid wire or value: " + wire);
            }
        } else if (instruction.matches("\\d+")) {
            value = Integer.parseInt(instruction);
        } else if (instruction.contains("AND")) {
            value = applyBinaryOp(instruction, (a, b) -> a & b);
        } else if (instruction.contains("OR")) {
            value = applyBinaryOp(instruction, (a, b) -> a | b);
        } else if (instruction.contains("LSHIFT")) {
            value = applyBinaryOp(instruction, (a, b) -> a << b);
        } else if (instruction.contains("RSHIFT")) {
            value = applyBinaryOp(instruction, (a, b) -> a >> b);
        } else if (instruction.contains("NOT")) {
            value = applyUnaryOp(instruction, a -> ~a);
        } else {
            value = getSignal(instruction);
        }

        wirevalues.put(wire, value);
        return value;
    }

    private static int applyBinaryOp(String instruction, IntBinaryOperator op) {
        String[] parts = instruction.split(" ");
        int a = getSignal(parts[0]);
        int b = getSignal(parts[2]);
        return op.applyAsInt(a, b);
    }

    private static int applyUnaryOp(String instruction, IntUnaryOperator op) {
        String[] parts = instruction.split(" ");
        int a = getSignal(parts[1]);
        return op.applyAsInt(a);
    }
}
