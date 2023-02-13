package dao.interfaces;

import datamodels.Account;
import datamodels.DepositWithdraw;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @author Moussa
 */
public interface IDepositWithdrawHistoryDAO extends IBaseDAO<DepositWithdraw> {
<<<<<<< HEAD
}
=======
    void logDepositOrWithdrawal(Account account, BigDecimal oldBalance, BigDecimal newBalance, String type) throws SQLException;
}
>>>>>>> 8c0cfe6aa541b68c9ba34970652db7ac3ea71762
