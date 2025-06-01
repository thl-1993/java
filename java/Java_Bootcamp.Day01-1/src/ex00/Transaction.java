package ex00;

import java.util.UUID;

public class Transaction {
    public enum Category {
        INCOME, OUTCOME
    }

    private final UUID transactionId;
    private final User recipient;
    private final User sender;
    private final Category category;
    private final double amount;

    public Transaction(User recipient, User sender, Category category, double amount) {
        this(UUID.randomUUID(), recipient, sender, category, amount);
    }

    public Transaction(UUID transactionId, User recipient, User sender, Category category, double amount) {
        if (category == Category.OUTCOME && amount >= 0) {
            System.err.println("Сумма для исходящих транзакций должна быть отрицательной");
            System.exit(-1);
        } else if (category == Category.INCOME && amount <= 0) {
            System.err.println("Cумма для входящих транзакций должна быть положительной");
            System.exit(-1);
        }
        if (category == Category.OUTCOME && sender.getBalanceUser() + amount < 0) {
            System.err.println("Недостаточно средств для выполнения транзакции");
            System.exit(-1);
        }
        if (category == Category.INCOME && sender.getBalanceUser() + amount > 0) {
            System.err.println("Недостаточно средств для выполнения транзакции");
            System.exit(-1);
        }

        this.transactionId = transactionId;
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        this.amount = amount;
        if (category == Category.OUTCOME) {
            sender.setBalanceUser(sender.getBalanceUser() + amount);
            recipient.setBalanceUser(recipient.getBalanceUser() - amount);
        } else if (category == Category.INCOME) {
            recipient.setBalanceUser(recipient.getBalanceUser() + amount);
            sender.setBalanceUser(sender.getBalanceUser() - amount);
        }
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + "\n" +
                "Sender: " + sender.getName() + "\n" +
                "Recipient: " + recipient.getName() + "\n" +
                "Category: " + category + "\n" +
                "Amount: " + amount;
    }
}
