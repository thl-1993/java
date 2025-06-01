package ex04;


public class UsersArrayList implements UsersList {
    private int size;
    private int maxSize;
    private User[] list;

    public UsersArrayList() {
        size = 0;
        maxSize = 1;
        list = new User[maxSize];
    }

    @Override
    public void add(User user) {
        if (size >= maxSize) {
            maxSize *= 2;
            User[] newList = new User[maxSize];
            System.arraycopy(list, 0, newList, 0, size);
            list = newList;
        }
        list[size++] = user;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public User getById(int id) {
        for (int i = 0; i < size; i++) {
            if (list[i].getId() == id) {
                return list[i];
            }
        }
        throw new UserNotFoundException("Attempt to retrieve a user with a non-existent ID:" + id);
    }

    @Override
    public User getByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new UserNotFoundException("Attempt to retrieve a user with a non-existent Index:" + index);
        }
        return list[index];
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += list[i] + "\n";
        }
        return "UsersArrayList{" +
                "size=" + size +
                ", maxSize=" + maxSize +
                "}\n" + result;
    }
}
