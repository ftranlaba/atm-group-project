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
    protected JDBCDAOFactory(){ }

    @Override
    public IBaseDAO<? extends IdInfo> getDAO(String modal) {
        switch (modal.toLowerCase()) {
            case "iaccountaccesshistorydao":
                return new AccountAccessHistoryDAO();
            case "iaccountsdao":
                return new AccountsDAO();
            case "icardsdao":
                return new CardsDAO();
            case "idepositwithdrawhistorydao":
                return new DepositWithdrawHistoryDAO();
            case "itransactionsdao":
                return new TransactionsDAO();
            case "itransfersdao":
                return new TransfersDAO();
            case "iusersdao":
                return new UsersDAO();
            default:
                throw new UnsupportedFactoryException("JDBC Factory Type not supported.");
        }
    }
}