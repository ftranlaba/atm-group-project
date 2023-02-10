package datamodels;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DepositWithdraw extends  BalanceInfo {
    private String type;

    public DepositWithdraw(int id, int idForeignKey, Timestamp time, BigDecimal oldBalance,
                           BigDecimal newBalance, String type) {
        super(id, idForeignKey, time, oldBalance, newBalance);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DepositWithdraw{" +
                "type='" + type + '\'' +
                ", oldBalance=" + this.getOldBalance() +
                ", newBalance=" + this.getNewBalance() +
                ", time=" + this.getTime() +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
