package datamodels.builders;

import datamodels.BalanceInfo;
import datamodels.Transfer;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransferBuilder extends BalanceInfo {

    private int idAccount2;
    private BigDecimal oldBalance2;
    private BigDecimal newBalance2;

    public TransferBuilder(int id, int idForeignKey, Timestamp time, BigDecimal oldBalance, BigDecimal newBalance) {
        super(id, idForeignKey, time, oldBalance, newBalance);
    }

    public TransferBuilder setIdAccount2(int idAccount2) {
        this.idAccount2 = idAccount2;
        return this;
    }

    public TransferBuilder setOldBalance2(BigDecimal oldBalance2) {
        this.oldBalance2 = oldBalance2;
        return this;
    }

    public TransferBuilder setNewBalance2(BigDecimal newBalance2) {
        this.newBalance2 = newBalance2;
        return this;
    }

    public Transfer build() {
        Transfer transfer = new Transfer();
        transfer.setId(getId());
        transfer.setIdForeignKey(getIdForeignKey());
        transfer.setTime(getTime());
        transfer.setOldBalance(getOldBalance());
        transfer.setNewBalance(getNewBalance());
        transfer.setIdAccount2(idAccount2);
        transfer.setOldBalance2(oldBalance2);
        transfer.setNewBalance2(newBalance2);
        return transfer;
    }
}
