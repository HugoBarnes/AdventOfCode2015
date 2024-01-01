import java.io.*;
import java.util.*;
import java.util.function.*;

class day7Part1 {
    public static void main(String[] args) {
        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day7Input.txt";
        List<String> instructions = handleInput(filePath);
        System.out.println(followInstructions(instructions));
    }
    public static List<String> handleInput(String filePath) {
        List<String> instructions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                instructions.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instructions;
    }

    // Stores calculated values of wires to avoid redundant calculations
    private static Map<String, Integer> wireValues = new HashMap<>();

    // Maps each wire to its corresponding instruction
    private static Map<String, String> instructionMap = new HashMap<>();

    // Method to process all instructions and find the value for wire 'a'
    public static int followInstructions(List<String> instructions) {
        // Parse and store instructions
        for (String instruction : instructions) {
            String[] parts = instruction.split(" -> ");
            instructionMap.put(parts[1], parts[0]);
        }
        // Compute and return the value of wire 'a'
        return getSignal("a");
    }

    // Recursive method to compute the value of a given wire
    private static int getSignal(String wire) {
        // Check if the value of this wire is already computed
        if (wireValues.containsKey(wire)) {
            return wireValues.get(wire);
        }

        // Get the instruction for this wire
        String instruction = instructionMap.get(wire);

        // Variable to hold the computed value
        int value;

        // Evaluate the instruction or the direct value
        if (instruction == null) {
            // Direct value
            value = Integer.parseInt(wire);
        } else if (instruction.matches("\\d+")) {
            // Direct number in instruction
            value = Integer.parseInt(instruction);
        } else if (instruction.contains("AND")) {
            // Bitwise AND operation
            value = applyBinaryOp(instruction, (a, b) -> a & b);
        } else if (instruction.contains("OR")) {
            // Bitwise OR operation
            value = applyBinaryOp(instruction, (a, b) -> a | b);
        } else if (instruction.contains("LSHIFT")) {
            // Left shift operation
            value = applyBinaryOp(instruction, (a, b) -> a << b);
        } else if (instruction.contains("RSHIFT")) {
            // Right shift operation
            value = applyBinaryOp(instruction, (a, b) -> a >> b);
        } else if (instruction.contains("NOT")) {
            // Bitwise NOT operation
            value = applyUnaryOp(instruction, a -> ~a);
        } else {
            // Recursively compute the value for another wire
            value = getSignal(instruction);
        }

        // Cache and return the computed value
        wireValues.put(wire, value);
        return value;
    }

    // Helper method to apply a binary operation (AND, OR, LSHIFT, RSHIFT)
    private static int applyBinaryOp(String instruction, IntBinaryOperator op) {
        String[] parts = instruction.split(" ");
        int a = getSignal(parts[0]);
        int b = getSignal(parts[2]);
        return op.applyAsInt(a, b);
    }

    // Helper method to apply a unary operation (NOT)
    private static int applyUnaryOp(String instruction, IntUnaryOperator op) {
        String[] parts = instruction.split(" ");
        int a = getSignal(parts[1]);
        return op.applyAsInt(a);
    }
}
