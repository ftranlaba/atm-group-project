package dao.util;

import dao.IBaseDAO;
import dao.concretedaos.*;
import dao.util.exceptions.UnsupportedFactoryException;

/**
 * @author Moussa
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class JDBCDAOFactory implements IDAOFactory {
    protected JDBCDAOFactory(){ }

    @Override
    public IBaseDAO getDAO(String modal) {
        switch (modal.toLowerCase()) {
            case "IAccountAccessHistoryDAO":
                return new AccountAccessHistoryDAO();
            case "IAccountsDAO":
                return new AccountsDAO();
            case "ICardsDAO":
                return new CardsDAO();
            case "IDepositWithdrawHistoryDAO":
                return new DepositWithdrawHistoryDAO();
            case "ITransactionsDAO":
                return new TransactionsDAO();
            case "ITransfersDAO":
                return new TransfersDAO();
            case "IUsersDAO":
                return new UsersDAO();
            default:
                throw new UnsupportedFactoryException("JDBC Factory Type not supported.");
        }
    }
}