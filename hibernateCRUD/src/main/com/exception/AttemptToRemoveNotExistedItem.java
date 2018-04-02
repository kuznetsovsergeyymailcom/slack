package exception;

public class AttemptToRemoveNotExistedItem extends Exception {
    public AttemptToRemoveNotExistedItem(String message) {
        super(message);
    }
}
