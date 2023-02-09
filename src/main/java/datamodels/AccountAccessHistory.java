package datamodels;

import java.sql.Timestamp;

public class AccountAccessHistory extends IdInfo {
    private int idAccount;
    private Timestamp time;
    private String macAddress;

    public AccountAccessHistory(int id, int idAccount, Timestamp time, String macAddress) {
        super(id);
        this.idAccount = idAccount;
        this.time = time;
        this.macAddress = macAddress;
    }

    public int getIdAccount() {
        return this.idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
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
                "idAccount=" + idAccount +
                ", time=" + time +
                ", macAddress='" + macAddress + '\'' +
                ", id=" + id +
                '}';
    }
}
