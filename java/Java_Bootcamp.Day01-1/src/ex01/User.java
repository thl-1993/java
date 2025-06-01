package ex01;

public class User {
    private final int id;
    private String name;
    private final int balance;
    public User(String name, int balance){
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getBalance(){
        return balance;
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
