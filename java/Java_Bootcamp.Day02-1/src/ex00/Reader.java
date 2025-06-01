
package ex00;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reader {
    public static final String UNDEFINED = "UNDEFINED";
    private static final int MAX_SIGNATURE_LENGTH = 10;
    private final Map<ArrayList<Integer>, String> stringMap;

    public Reader(String fileName) throws FileNotFoundException {
        // Получаем сигнатуры из файла при создании объекта
        this.stringMap = getSignatureMap(fileName);
    }

    // Метод для чтения файла сигнатур и создания карты
    private Map<ArrayList<Integer>, String> getSignatureMap(String fileName) throws FileNotFoundException {
        Map<ArrayList<Integer>, String> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                // Разбиваем строку на части, используя запятую в качестве разделителя
                String[] parts = line.split(",");
                if (parts.length < 2) continue; // Пропускаем строки, если формат неверный

                String value = parts[0].trim(); // Первый элемент — это название формата
                String[] hexValues = parts[1].trim().split("\\s+"); // Разделяем оставшиеся шестнадцатеричные значения по пробелам

                ArrayList<Integer> keyList = new ArrayList<>();
                for (String hex : hexValues) {
                    keyList.add(Integer.parseInt(hex, 16)); // Преобразуем каждый байт в шестнадцатеричном формате
                }
                map.put(keyList, value); // Добавляем в карту формат и сигнатуру
            }
        }
        return map;
    }

    // Метод для чтения файла и поиска сигнатуры
    public String readSignatureFile(String fileName) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            int input;
            int length = 0;
            ArrayList<Integer> list = new ArrayList<>();
            // Читаем первые MAX_SIGNATURE_LENGTH байтов из файла
            while ((input = fileInputStream.read()) != -1 && length < MAX_SIGNATURE_LENGTH) {
                list.add(input);
                length++;
                if (stringMap.get(list) != null) {
                    return stringMap.get(list); // Возвращаем найденную сигнатуру
                }
            }
        }
        return UNDEFINED; // Если сигнатура не найдена
    }
}

