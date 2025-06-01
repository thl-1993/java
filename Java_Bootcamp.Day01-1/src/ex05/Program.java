package ex05;

public class Program {
    public static void main(String[] args) {
        new Menu(isDevArgs(args)).start();
    }

    private static boolean isDevArgs(String[] args) {
        return args.length == 1 && args[0].equals("--profile=dev");
    }
}