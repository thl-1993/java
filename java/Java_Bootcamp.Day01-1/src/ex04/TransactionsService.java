package ex04;

import java.util.UUID;

public class TransactionsService {


    private final UsersList usersList = new UsersArrayList();

    public void addUser(User user) {
        usersList.add(user);
    }

    public User getUserById(int id) {
        return usersList.getById(id);
    }

    public int getUserBalanceById(int id) {
        return usersList.getById(id).getBalance();
    }

    public void transferTransaction(int idSender, int idRecipient, int transferAmount) {
        User sender = usersList.getById(idSender);
        User recipient = usersList.getById(idRecipient);
        UUID uuid = UUID.randomUUID();
        Transaction transactionSender =
                new Transaction(uuid, sender, recipient, Transaction.TransferCategory.credits, -transferAmount);
        Transaction transactionRecipient =
                new Transaction(uuid, recipient, sender, Transaction.TransferCategory.debits, transferAmount);
        sender.addTransaction(transactionSender);
        recipient.addTransaction(transactionRecipient);
    }

    public Transaction[] checkTransactions() {
        Transaction[] allTransactions = new Transaction[10000];
        int allSize = 0;
        for (int i = 0; i < usersList.size(); i++) {
            Transaction[] transactions = usersList.getByIndex(i).getList().toArray();
            System.arraycopy(transactions, 0, allTransactions, allSize, transactions.length);
            allSize += transactions.length;
        }
        for (int i = 0; i < allSize; i++) {
            for (int j = i; j < allSize; j++) {
                if (allTransactions[i].getIdentifier().compareTo(allTransactions[j].getIdentifier()) > 0) {
                    Transaction tmp = allTransactions[i];
                    allTransactions[i] = allTransactions[j];
                    allTransactions[j] = tmp;
                }
            }
        }
        Transaction[] unpairedTransactions = new Transaction[10000];
        int unpairedSize = 0;
        for (int i = 1; i < allSize; i++) {
            if (allTransactions[i].getIdentifier().equals(allTransactions[i - 1].getIdentifier())) {
                i++;
            } else {
                unpairedTransactions[unpairedSize++] = allTransactions[i - 1];
            }
        }
        Transaction[] result;
        if (allSize != 1) {
            if (!allTransactions[allSize - 1].getIdentifier().equals(allTransactions[allSize - 2].getIdentifier())) {
                unpairedTransactions[unpairedSize++] = allTransactions[allSize - 1];
            }
            result = new Transaction[unpairedSize];
            System.arraycopy(unpairedTransactions, 0, result, 0, unpairedSize);
        } else {
            result = new Transaction[1];
            System.arraycopy(allTransactions, 0, result, 0, 1);
        }
        return result;
    }

    public Transaction removeTransactionByTransactionIdAndUserId(UUID uuid, int userId) {
        return usersList.getById(userId).getList().remove(uuid);
    }

    public UsersList getUsersList() {
        return usersList;
    }

    @Override
    public String toString() {
        String result = "TransactionsService:\n";
        for (int i = 0; i < usersList.size(); i++) {
            result += usersList.getByIndex(i) + "\n";
            result += usersList.getByIndex(i).getList() + "\n";
        }
        return result;
    }
}
