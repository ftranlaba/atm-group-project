package datamodels;

import java.sql.Timestamp;

public class AccountAccess extends TimeInfo {
    private String macAddress;

    public AccountAccess(int id, int idForeignKey, Timestamp time, String macAddress) {
        super(id, idForeignKey, time);
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "AccountAccess{" +
                "macAddress='" + macAddress + '\'' +
                ", time=" + this.getTime() +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
