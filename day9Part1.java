import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day9Part1 {
    public static void main(String[] args) {
        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day9Input.txt";
        List<List<String>> list = handleInput(filePath);

        Map<String, Map<String, Integer>> graph = buildGraph(list);
        System.out.println(findLongestPath(graph));
    }

    public static List<List<String>> handleInput(String filePath) {
        List<List<String>> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> temp = new ArrayList<>();
                // gets the forst wprd
                Pattern pattern = Pattern.compile("^\\w+");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    temp.add(matcher.group());
                } else {
                    temp.add("No match");
                }
                // gets the word after to
                Pattern pattern2 = Pattern.compile("\\bto\\s+(\\w+)");
                Matcher matcher2 = pattern2.matcher(line);
                if (matcher2.find()) {
                    temp.add(matcher2.group(1));
                } else {
                    temp.add("No match");
                }
                //
                Pattern pattern3 = Pattern.compile("\\d+");
                Matcher matcher3 = pattern3.matcher(line);
                if (matcher3.find()) {
                    temp.add(matcher3.group());
                } else {
                    temp.add("No match");
                }

                list.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, Map<String, Integer>> buildGraph(List<List<String>> list) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        for (List<String> connection : list) {
            if (connection.size() != 3) {
                throw new IllegalArgumentException("Each connection must have exactly 3 elements");
            }
            String city1 = connection.get(0);
            String city2 = connection.get(1);
            int distance = Integer.parseInt(connection.get(2));
            graph.putIfAbsent(city1, new HashMap<>());
            graph.get(city1).put(city2, distance);
            graph.putIfAbsent(city2, new HashMap<>());
            graph.get(city2).put(city1, distance);
        }
        return graph;
    }

    public static int findLongestPath(Map<String, Map<String, Integer>> graph) {
        List<String> cities = new ArrayList<>(graph.keySet());
        return permute(cities, 0, new ArrayList<>(), graph, 0);
    }

    private static int permute(List<String> cities, int k, List<String> longestPath,
            Map<String, Map<String, Integer>> graph, int longestDistance) {
        int currentLongest = longestDistance;
        for (int i = k; i < cities.size(); i++) {
            Collections.swap(cities, i, k);
            currentLongest = Math.max(currentLongest, permute(cities, k + 1, longestPath, graph, currentLongest));
            Collections.swap(cities, k, i);
        }
        if (k == cities.size() - 1) {
            int currentDistance = calculateTotalDistance(cities, graph);
            if (currentDistance > currentLongest) {
                longestPath.clear();
                longestPath.addAll(cities);
                return currentDistance;
            }
        }
        return currentLongest;
    }

    private static int calculateTotalDistance(List<String> path, Map<String, Map<String, Integer>> graph) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalDistance += graph.get(path.get(i)).get(path.get(i + 1));
        }
        return totalDistance;
    }
}
