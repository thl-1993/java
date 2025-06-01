package ex02;

public class User {
    private final int id;
    private final String name;
    private final int balance;
    public User(String name, int balance){
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }
    public int getId(){
        return id;
    }



    @Override
    public String toString() {
        return "User{" +
                "identifier=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }


}
