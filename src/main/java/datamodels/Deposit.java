package datamodels;

import java.sql.Timestamp;

public class Deposit {
    private int depositId;
    private int accountId;
    private Timestamp time;
    private int oldBalance;
    private int newBalance;

    public int getDepositId() {
        return this.depositId;
    }

    public void setDepositId(int depositId) {
        this.depositId = depositId;
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
        return "\n[Deposit ID = " + depositId +
                ", Account ID = " + accountId +
                ", Time = " + time +
                ", Old Balance = " + oldBalance +
                ", New Balance = " + newBalance + ']';
    }
}
