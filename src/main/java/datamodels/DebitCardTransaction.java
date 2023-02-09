package datamodels;

import java.sql.Timestamp;

public class DebitCardTransaction {
    private int debitCardTransactionId;
    private int debitCardId;
    private Timestamp time;
    private String merchantName;
    private int cost;

    public int getDebitCardTransactionId() {
        return this.debitCardTransactionId;
    }

    public void setDebitCardTransactionId(int debitCardTransactionId) {
        this.debitCardTransactionId = debitCardTransactionId;
    }

    public int getDebitCardId() {
        return this.debitCardId;
    }

    public void setDebitCardId(int debitCardId) {
        this.debitCardId = debitCardId;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\n[Debit Card Transaction ID = " + debitCardTransactionId +
                ", Debit Card ID = " + debitCardId +
                ", Time = " + time +
                ", Merchant Name = '" + merchantName + '\'' +
                ", Cost = " + cost + ']';
    }
}
