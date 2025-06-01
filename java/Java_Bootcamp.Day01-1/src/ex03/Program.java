package ex03;

public class Program {
    public static void main(String[] args) {
        TransactionsLinkedList list = new TransactionsLinkedList();
        User user1 = new User("First", 1);
        User user2 = new User("Second", 2);
        User user3 = new User("Third", 3);

        System.out.println(list);
        list.add(new Transaction(user1, user2, Transaction.TransferCategory.credits, -1));
        System.out.println(list);
        list.add(new Transaction(user2, user3, Transaction.TransferCategory.debits, 2));
        System.out.println(list);
        list.add(new Transaction(user3, user1, Transaction.TransferCategory.credits, -3));
        System.out.println(list);

        System.out.println("list.toArray():");
        Transaction[] array = list.toArray();
        for (Transaction transaction : array) {
            System.out.println(transaction);
        }

        list.remove(array[1].getIdentifier());
        System.out.println("remove array[1]: " + list);
        list.remove(array[0].getIdentifier());
        System.out.println("remove array[0]: " + list);
        try {
            System.out.println("remove not exist: ");
            list.remove(array[0].getIdentifier());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("finish result: " + list);

        user1.getList().add(array[0]);
        user1.getList().add(array[2]);

        user2.getList().add(array[0]);
        user2.getList().add(array[1]);

        user3.getList().add(array[1]);
        user3.getList().add(array[2]);

        System.out.println(user1 + "\n" + user1.getList());
        System.out.println(user2 + "\n" + user2.getList());
        System.out.println(user3 + "\n" + user3.getList());
    }
}