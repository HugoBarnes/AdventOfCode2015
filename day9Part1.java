import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day9Part1 {
    public static void main(String[] args) {

        // build a weighted graph
            // add all the cities to the weighted graph 
            // find the shortest path Dijkstras
        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day9Input.txt";
        List<List<String>> list = handleInput(filePath);
        //System.out.println(list);

        // Build and assign my graph:
        Map<String, Map<String, List<Integer>>> graph = buildGraph(list);
        System.out.println(graph);

        System.out.println(nearestNeighborTour(graph));
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

    public static Map<String, Map<String, List<Integer>>> buildGraph(List<List<String>> list){
        // make our graph:
        Map<String, Map<String, List<Integer>>> graph = new HashMap<>();
        
        // for every connection in the list: connection is between two cities and has a distance
        for(List<String> connection: list){
            if(connection.size()!=3){
                throw new IllegalArgumentException("Each connection must have exactly 3 elements");
            }

            // define all of the elements in a connection 
            String city1 = connection.get(0);
            String city2 = connection.get(1);
            int distance = Integer.parseInt(connection.get(2));

            // add the data to the graphs: 
            graph.putIfAbsent(city1, new HashMap<>());
            graph.get(city1).put(city2, new ArrayList<>());
            graph.get(city1).get(city2).add(distance);

            // add the reverse connection:
            graph.putIfAbsent(city2, new HashMap<>());
            graph.get(city2).put(city1, new ArrayList<>());
            graph.get(city2).get(city1).add(distance);
        }

        return graph;
    }

    // nearest neighbor algorithm:
    public static int nearestNeighborTour(Map<String, Map<String, List<Integer>>> graph) {
        String currentCity = "Faerun";
        Set<String> visitedCities = new HashSet<>();
        int totalDistance = 0;
    
        while (visitedCities.size() < graph.size() - 1) {
            Map<String, List<Integer>> distanceLists = graph.get(currentCity);
            String nearestCity = null;
            int shortestDistance = Integer.MAX_VALUE;
    
            for (Map.Entry<String, List<Integer>> entry : distanceLists.entrySet()) {
                if (!visitedCities.contains(entry.getKey())) {
                    int currentShortestDistance = Collections.min(entry.getValue());
                    if (currentShortestDistance < shortestDistance) {
                        shortestDistance = currentShortestDistance;
                        nearestCity = entry.getKey();
                    }
                }
            }
    
            if (nearestCity == null) break; // No more unvisited cities
    
            visitedCities.add(currentCity);
            totalDistance += shortestDistance;
            currentCity = nearestCity;
        }
    
        return totalDistance;
    }
    
    
}
