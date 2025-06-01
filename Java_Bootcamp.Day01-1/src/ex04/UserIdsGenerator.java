package ex04;

public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private int id;

    private UserIdsGenerator(int id) {
        this.id = id;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator(1);
        }
        return instance;
    }

    public int generateId() {
        return id++;
    }
}