package exception;

public class UnknownDaoType extends Exception {
    public UnknownDaoType(String message) {
        super(message);
    }
}
