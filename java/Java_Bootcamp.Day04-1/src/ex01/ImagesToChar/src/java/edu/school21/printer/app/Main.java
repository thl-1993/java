package edu.school21.printer.app;
import edu.school21.printer.logic.Reader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Program <filePath>");
            System.exit(-1);
        }
        Path filePath = Paths.get(args[0]).toAbsolutePath();

        Reader reader = new Reader(filePath.toString());
        reader.changeColor();

    }
}
