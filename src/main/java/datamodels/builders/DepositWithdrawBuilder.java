package datamodels.builders;

import datamodels.BalanceInfo;
import datamodels.DepositWithdraw;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DepositWithdrawBuilder extends BalanceInfo {
    private String type;

    public DepositWithdrawBuilder(int id, int idForeignKey, Timestamp time, BigDecimal oldBalance,
                                  BigDecimal newBalance) {
        super(id, idForeignKey, time, oldBalance, newBalance);
    }

    public DepositWithdrawBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public DepositWithdraw build() {
        DepositWithdraw depositWithdraw = new DepositWithdraw();
        depositWithdraw.setId(getId());
        depositWithdraw.setIdForeignKey(getIdForeignKey());
        depositWithdraw.setTime(getTime());
        depositWithdraw.setOldBalance(getOldBalance());
        depositWithdraw.setNewBalance(getNewBalance());
        depositWithdraw.setType(type);
        return depositWithdraw;
    }
}
