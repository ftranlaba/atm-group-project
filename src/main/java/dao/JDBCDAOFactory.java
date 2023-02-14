package dao;

import dao.concretedaos.*;
import dao.interfaces.*;

/**
 * @author Moussa
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class JDBCDAOFactory implements IDAOFactory {
    @Override
    public IAccountsDAO getAccountsDAO() {
        return new AccountsDAO();
    }

    @Override
    public IAccountAccessHistoryDAO getAccountAccessHistoryDAO() {
        return new AccountAccessHistoryDAO();
    }

    @Override
    public ICardsDAO getCardsDAO() {
        return new CardsDAO();
    }

    @Override
    public IDepositWithdrawHistoryDAO getDepositWithdrawHistoryDAO() {
        return new DepositWithdrawHistoryDAO();
    }

    @Override
    public ITransactionsDAO getTransactionsDAO() {
        return new TransactionsDAO();
    }

    @Override
    public ITransfersDAO getTransfersDAO() {
        return new TransfersDAO();
    }

    @Override
    public IUsersDAO getUsersDAO() {
        return new UsersDAO();
    }
}
