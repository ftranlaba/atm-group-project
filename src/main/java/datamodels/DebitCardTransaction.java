package datamodels;

import java.sql.Timestamp;

public class DebitCardTransaction extends IdInfo {
    private int idCard;
    private Timestamp time;
    private String merchantName;
    private int cost;

    public DebitCardTransaction(int id, int idCard, Timestamp time, String merchantName, int cost) {
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
        return "DebitCardTransaction{" +
                "idCard=" + idCard +
                ", time=" + time +
                ", merchantName='" + merchantName + '\'' +
                ", cost=" + cost +
                ", id=" + id +
                '}';
    }
}
