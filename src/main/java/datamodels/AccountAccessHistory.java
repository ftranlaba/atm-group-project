package datamodels;

import java.sql.Timestamp;

public class AccountAccessHistory {
    private int accountAccessHistoryId;
    private int accountId;
    private Timestamp time;
    private String macAddress;

    public int getAccountAccessHistoryId() {
        return this.accountAccessHistoryId;
    }

    public void setAccountAccessHistoryId(int accountAccessHistoryId) {
        this.accountAccessHistoryId = accountAccessHistoryId;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "\n[Account Access History ID = " + accountAccessHistoryId +
                ", Account ID = " + accountId +
                ", Time = " + time +
                ", Mac Address = '" + macAddress + '\'' + ']';
    }
}
