package ex03;

public class User {
    private final int id;
    private String name;
    private int balance;

    private TransactionsLinkedList list;

    public User(String name, int balance) {
        if (balance < 0)
            throw new IllegalArgumentException("initial user balance (it cannot be negative:" + balance + ")");

        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
        list = new TransactionsLinkedList();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public TransactionsLinkedList getList() {
        return list;
    }

    @Override
    public String toString() {
        return "{" + name + "[" + balance +
                "]}";
    }
}
