package dao.util.exceptions;

/**
 * @author Moussa
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
