package ex03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileManager {
    private Path currentDirectory;

    // Конструктор принимает начальную директорию
    public FileManager(String startDir) {
        this.currentDirectory = Paths.get(startDir);
    }

    // Метод для вывода содержимого текущей директории
    public void listFiles() {
        File dir = currentDirectory.toFile();
        File[] filesList = dir.listFiles();

        if (filesList == null) {
            System.out.println("Empty directory or directory does not exist.");
            return;
        }

        for (File file : filesList) {
            if (file.isDirectory()) {
                System.out.println(file.getName() + " <DIR>");
            } else {
                System.out.println(file.getName() + " " + (file.length() / 1024) + " KB");
            }
        }
    }

    // Метод для изменения текущей директории
    public void changeDirectory(String folderName) {
        Path newPath = currentDirectory.resolve(folderName).normalize();

        if (Files.isDirectory(newPath)) {
            currentDirectory = newPath;
            System.out.println("Current directory: " + currentDirectory);
        } else {
            System.out.println("Directory not found: " + folderName);
        }
    }

    // Метод для перемещения или переименования файла
    public void moveFile(String sourceFile, String targetPath) {
        Path source = currentDirectory.resolve(sourceFile);
        Path target = currentDirectory.resolve(targetPath).normalize();

        try {
            if (Files.isDirectory(target)) {
                target = target.resolve(source.getFileName());
            }
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully.");
        } catch (IOException e) {
            System.out.println("Error moving file: " + e.getMessage());
        }
    }
}
