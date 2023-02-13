package dao;

import dao.interfaces.*;

public interface IDAOFactory {
    IAccountsDAO getAccountsDAO();
    IAccountAccessHistoryDAO getAccountAccessHistoryDAO();
    ICardsDAO getCardsDAO();
    IDepositWithdrawHistoryDAO getDepositWithdrawHistoryDAO();
    ITransactionsDAO getTransactionsDAO();
    ITransfersDAO getTransfersDAO();
    IUsersDAO getUsersDAO();
}
