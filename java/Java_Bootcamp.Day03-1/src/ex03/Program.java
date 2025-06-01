package ex03;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Program {
    private static final String FILE_URLS = "files_urls.txt";
    private static Queue<FileDownloadTask> downloadQueue = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.out.println("Usage: java Program --threadsCount=<number>");
            System.exit(-1);
        }

        int threadsCount = Integer.parseInt(args[0].substring(15));
        readFileUrls(FILE_URLS);

        // Создаем пул потоков с фиксированным количеством потоков
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);

        // Запускаем потоки, пока есть файлы для загрузки
        while (!downloadQueue.isEmpty()) {
            FileDownloadTask task = downloadQueue.poll();
            if (task != null) {
                executorService.submit(new FileDownloader(task.getFileNumber(), task.getFileUrl()));
            }
        }

        // Ожидание завершения всех потоков
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    private static  void readFileUrls(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int fileNumber = 1; // Добавим автоматический инкремент для номера файла
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Убираем пробелы по краям строки
                if (!line.isEmpty()) {
                    downloadQueue.add(new FileDownloadTask(fileNumber++, line));  // Добавляем задачу в очередь, с автоинкрементом номера
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class FileDownloadTask {
        private final int fileNumber;
        private final String fileUrl;

        public FileDownloadTask(int fileNumber, String fileUrl) {
            this.fileNumber = fileNumber;
            this.fileUrl = fileUrl;
        }

        public int getFileNumber() {
            return fileNumber;
        }

        public String getFileUrl() {
            return fileUrl;
        }
    }
}
