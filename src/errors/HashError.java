package errors;

public class HashError extends Exception {

    public HashError() {
        super("Unspecified HashError");
    }

    public HashError(String message) {
        super(message);
    }
}
