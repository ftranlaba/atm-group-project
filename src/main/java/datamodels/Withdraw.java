package datamodels;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Withdraw extends IdInfo {
    private int idAccount;
    private Timestamp time;
    private int oldBalance;
    private int newBalance;

    public Withdraw(int id, int idAccount, int oldBalance, int newBalance) {
        super(id);
        this.idAccount = idAccount;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
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

    public int getOldBalance() {
        return this.oldBalance;
    }

    public void setOldBalance(int oldBalance) {
        this.oldBalance = oldBalance;
    }

    public int getNewBalance() {
        return this.newBalance;
    }

    public void setNewBalance(int newBalance) {
        this.newBalance = newBalance;
    }

    @Override
    public String toString() {
        return "Withdraw{" +
                "idAccount=" + idAccount +
                ", time=" + time +
                ", oldBalance=" + oldBalance +
                ", newBalance=" + newBalance +
                ", id=" + id +
                '}';
    }
}
