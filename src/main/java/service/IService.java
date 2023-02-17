package service;

import datamodels.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface IService {
    void createAccountAccessHistory(AccountAccess o);

    AccountAccess getByIdAccountAccessHistory(int id);

    List<AccountAccess> getAllAccountAccessHistory();

    void updateAccountAccessHistory(AccountAccess o);

    void deleteAccountAccessHistory(int id);

    void createAccount(User u, Account o, Card c);

    Account getByIdAccount(int id);

    List<Account> getAllAccounts();

    void updateAccount(Account o);

    void deleteAccount(int id);

    void makeDepositAccount(Account o, BigDecimal num);

    Account getAccount(Card o, int pin) throws SQLException;

    void makeWithdrawalAccount(Account o, BigDecimal num);

    void createCard(Card o);

    Card getByIdCard(int id);

    List<Card> getAllCards();

    void updateCard(Card o);

    void deleteCard(int id);

    void toggleBlockStatus(Card o);

    void createDepositWithdrawHistory(DepositWithdraw o);

    DepositWithdraw getByIdDepositWithdrawHistory(int id);

    List<DepositWithdraw> getAllDepositWithdrawHistory();

    void updateDepositWithdrawHistory(DepositWithdraw o);

    void deleteDepositWithdrawHistory(int id);

    void createTransaction(Transaction o);

    Transaction getByIdTransaction(int id);

    List<Transaction> getAllTransactions();

    void updateTransactions(Transaction o);

    void deleteTransaction(int id);

    void createTransfer(Transfer o);

    void makeTransfer(Account from, Account to, BigDecimal amount);

    Transfer getByIdTransfer(int id);

    List<Transfer> getAllTransfers();

    void updateTransfer(Transfer o);

    void deleteTransfer(int id);

    void createUser(User o);

    User getByIdUser(int id);

    List<User> getAllUsers();

    void updateUser(User o);

    void deleteUser(int id);
}
