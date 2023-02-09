package datamodels;

import java.sql.Timestamp;

public class CreditCardTransaction extends IdInfo {
    private Timestamp time;
    private int idCard;
    private String merchantName;
    private int cost;

    public CreditCardTransaction(int id, int idCard, Timestamp time, String merchantName, int cost) {
        super(id);
        this.idCard = idCard;
        this.time = time;
        this.merchantName = merchantName;
        this.cost = cost;
    }

    public int getIdCard() {
        return this.idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public CreditCardTransaction(int id) {
        super(id);
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
        return "CreditCardTransaction{" +
                "time=" + time +
                ", idCard=" + idCard +
                ", merchantName='" + merchantName + '\'' +
                ", cost=" + cost +
                ", id=" + id +
                '}';
    }
}
