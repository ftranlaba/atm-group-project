package service;

import dao.interfaces.*;
import dao.util.DBFactoryGenerator;
import dao.util.enums.DBConnectionType;
import datamodels.*;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class JDBCService implements IService {
    private static final IAccountAccessHistoryDAO ACCOUNT_ACCESS_HISTORY_DAO = (IAccountAccessHistoryDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("IAccountAccessHistoryDAO");

    private static final IAccountsDAO ACCOUNTS_DAO = (IAccountsDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("IAccountsDAO");

    private static final ICardsDAO CARDS_DAO = (ICardsDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("ICardsDAO");

    private static final IDepositWithdrawHistoryDAO DEPOSIT_WITHDRAW_HISTORY_DAO = (IDepositWithdrawHistoryDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("IDepositWithdrawHistoryDAO");

    private static final ITransactionsDAO TRANSACTIONS_DAO = (ITransactionsDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("ITransactionsDAO");

    private static final ITransfersDAO TRANSFERS_DAO = (ITransfersDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("ITransfersDAO");

    private static final IUsersDAO USERS_DAO = (IUsersDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("IUsersDAO");

    private static JDBCService instance = null;

    private JDBCService(){ }

    public static JDBCService getInstance(){
        if(instance == null){
            instance = new JDBCService();
        }
        return instance;
    }

    @Override
    public void createAccountAccessHistory(AccountAccess o) {
        ACCOUNT_ACCESS_HISTORY_DAO.create(o);
    }

    @Override
    public AccountAccess getByIdAccountAccessHistory(int id) {
        return ACCOUNT_ACCESS_HISTORY_DAO.getById(id);
    }

    @Override
    public List<AccountAccess> getAllAccountAccessHistory() {
        return ACCOUNT_ACCESS_HISTORY_DAO.getAll();
    }

    @Override
    public void updateAccountAccessHistory(AccountAccess o) {
        ACCOUNT_ACCESS_HISTORY_DAO.update(o);
    }

    @Override
    public void deleteAccountAccessHistory(int id) {
        ACCOUNT_ACCESS_HISTORY_DAO.delete(id);
    }

    @Override
    public void createAccount(Account o) {
        ACCOUNTS_DAO.create(o);
    }

    @Override
    public Account getByIdAccount(int id) {
        return ACCOUNTS_DAO.getById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return ACCOUNTS_DAO.getAll();
    }

    @Override
    public void updateAccount(Account o) {
        ACCOUNTS_DAO.update(o);
    }

    @Override
    public void deleteAccount(int id) {
        ACCOUNTS_DAO.delete(id);
    }

    @Override
    public void makeDepositAccount(Account o, BigDecimal num) {
        ACCOUNTS_DAO.makeDeposit(o, num);
    }

    @Override
    public void makeWithdrawalAccount(Account o, BigDecimal num) {
        ACCOUNTS_DAO.makeWithdrawal(o, num);
    }

    @Override
    public void createCard(Card o) {
        CARDS_DAO.create(o);
    }

    @Override
    public Card getByIdCard(int id) {
        return CARDS_DAO.getById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return CARDS_DAO.getAll();
    }

    @Override
    public void updateCard(Card o) {
        CARDS_DAO.update(o);
    }

    @Override
    public void deleteCard(int id) {
        CARDS_DAO.delete(id);
    }

    @Override
    public void toggleBlockStatus(Card o){
        CARDS_DAO.toggleBlockStatus(o);
    }

    @Override
    public void createDepositWithdrawHistory(DepositWithdraw o) {
        DEPOSIT_WITHDRAW_HISTORY_DAO.create(o);
    }

    @Override
    public DepositWithdraw getByIdDepositWithdrawHistory(int id) {
        return DEPOSIT_WITHDRAW_HISTORY_DAO.getById(id);
    }

    @Override
    public List<DepositWithdraw> getAllDepositWithdrawHistory() {
        return DEPOSIT_WITHDRAW_HISTORY_DAO.getAll();
    }

    @Override
    public void updateDepositWithdrawHistory(DepositWithdraw o) {
        DEPOSIT_WITHDRAW_HISTORY_DAO.update(o);
    }

    @Override
    public void deleteDepositWithdrawHistory(int id) {
        DEPOSIT_WITHDRAW_HISTORY_DAO.delete(id);
    }

    @Override
    public void createTransaction(Transaction o) {
        TRANSACTIONS_DAO.create(o);
    }

    @Override
    public Transaction getByIdTransaction(int id) {
        return TRANSACTIONS_DAO.getById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return TRANSACTIONS_DAO.getAll();
    }

    @Override
    public void updateTransactions(Transaction o) {
        TRANSACTIONS_DAO.update(o);
    }

    @Override
    public void deleteTransaction(int id) {
        TRANSACTIONS_DAO.delete(id);
    }

    @Override
    public void createTransfer(Transfer o) {
        TRANSFERS_DAO.create(o);
    }

    @Override
    public Transfer getByIdTransfer(int id) {
        return TRANSFERS_DAO.getById(id);
    }

    @Override
    public List<Transfer> getAllTransfers() {
        return TRANSFERS_DAO.getAll();
    }

    @Override
    public void updateTransfer(Transfer o) {
        TRANSFERS_DAO.update(o);
    }

    @Override
    public void deleteTransfer(int id) {
        TRANSFERS_DAO.delete(id);
    }

    @Override
    public void createUser(User o) {
        USERS_DAO.create(o);
    }

    @Override
    public User getByIdUser(int id) {
        return USERS_DAO.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return USERS_DAO.getAll();
    }

    @Override
    public void updateUser(User o) {
        USERS_DAO.update(o);
    }

    @Override
    public void deleteUser(int id) {
        USERS_DAO.delete(id);
    }
}
