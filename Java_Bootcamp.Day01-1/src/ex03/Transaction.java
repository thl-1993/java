package ex03;

import java.util.UUID;

public class Transaction {
    private final UUID identifier;
    private final User recipient;
    private final User sender;
    private final TransferCategory transferCategory;
    private final int transferAmount;

    public Transaction(User recipient, User sender, TransferCategory transferCategory, int transferAmount) {
        this(UUID.randomUUID(), recipient, sender, transferCategory, transferAmount);
    }

    public Transaction(UUID identifier, User recipient, User sender, TransferCategory transferCategory, int transferAmount) {
        if (transferCategory == TransferCategory.credits && transferAmount > 0)
            throw new IllegalArgumentException("outgoing:" + transferCategory + " (negative amounts only) transactions:" + transferAmount);
        if (transferCategory == TransferCategory.debits && transferAmount < 0)
            throw new IllegalArgumentException("incoming:" + transferCategory + " (positive amounts only) transactions:" + transferAmount);

        this.identifier = identifier;
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getTransferCategory() {
        return transferCategory;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "UUID=" + identifier +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", " + transferCategory +
                ", [" + transferAmount +
                "]}";
    }

    public enum TransferCategory {
        debits,
        credits
    }


}
