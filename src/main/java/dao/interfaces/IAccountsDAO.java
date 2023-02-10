package dao.interfaces;

import datamodels.Account;
import datamodels.Card;

import java.sql.SQLException;

/**
 * @author Moussa
 */
public interface IAccountsDAO extends IBaseDAO<Account> {
    Account getAccountByCard(Card card) throws SQLException;
}
