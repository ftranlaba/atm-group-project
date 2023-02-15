package service;

import dao.concretedaos.AccountsDAO;
import dao.interfaces.*;
import dao.util.DBFactoryGenerator;
import dao.util.JDBCDAOFactory;
import dao.util.enums.DBConnectionType;
import datamodels.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class JDBCService implements IService {
    private static final IAccountAccessHistoryDAO accountAccessHistoryDAO = (IAccountAccessHistoryDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("IAccountAccessHistoryDAO");

    private static final IAccountsDAO accountsDAO = (IAccountsDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("IAccountsDAO");

    private static final ICardsDAO cardsDAO = (ICardsDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("ICardsDAO");

    private static final IDepositWithdrawHistoryDAO depositWithdrawHistoryDAO = (IDepositWithdrawHistoryDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("IDepositWithdrawHistoryDAO");

    private static final ITransactionsDAO transactionsDAO = (ITransactionsDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("ITransactionsDAO");

    private static final ITransfersDAO transfersDAO = (ITransfersDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("ITransfersDAO");

    private static final IUsersDAO usersDAO = (IUsersDAO) DBFactoryGenerator.getFactory(DBConnectionType.JDBC).getDAO("IUsersDAO");

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
        accountAccessHistoryDAO.create(o);
    }

    @Override
    public AccountAccess getByIdAccountAccessHistory(int id) {
        return accountAccessHistoryDAO.getById(id);
    }

    @Override
    public List<AccountAccess> getAllAccountAccessHistory() {
        return accountAccessHistoryDAO.getAll();
    }

    @Override
    public void updateAccountAccessHistory(AccountAccess o) {
        accountAccessHistoryDAO.update(o);
    }

    @Override
    public void deleteAccountAccessHistory(int id) {
        accountAccessHistoryDAO.delete(id);
    }

    @Override
    public void createAccount(User u, Account o, Card c) {
        accountsDAO.createAccount(u, o, c);
    }

    @Override
    public Account getByIdAccount(int id) {
        return accountsDAO.getById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountsDAO.getAll();
    }

    @Override
    public void updateAccount(Account o) {
        accountsDAO.update(o);
    }

    @Override
    public void deleteAccount(int id) {
        accountsDAO.delete(id);
    }

    @Override
    public void makeDepositAccount(Account o, BigDecimal num) {
        accountsDAO.makeDeposit(o, num);
    }

    @Override
    public Account getAccount(Card o, int pin) throws SQLException {
       return accountsDAO.getAccount(o, pin);
    }

    @Override
    public void makeWithdrawalAccount(Account o, BigDecimal num) {
        accountsDAO.makeWithdrawal(o, num);
    }

    @Override
    public void createCard(Card o) {
        cardsDAO.create(o);
    }

    @Override
    public Card getByIdCard(int id) {
        return cardsDAO.getById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return cardsDAO.getAll();
    }

    @Override
    public void updateCard(Card o) {
        cardsDAO.update(o);
    }

    @Override
    public void deleteCard(int id) {
        cardsDAO.delete(id);
    }

    @Override
    public void toggleBlockStatus(Card o){
        cardsDAO.toggleBlockStatus(o);
    }

    @Override
    public void createDepositWithdrawHistory(DepositWithdraw o) {
        depositWithdrawHistoryDAO.create(o);
    }

    @Override
    public DepositWithdraw getByIdDepositWithdrawHistory(int id) {
        return depositWithdrawHistoryDAO.getById(id);
    }

    @Override
    public List<DepositWithdraw> getAllDepositWithdrawHistory() {
        return depositWithdrawHistoryDAO.getAll();
    }

    @Override
    public void updateDepositWithdrawHistory(DepositWithdraw o) {
        depositWithdrawHistoryDAO.update(o);
    }

    @Override
    public void deleteDepositWithdrawHistory(int id) {
        depositWithdrawHistoryDAO.delete(id);
    }

    @Override
    public void createTransaction(Transaction o) {
        transactionsDAO.create(o);
    }

    @Override
    public Transaction getByIdTransaction(int id) {
        return transactionsDAO.getById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionsDAO.getAll();
    }

    @Override
    public void updateTransactions(Transaction o) {
        transactionsDAO.update(o);
    }

    @Override
    public void deleteTransaction(int id) {
        transactionsDAO.delete(id);
    }

    @Override
    public void createTransfer(Transfer o) {
        transfersDAO.create(o);
    }

    @Override
    public Transfer getByIdTransfer(int id) {
        return transfersDAO.getById(id);
    }

    @Override
    public List<Transfer> getAllTransfers() {
        return transfersDAO.getAll();
    }

    @Override
    public void updateTransfer(Transfer o) {
        transfersDAO.update(o);
    }

    @Override
    public void deleteTransfer(int id) {
        transfersDAO.delete(id);
    }

    @Override
    public void createUser(User o) {
        usersDAO.create(o);
    }

    @Override
    public User getByIdUser(int id) {
        return usersDAO.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return usersDAO.getAll();
    }

    @Override
    public void updateUser(User o) {
        usersDAO.update(o);
    }

    @Override
    public void deleteUser(int id) {
        usersDAO.delete(id);
    }
}
