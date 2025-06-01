package ex02;

public class UserArrayList implements IUserList {
    private User[] users;
    private int count;
    private static final int DEFAULT_CAPACITY = 10;
    public UserArrayList(){
        users = new User[DEFAULT_CAPACITY];
        count = 0;
    }


    @Override
    public void addUser(User user) {
        if(count == users.length){
            User[] newUsers = new User[users.length * 2];
            System.arraycopy(users, 0, newUsers, 0, users.length);
            users = newUsers;
        }
        users[count++] = user;
    }

    @Override
    public User getUserById(int id) {
        for(int i = 0; i < count; i++){
            if(users[i].getId() == id){
                return users[i];
            }
        }
        throw new UserNotFoundException("User with ID " + id + " not found");
    }

    @Override
    public User getUserByIndex(int index) {
        if(index < 0 || index >= count){
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out if bound");
        }
        return users[index];
    }

    @Override
    public int getUsersCount() {
        return count;
    }
}
