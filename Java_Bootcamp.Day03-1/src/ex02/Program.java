package ex02;

import java.util.Random;

public class Program {

    public static void main(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
            System.out.println("Usage: java Program --arraySize=<number> --threadsCount=<number>");
            System.exit(-1);
        }

        int arraySize = Integer.parseInt(args[0].substring(12));
        int threadsCount = Integer.parseInt(args[1].substring(15));

        if (threadsCount > arraySize) {
            System.err.println("Error: The number of threads cannot be greater than the size of the array.");
            System.exit(-1);
        }

        int[] array = generateRandomArray(arraySize);
        int totalSum = calculateArraySum(array);
        System.out.println("Sum: " + totalSum);

        // Разделение массива для потоков
        SumThread[] threads = new SumThread[threadsCount];
        int sumByThreads = 0;
        int sectionSize = arraySize / threadsCount;

        for (int i = 0; i < threadsCount; i++) {
            int start = i * sectionSize;
            int end = (i == threadsCount - 1) ? arraySize : start + sectionSize;
            threads[i] = new SumThread(array, start, end, i + 1);
            threads[i].start();
        }

        // Ожидаем завершения всех потоков и собираем результат
        for (int i = 0; i < threadsCount; i++) {
            try {
                threads[i].join();
                sumByThreads += threads[i].getPartialSum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum by threads: " + sumByThreads);
    }
    // Метод для генерации случайного массива
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(2001) - 1000; // Числа от -1000 до 1000
        }
        return array;
    }

    // Метод для вычисления суммы массива стандартным способом
    private static int calculateArraySum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}