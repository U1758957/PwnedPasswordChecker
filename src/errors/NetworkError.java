package errors;

public class NetworkError extends Exception {

    public NetworkError() {
        super("Unspecified NetworkError");
    }

    public NetworkError(String message) {
        super(message);
    }
}
