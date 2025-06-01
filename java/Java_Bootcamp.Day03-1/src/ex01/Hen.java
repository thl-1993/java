package ex01;

public class Hen extends Thread {
    private final int count;
    private final Object lock;
    private final boolean[] isEggTurn;

    public Hen(int count, Object lock, boolean[] isEggTurn) {
        this.count = count;
        this.lock = lock;
        this.isEggTurn = isEggTurn;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (lock) {
                while (isEggTurn[0]) {
                    try {
                        lock.wait(); // Ждем своей очереди
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Hen");
                isEggTurn[0] = true; // Передаем ход яйцу
                lock.notify(); // Будим поток "Egg"
            }
        }
    }
}
