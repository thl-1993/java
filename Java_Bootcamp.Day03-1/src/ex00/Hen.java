package ex00;

public class Hen extends Thread {
    private final int count;

    public Hen(int count) {
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Hen");
        }
    }
}
