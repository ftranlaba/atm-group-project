package dao.util;

import dao.IBaseDAO;
import dao.concretedaos.*;
import dao.util.exceptions.UnsupportedFactoryException;
import datamodels.IdInfo;

/**
 * @author Francis
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class JDBCDAOFactory implements IDAOFactory {
    protected JDBCDAOFactory() {}

    @Override
    public IBaseDAO<? extends IdInfo> getDAO(String modal) {
        switch (modal) {
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