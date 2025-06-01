package ex03;

import java.util.UUID;

public interface TransactionsList {
    void add(Transaction transaction);

    Transaction remove(UUID identifier);

    Transaction[] toArray();
}
