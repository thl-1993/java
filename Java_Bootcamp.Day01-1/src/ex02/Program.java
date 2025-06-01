package ex02;

public class Program {
    public static void main(String[] args) {

        UserArrayList usersList = new UserArrayList();

        User user1 = new User("Ivan", 1);
        User user2 = new User("Ni Wey", 100);
        User user3 = new User("Johan", 150);

        usersList.addUser(user1);
        usersList.addUser(user2);
        usersList.addUser(user3);

        System.out.println("All users: ");
        for (int i = 0; i< usersList.getUsersCount(); i++){
            System.out.println(usersList.getUserByIndex(i));
        }
        System.out.println("User with ID 2: " + usersList.getUserById(2));

        try {
            System.out.println(usersList.getUserById(101));
        }catch (UserNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}