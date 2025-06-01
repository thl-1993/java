package ex04;

public class User {
    private final int id;
    private final TransactionsLinkedList list = new TransactionsLinkedList();
    private String name;
    private int balance;

    public User(String name, int balance) {
        if (balance < 0)
            throw new IllegalArgumentException("initial user balance (it cannot be negative:" + balance + ")");

        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
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

    public void addTransaction(Transaction transaction) {
        if (transaction.getSender().getId() == id &&
                balance + transaction.getTransferAmount() >= 0) {
            balance += transaction.getTransferAmount();
            list.add(transaction);
        } else {
            throw new IllegalTransactionException("transfer of the amount exceeding user's residual balance(" +
                    balance + transaction.getTransferAmount() + ")");
        }
    }

    @Override
    public String toString() {
        return "{" + name + "[" + balance +
                "]}";
    }
}
