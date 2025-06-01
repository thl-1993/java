package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import edu.school21.printer.logic.ArgsJC;
import edu.school21.printer.logic.ColorMapper;
import edu.school21.printer.logic.ReaderWriterBMP;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ArgsJC argsJC = new ArgsJC();
        JCommander jc = new JCommander(argsJC);

        try {
            jc.parse(args);
            File file = new File(argsJC.filename);
            ReaderWriterBMP.printBMP(ReaderWriterBMP.getArray(file), ColorMapper.getAttribute(argsJC.black), ColorMapper.getAttribute(argsJC.white));
        } catch (ParameterException e) {
            System.err.println("Error: ");
            e.usage();
            System.exit(-1);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(-1);
        }
    }
}


// src\ex02\ImagesToChar\src\resources\image.bmp --black=BLACK --white=WHITE