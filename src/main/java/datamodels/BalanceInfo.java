package datamodels;

import java.math.BigDecimal;
import java.sql.Timestamp;

public abstract class BalanceInfo extends TimeInfo {

    protected BigDecimal oldBalance;
    protected BigDecimal newBalance;

    public BalanceInfo(int id, int idForeignKey, Timestamp time, BigDecimal oldBalance, BigDecimal newBalance) {
        super(id, idForeignKey, time);
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public BigDecimal getOldBalance() {
        return this.oldBalance;
    }

    public void setOldBalance(BigDecimal oldBalance) {
        this.oldBalance = oldBalance;
    }

    public BigDecimal getNewBalance() {
        return this.newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    @Override
    public String toString() {
        return "BalanceInfo{" +
                "oldBalance=" + oldBalance +
                ", newBalance=" + newBalance +
                ", time=" + time +
                ", idForeignKey=" + idForeignKey +
                ", id=" + id +
                '}';
    }
}
