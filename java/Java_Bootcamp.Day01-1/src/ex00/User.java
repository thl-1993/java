package ex00;

public class User {
    private final int id;
    private final String name;
    private double balance;

    public User(int id, String name, double balance) {
        if (balance < 0) {
            System.err.println("balance < 0");
            System.exit(-1);
        }
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalanceUser() {
        return balance;
    }

    public void setBalanceUser(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
//        System.out.println("сработал toString");
        return "ID: " + id + ", " + "Name: " + name + ", " + "Balance: " + balance;
    }


}
