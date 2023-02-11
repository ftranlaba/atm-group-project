package dao.interfaces;

import datamodels.Account;
import datamodels.DepositWithdraw;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @author Moussa
 */
public interface IDepositWithdrawHistoryDAO extends IBaseDAO<DepositWithdraw> {
    void logDepositOrWithdrawal(Account account, BigDecimal oldBalance, BigDecimal newBalance, String type) throws SQLException;
}
