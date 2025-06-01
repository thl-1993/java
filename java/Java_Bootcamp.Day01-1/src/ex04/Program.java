package ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        TransactionsService service = new TransactionsService();

        service.addUser(new User("First", 1000));
        service.addUser(new User("Second", 200));
        service.addUser(new User("Third", 300));
        service.addUser(new User("Four", 400));
        service.addUser(new User("Five", 500));


        service.transferTransaction(1, 2, 100);

        try {
            service.transferTransaction(4, 1, 1000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            service.transferTransaction(10, 1, 10);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println(service);

        printUnpaired(service.checkTransactions());

        UUID uuid = service.getUsersList().getById(1).getList().toArray()[0].getIdentifier();
        service.removeTransactionByTransactionIdAndUserId(uuid, 1);

        printUnpaired(service.checkTransactions());

        service.transferTransaction(1, 4, 200);

        uuid = service.getUsersList().getById(1).getList().toArray()[0].getIdentifier();
        service.removeTransactionByTransactionIdAndUserId(uuid, 1);

        printUnpaired(service.checkTransactions());

        service.transferTransaction(1, 3, 300);


        uuid = service.getUsersList().getById(1).getList().toArray()[0].getIdentifier();
        service.removeTransactionByTransactionIdAndUserId(uuid, 1);

        printUnpaired(service.checkTransactions());

        service.transferTransaction(4, 1, 400);


        uuid = service.getUsersList().getById(1).getList().toArray()[0].getIdentifier();
        service.removeTransactionByTransactionIdAndUserId(uuid, 1);

        printUnpaired(service.checkTransactions());

    }

    private static void printUnpaired(Transaction[] checkTransactions) {
        System.out.println("unpairedTransactions:" + checkTransactions.length);
        for (Transaction checkTransaction : checkTransactions) {
            System.out.println(checkTransaction);
        }
    }
}