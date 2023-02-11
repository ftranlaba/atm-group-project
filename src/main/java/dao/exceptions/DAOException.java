package dao.exceptions;

/**
 * @author Moussa
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }
}
