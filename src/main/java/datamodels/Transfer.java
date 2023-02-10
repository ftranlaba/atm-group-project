package datamodels;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transfer extends BalanceInfo {
    private int idAccount2;
    private BigDecimal oldBalance2;
    private BigDecimal newBalance2;

    public Transfer(int id, int idForeignKey, Timestamp time, BigDecimal oldBalance, BigDecimal newBalance,
                    int idAccount2, BigDecimal oldBalance2, BigDecimal newBalance2) {
        super(id, idForeignKey, time, oldBalance, newBalance);
        this.idAccount2 = idAccount2;
        this.oldBalance2 = oldBalance2;
        this.newBalance2 = newBalance2;
    }

    public Transfer() {
        super();
    }

    public int getIdAccount2() {
        return this.idAccount2;
    }

    public void setIdAccount2(int idAccount2) {
        this.idAccount2 = idAccount2;
    }

    public BigDecimal getOldBalance2() {
        return this.oldBalance2;
    }

    public void setOldBalance2(BigDecimal oldBalance2) {
        this.oldBalance2 = oldBalance2;
    }

    public BigDecimal getNewBalance2() {
        return this.newBalance2;
    }

    public void setNewBalance2(BigDecimal newBalance2) {
        this.newBalance2 = newBalance2;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "idAccount2=" + idAccount2 +
                ", oldBalance2=" + oldBalance2 +
                ", newBalance2=" + newBalance2 +
                ", oldBalance=" + this.getOldBalance() +
                ", newBalance=" + this.getNewBalance() +
                ", time=" + this.getTime() +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
