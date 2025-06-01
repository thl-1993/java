package school21.spring.service.models;

public class User {

    private Long id;
    private String Email;
    private String password;

    public User(Long id, String email, String password) {
        this.id = id;
        this.Email = email;
        this.password = password;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        Email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Email='" + Email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
