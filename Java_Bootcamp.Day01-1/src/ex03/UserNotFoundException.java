package ex03;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "UserNotFoundException: " + super.getMessage();
    }
}
