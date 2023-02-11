package dao.interfaces;

import datamodels.Account;
import datamodels.Card;

import java.math.BigDecimal;
import java.sql.SQLException;

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
     * @return True if the withdrawal was successful, false otherwise.
     * @throws SQLException If a database access error occurs.
     */
    boolean makeWithdrawal(Account account, BigDecimal amount) throws SQLException;
}
