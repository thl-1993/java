package edu.school21.classes;

import edu.school21.interfaces.PreProcessor;
import edu.school21.interfaces.Printer;
import edu.school21.interfaces.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public LocalDateTime localDateTime = LocalDateTime.now();

    String formatted = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


    @Override
    public void print(String s) {
        renderer.print(formatted + " " + s);
    }
}
