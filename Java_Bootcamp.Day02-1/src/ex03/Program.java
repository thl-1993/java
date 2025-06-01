package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        // Проверка аргументов командной строки
        if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
            System.out.println("Usage: java Program --current-folder=<path>");
            return;
        }

        // Извлечение пути к папке из аргументов командной строки
        String startDir = args[0].substring("--current-folder=".length());

        // Создание объекта файлового менеджера
        FileManager fileManager = new FileManager(startDir);

        // Создание сканера для чтения команд пользователя
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("-> ");
            // Проверяем, есть ли ввод
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                String[] parts = input.split(" ");

                // Обработка команд
                switch (parts[0]) {
                    case "ls":
                        fileManager.listFiles();
                        break;

                    case "cd":
                        if (parts.length > 1) {
                            fileManager.changeDirectory(parts[1]);
                        } else {
                            System.out.println("Usage: cd <directory>");
                        }
                        break;

                    case "mv":
                        if (parts.length > 2) {
                            fileManager.moveFile(parts[1], parts[2]);
                        } else {
                            System.out.println("Usage: mv <source> <target>");
                        }
                        break;

                    case "exit":
                        running = false;
                        break;

                    default:
                        System.out.println("Unknown command: " + parts[0]);
                }
            }
        }

        // Закрытие сканера перед завершением программы
        scanner.close();
    }
}
