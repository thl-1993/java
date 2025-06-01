package ex01;

import java.io.*;
import java.util.*;

public class Dictionary {
    private final Set<String> words;

    public Dictionary() {
        this.words = new TreeSet<>();
    }

    public void addWords(List<String> text) {
        words.addAll(text);
    }

    public Set<String> getWords() {
        return words;
    }

    public void writeToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (String word : words) {
            writer.write(word);
            writer.newLine();
        }

        writer.close();
    }
}