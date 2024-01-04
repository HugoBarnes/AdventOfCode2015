import java.io.*;
import java.util.*;
import java.util.regex.*;

public class day9Part2 {
    public static void main(String [] args){
        String filePath = "C:/Users/hugos/AdventOfCode2015/AdventOfCode2015/inputs/day9Input.txt";
        List<List<String>> tour = handleInput(filePath);
        //System.out.println(tour);

        Map<String, Map<String, List<Integer>>> graph = createGraph(tour);

        //System.out.println(graph);

        System.out.println(longestPath(graph));
    }
    public static List<List<String>> handleInput(String filePath){
        List<List<String>> tour   = new ArrayList<>();

        try(BufferedReader reader =  new BufferedReader(new FileReader(filePath))) {
            String line; 
            while((line = reader.readLine()) != null){
                List<String> temp = new ArrayList<>();

                // get the first word
                // ^ starts from the beginning 
                // \\ escapes from a character 
                // w looks for a new word
                // + quantifies; in this case looking for 1 or more
                Pattern pattern = Pattern.compile("^\\w+");
                Matcher matcher = pattern.matcher(line);

                // Pattern class represents a compiled regular expression
                // Matcher class performs match operations on a character sequence

                if(matcher.find()){
                    temp.add(matcher.group());
                } else{
                    System.out.println("no such group found");
                }

                Pattern pattern2 = Pattern.compile("\\bto\\s+(\\w+)");
                Matcher matcher2 = pattern2.matcher(line);

                if(matcher2.find()){
                    temp.add(matcher2.group(1));
                } else{
                    System.out.println("no such pattern found");
                }

                Pattern pattern3 = Pattern.compile("\\d+");
                Matcher matcher3 = pattern3.matcher(line);

                if(matcher3.find()){
                    temp.add(matcher3.group());
                } else{
                    System.out.println("No such word found");
                }
                tour.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tour;
    }

    public static Map<String, Map<String, List<Integer>>> createGraph(List<List<String>> tour){
        Map<String, Map<String, List<Integer>>> graph = new HashMap<>();

        for(List<String> conncetion: tour){
            if(conncetion.size() != 3){
                throw new IllegalArgumentException("Each tour must have 3 inputs");
            }

            String connection1 = conncetion.get(0);
            String connection2 = conncetion.get(1);
            int distance = Integer.parseInt(conncetion.get(2));

            graph.putIfAbsent(connection1, new HashMap<>());
            graph.get(connection1).put(connection2, new ArrayList<>());
            graph.get(connection1).get(connection2).add(distance);

            graph.putIfAbsent(connection2, new HashMap<>());
            graph.get(connection2).put(connection1, new ArrayList<>());
            graph.get(connection2).get(connection1).add(distance);

        }
        return graph;
    }

    public static int longestPath(Map<String, Map<String, List<Integer>>> graph){
        String firstCity = "Faerun";
        int totalDistance = 0;
        Set<String> visitedCities = new HashSet<>();

        // go through all cities except the last one, don't have to loop back

        while(visitedCities.size() < graph.size()-1){
            Map<String, List<Integer>> distanceLists = graph.get(firstCity);
            String furthestCity = null;
            int longestPath = -1;

            for(Map.Entry<String, List<Integer>> entry : distanceLists.entrySet()){
                if(!visitedCities.contains(entry.getKey())){
                    int currentLongestDistance = Collections.max(entry.getValue());
                    if(currentLongestDistance > longestPath){
                        longestPath = currentLongestDistance;
                        furthestCity = entry.getKey();
                    }
                }
            }
             visitedCities.add(firstCity);
            totalDistance += longestPath;
            firstCity = furthestCity;
        }
        return totalDistance;
    }
}
