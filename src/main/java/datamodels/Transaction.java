package datamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction extends TimeInfo {
    @JsonProperty
    private String merchantName;
    @JsonProperty
    private BigDecimal cost;

    public Transaction(int id, int idForeignKey, Timestamp time, String merchantName, BigDecimal cost) {
        super(id, idForeignKey, time);
        this.merchantName = merchantName;
        this.cost = cost;
    }

    public Transaction() {
        super();
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "merchantName='" + merchantName + '\'' +
                ", cost=" + cost +
                ", time=" + this.getTime() +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
