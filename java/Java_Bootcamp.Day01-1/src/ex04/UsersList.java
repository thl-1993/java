package ex04;

public interface UsersList {
    void add(User user);

    int size();

    User getById(int id);

    User getByIndex(int index);
}
