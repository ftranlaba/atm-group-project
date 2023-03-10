package datamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public abstract class BalanceInfo extends TimeInfo {
    @JsonProperty
    private BigDecimal oldBalance;
    @JsonProperty
    private BigDecimal newBalance;

    public BalanceInfo(int id, int idForeignKey, Timestamp time, BigDecimal oldBalance, BigDecimal newBalance) {
        super(id, idForeignKey, time);
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public BalanceInfo(int idForeignKey, Timestamp time, BigDecimal oldBalance, BigDecimal newBalance) {
        super(idForeignKey, time);
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public BalanceInfo() {

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
                ", time=" + this.getTime() +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
