package datamodels;

import java.sql.Timestamp;

public class CreditCardTransaction {
    private int creditCardTransactionId;
    private int creditCardId;
    private Timestamp time;
    private String merchantName;
    private int cost;

    public int getCreditCardTransactionId() {
        return this.creditCardTransactionId;
    }

    public void setCreditCardTransactionId(int creditCardTransactionId) {
        this.creditCardTransactionId = creditCardTransactionId;
    }

    public int getCreditCardId() {
        return this.creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
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
        return "\n[Credit Card Transaction ID = " + creditCardTransactionId +
                ", Credit Card ID = " + creditCardId +
                ", Time = " + time +
                ", Merchant Name = '" + merchantName + '\'' +
                ", Cost = " + cost + ']';
    }
}
