package ex01;

import java.io.*;
import java.util.*;

public class FileReaderUtil {
    public static List<String> readWordsFromFile(String fileName) throws IOException {
        List<String> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = reader.readLine()) != null){
            // Убираем небуквенные символы и разбиваем строку на слова
            String[] tokens = line.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");
            Collections.addAll(words, tokens);
        }
        reader.close();
        return words;

}
}
