package dao.interfaces;

import dao.IBaseDAO;
import datamodels.Account;
import datamodels.AccountAccess;

/**
 * @author Moussa
 */
public interface IAccountAccessHistoryDAO extends IBaseDAO<AccountAccess> {
    void logAccountAccess(Account account);
}

