package ex02;

public interface IUserList {
    void addUser(User user); //добавление польз-я
    User getUserById(int id);//получения польз-я по id
    User getUserByIndex(int index);//получение польз-ля по индексу
    int getUsersCount();
}
