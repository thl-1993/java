package ex01;
import java.util.*;

public class CosineSimilarityCalculator {

    public static double calculateSimilarity(List<Integer> vectorA, List<Integer> vectorB) {
        int numerator = 0;
        double sumA = 0;
        double sumB = 0;

        for (int i = 0; i < vectorA.size(); i++) {
            numerator += vectorA.get(i) * vectorB.get(i);
            sumA += vectorA.get(i) * vectorA.get(i);
            sumB += vectorB.get(i) * vectorB.get(i);
        }

        return numerator / (Math.sqrt(sumA) * Math.sqrt(sumB));
    }
}