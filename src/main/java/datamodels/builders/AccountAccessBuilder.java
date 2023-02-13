package datamodels.builders;

import datamodels.AccountAccess;
import datamodels.TimeInfo;

import java.sql.Timestamp;

public class AccountAccessBuilder extends TimeInfo {
    private String macAddress;

    public AccountAccessBuilder(int id, int idForeignKey, Timestamp time) {
        super(id, idForeignKey, time);
    }

    public AccountAccessBuilder setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public AccountAccess build() {
        AccountAccess accountAccess = new AccountAccess();
        accountAccess.setId(getId());
        accountAccess.setIdForeignKey(getIdForeignKey());
        accountAccess.setTime(getTime());
        accountAccess.setMacAddress(macAddress);
        return accountAccess;
    }
}
