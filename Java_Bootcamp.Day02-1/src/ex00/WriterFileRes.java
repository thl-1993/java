package ex00;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterFileRes implements Closeable {
    private final Writer writer;

    public WriterFileRes(String fileName) throws IOException {
        // Используем BufferedWriter для повышения производительности
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    // Записываем строку в файл, если она не равна UNDEFINED
    public void writeToFile(String s) throws IOException {
        if (!s.equals(Reader.UNDEFINED)) {
            writer.write(s + System.lineSeparator()); // Используем системный разделитель строк
        } else {
            System.out.println(Reader.UNDEFINED); // Логируем ошибку в консоль
        }
    }

    // Метод close для автоматического закрытия ресурса (Writer)
    @Override
    public void close() throws IOException {
        writer.close(); // Закрываем Writer после использования
    }
}
