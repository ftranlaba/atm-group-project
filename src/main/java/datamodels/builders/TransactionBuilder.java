package datamodels.builders;

import datamodels.TimeInfo;
import datamodels.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionBuilder extends TimeInfo {
    private String merchantName;
    private BigDecimal cost;

    public TransactionBuilder(int id, int idForeignKey, Timestamp time) {
        super(id, idForeignKey, time);
    }

    public TransactionBuilder setMerchantName(String merchantName){
        this.merchantName = merchantName;
        return this;
    }

    public TransactionBuilder setCost(BigDecimal cost){
        this.cost = cost;
        return this;
    }

    public Transaction build(){
        Transaction transaction = new Transaction();
        transaction.setId(getId());
        transaction.setIdForeignKey(getIdForeignKey());
        transaction.setTime(getTime());
        transaction.setMerchantName(merchantName);
        transaction.setCost(cost);
        return transaction;
    }
}
