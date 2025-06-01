package ex00;


public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.out.println("Usage: java Program --count=<number>");
            System.exit(-1);
        }
        int count = Integer.parseInt(args[0].substring(8));
        Thread eggThread = new Thread(new Egg(count));
        Thread henThread = new Thread(new Hen(count));
        eggThread.start();
        henThread.start();
        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
