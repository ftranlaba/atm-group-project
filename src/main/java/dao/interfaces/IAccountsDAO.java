package dao.interfaces;

import dao.exceptions.DAOException;
import datamodels.Account;
import datamodels.Card;
import datamodels.User;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @author Moussa
 */
<<<<<<< HEAD
public interface IAccountsDAO extends IBaseDAO<Account>{
}
=======
public interface IAccountsDAO extends IBaseDAO<Account> {
    /**
     * @param card The card to get the account of. The only fields that need to be set are
     *             the number, expiration date, and cvc.
     * @param pin  The pin of the account.
     * @return The account corresponding to the card.
     * If no account is found or the pin is incorrect then null is returned.
     * @throws SQLException If a database access error occurs.
     */
    Account getAccount(Card card, int pin) throws SQLException;

    /**
     * @param account The account to make the deposit from.
     * @param amount  The amount to deposit.
     * @throws SQLException If a database access error occurs.
     */
    void makeDeposit(Account account, BigDecimal amount) throws SQLException;

    /**
     * @param account The account to make the withdrawal from.
     * @param amount  The amount to withdraw.
     * @throws SQLException If a database access error occurs.
     * @throws DAOException If the account does not have enough money to make the withdrawal.
     */
    void makeWithdrawal(Account account, BigDecimal amount) throws SQLException, DAOException;

    /**
     * @param from   The account to transfer from.
     * @param to     The account to transfer to.
     * @param amount The amount to transfer.
     * @throws SQLException If a database access error occurs.
     * @throws DAOException If the from account does not have enough money to make the transfer.
     */
    void makeTransfer(Account from, Account to, BigDecimal amount) throws SQLException, DAOException;

    /**
     * @param user    The user to create the account for. The user should already be in the users table.
     * @param account An account object with the PIN and type fields set. It will be modified inplace to hold the info of the created account.
     * @param card    An uninitialized card object. It will be modified inplace to hold the info of the created card.
     * @throws SQLException If a database access error occurs.
     */
    void createAccount(User user, Account account, Card card) throws SQLException;
}
>>>>>>> 8c0cfe6aa541b68c9ba34970652db7ac3ea71762
