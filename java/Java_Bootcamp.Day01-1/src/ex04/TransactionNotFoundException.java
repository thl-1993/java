package ex04;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "TransactionNotFoundException: " + super.getMessage();
    }
}
