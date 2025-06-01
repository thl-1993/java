package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class Reader {
    private final String filePath;

    public Reader(String filePath)  {

        this.filePath = filePath;
    }

    public void changeColor() {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixelColor = image.getRGB(x, y);
                    Color color = new Color(pixelColor);
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    if (red == 255 && green == 255 && blue == 255) {
                        System.out.print(".");
                    } else if (red == 0 && green == 0 && blue == 0) {
                        System.out.print("0");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
