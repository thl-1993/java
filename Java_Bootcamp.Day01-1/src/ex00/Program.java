package ex00;

public class Program {
    public static void main(String[] args) {
        User john = new User(1, "John", 1000.00);
        User mike = new User(2, "Mike", 500.00);
        System.out.println(john);
        System.out.println(mike);
        Transaction transaction1 = new Transaction(mike, john, Transaction.Category.OUTCOME, -333);
        System.out.println(transaction1);
        System.out.println(john);
        System.out.println(mike);
        Transaction transaction2 = new Transaction(mike, john, Transaction.Category.INCOME, 888);
        System.out.println(john);
        System.out.println(mike);



    }
}
