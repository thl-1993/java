package ex01;
import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException {
        args = new String[]{"C:\\Users\\tohal\\IdeaProjects\\Java_Bootcamp.Day02-1\\src\\ex01\\file1.txt", "C:\\Users\\tohal\\IdeaProjects\\Java_Bootcamp.Day02-1\\src\\ex01\\file1.txt"};
        if (args.length != 2) {
            System.err.println("Usage: java Program <file1> <file2>");
            System.exit(-1);
        }

        // Чтение файлов
        List<String> textA = FileReaderUtil.readWordsFromFile(args[0]);
        List<String> textB = FileReaderUtil.readWordsFromFile(args[1]);

        // Создание словаря
        Dictionary dictionary = new Dictionary();
        dictionary.addWords(textA);
        dictionary.addWords(textB);
        dictionary.writeToFile("dictionary.txt");

        // Подсчет частоты слов
        Map<String, Integer> freqA = VectorUtil.createFrequencyMap(textA);
        Map<String, Integer> freqB = VectorUtil.createFrequencyMap(textB);

        // Создание векторов частот
        List<Integer> vectorA = VectorUtil.createFrequencyVector(freqA, dictionary.getWords());
        List<Integer> vectorB = VectorUtil.createFrequencyVector(freqB, dictionary.getWords());

        // Вычисление сходства
        double similarity = CosineSimilarityCalculator.calculateSimilarity(vectorA, vectorB);
        System.out.printf("Similarity = %.2f\n", similarity);
    }
}