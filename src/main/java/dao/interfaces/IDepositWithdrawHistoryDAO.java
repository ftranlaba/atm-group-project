package dao.interfaces;

import dao.IBaseDAO;
import datamodels.Account;
import datamodels.DepositWithdraw;

import java.math.BigDecimal;

/**
 * @author Moussa
 */
public interface IDepositWithdrawHistoryDAO extends IBaseDAO<DepositWithdraw> {

    void logDepositOrWithdrawal(Account account, BigDecimal oldBalance, BigDecimal newBalance, String type);
}
