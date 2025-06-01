package ex01;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.out.println("Usage: java Program --count=<number>");
            System.exit(-1);
        }
        int count = Integer.parseInt(args[0].substring(8));

        // Общий объект блокировки и флаг
        Object lock = new Object();
        boolean[] isEggTurn = { true }; // Используем массив, чтобы его можно было менять

        // Создаем и запускаем потоки
        Thread eggThread = new Egg(count, lock, isEggTurn);
        Thread henThread = new Hen(count, lock, isEggTurn);

        eggThread.start();
        henThread.start();

        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Выводим "Human" после завершения обоих потоков
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
