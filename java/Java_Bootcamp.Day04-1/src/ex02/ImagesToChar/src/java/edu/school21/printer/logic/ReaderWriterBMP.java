package edu.school21.printer.logic;


import com.diogonunes.jcolor.Attribute;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.colorize;

public class ReaderWriterBMP {
    public static int[][] getArray(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException("File not exist: " + file.getAbsoluteFile());
        }

        BufferedImage image = ImageIO.read(file);
        int[][] imageArray = new int[image.getWidth()][image.getHeight()];

        for (int y = 0; y < image.getWidth(); y++) {
            for (int x = 0; x < image.getHeight(); x++) {
                imageArray[y][x] = (image.getRGB(x, y) == Color.BLACK.getRGB()) ? 1 : 0;
            }
        }
        return imageArray;
    }

    public static void printBMP(int[][] imageArray, Attribute black, Attribute white) {
        for (int[] imageLine : imageArray) {
            for (int pixel : imageLine) {
                System.out.print(colorize(" ", (pixel == 1) ? black : white));
            }
            System.out.println();
        }
    }
}
