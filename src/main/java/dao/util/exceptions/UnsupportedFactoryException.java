package dao.util.exceptions;

public class UnsupportedFactoryException extends RuntimeException {
    public UnsupportedFactoryException(String message) {
        super(message);
    }

    public UnsupportedFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}