package datamodels;

import java.sql.Timestamp;

public class AccountAccessHistory extends AccountIdInfo {
    private Timestamp time;
    private String macAddress;

    public AccountAccessHistory(int id, int foreignIdAccount, Timestamp time, String macAddress) {
        super(id, foreignIdAccount);
        this.time = time;
        this.macAddress = macAddress;
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
        return "AccountAccessHistory{" +
                "time=" + time +
                ", macAddress='" + macAddress + '\'' +
                ", foreignIdAccount=" + foreignIdAccount +
                ", id=" + id +
                '}';
    }
}
