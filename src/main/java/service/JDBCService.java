package service;

import dao.concretedaos.AccountsDAO;
import dao.interfaces.*;
import dao.util.DBFactoryGenerator;
import dao.util.IDAOFactory;
import dao.util.enums.DBConnectionType;
import datamodels.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public final class JDBCService implements IService {
    private static final IAccountAccessHistoryDAO ACCOUNT_ACCESS_HISTORY_DAO;
    private static final IAccountsDAO ACCOUNTS_DAO;
    private static final ICardsDAO CARDS_DAO;
    private static final IDepositWithdrawHistoryDAO DEPOSIT_WITHDRAW_HISTORY_DAO;
    private static final ITransactionsDAO TRANSACTIONS_DAO;
    private static final ITransfersDAO TRANSFERS_DAO;
    private static final IUsersDAO USERS_DAO;

    static {
        IDAOFactory factory = DBFactoryGenerator.getFactory(DBConnectionType.JDBC);
        ACCOUNT_ACCESS_HISTORY_DAO = (IAccountAccessHistoryDAO) factory.getDAO("IAccountAccessHistoryDAO");
        ACCOUNTS_DAO = (IAccountsDAO) factory.getDAO("IAccountsDAO");
        CARDS_DAO = (ICardsDAO) factory.getDAO("ICardsDAO");
        DEPOSIT_WITHDRAW_HISTORY_DAO = (IDepositWithdrawHistoryDAO) factory.getDAO("IDepositWithdrawHistoryDAO");
        TRANSACTIONS_DAO = (ITransactionsDAO) factory.getDAO("ITransactionsDAO");
        TRANSFERS_DAO = (ITransfersDAO) factory.getDAO("ITransfersDAO");
        USERS_DAO = (IUsersDAO) factory.getDAO("IUsersDAO");
    }

    private static JDBCService instance = null;

    private JDBCService() {
    }

    public static JDBCService getInstance() {
        if (instance == null) {
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
    public void createAccount(User u, Account o, Card c) {
        ACCOUNTS_DAO.createAccount(u, o, c);
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
    public Account getAccount(Card o, int pin) throws SQLException {
        return ACCOUNTS_DAO.getAccount(o, pin);
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
    public void toggleBlockStatus(Card o) {
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
    public void makeTransfer(Account from, Account to, BigDecimal amount) {
        ACCOUNTS_DAO.makeTransfer(from, to, amount);
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
