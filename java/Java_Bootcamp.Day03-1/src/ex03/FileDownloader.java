package ex03;

import java.io.*;
import java.net.URL;

public class FileDownloader implements Runnable {
    private final int fileNumber;
    private final String fileUrl;

    public FileDownloader(int fileNumber, String fileUrl) {
        this.fileNumber = fileNumber;
        this.fileUrl = fileUrl;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " start download file number " + fileNumber);

            // Извлекаем имя файла из URL
            String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);

            // Извлекаем расширение файла
            String fileExtension = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf('.')) : ".dat";

            // Создаем имя файла с номером файла и расширением
            String fullFileName = "file_" + fileNumber + fileExtension;

            // Скачиваем файл
            downloadFile(fileUrl, fullFileName);

            System.out.println(Thread.currentThread().getName() + " finish download file number " + fileNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFile(String fileUrl, String fileName) throws IOException {
        URL url = new URL(fileUrl);
        try (InputStream in = url.openStream();
             FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
