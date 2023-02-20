package dao.interfaces;

import dao.IBaseDAO;
import datamodels.Account;
import datamodels.Card;
import datamodels.User;

import java.math.BigDecimal;

/**
 * @author Moussa
 */
public interface IAccountsDAO extends IBaseDAO<Account> {
    /**
     * @param card The card to get the account of. The only fields that need to be set are
     *             the number, expiration date, and cvc.
     * @param pin  The pin of the account.
     * @return The account corresponding to the card.
     * If no account is found or the pin is incorrect then null is returned.
     */
    Account getAccount(Card card, int pin);

    /**
     * @param account The account to make the deposit from.
     * @param amount  The amount to deposit.
     */
    void makeDeposit(Account account, BigDecimal amount);

    /**
     * @param account The account to make the withdrawal from.
     * @param amount  The amount to withdraw.
     */
    void makeWithdrawal(Account account, BigDecimal amount);

    /**
     * @param from   The account to transfer from.
     * @param to     The account to transfer to.
     * @param amount The amount to transfer.
     * @return True if the transfer was successful, false otherwise.
     */
    boolean makeTransfer(Account from, Account to, BigDecimal amount);

    /**
     * @param user    The user to create the account for. The user should already be in the users table.
     * @param account An account object with the PIN and type fields set. It will be modified inplace to hold the info of the created account.
     * @param card    An uninitialized card object. It will be modified inplace to hold the info of the created card.
     */
    void createAccount(User user, Account account, Card card);
}
