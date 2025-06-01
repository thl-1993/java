package ex01;
import java.util.*;

public class VectorUtil {

    public static Map<String, Integer> createFrequencyMap(List<String> text) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : text) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        return frequencyMap;
    }

    public static List<Integer> createFrequencyVector(Map<String, Integer> frequencyMap, Set<String> dictionary) {
        List<Integer> vector = new ArrayList<>();
        for (String word : dictionary) {
            vector.add(frequencyMap.getOrDefault(word, 0));
        }
        return vector;
    }
}