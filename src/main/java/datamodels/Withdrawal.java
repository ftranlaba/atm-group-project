package datamodels;

import java.sql.Timestamp;

public class Withdrawal {
    private int withdrawalId;
    private int accountId;
    private Timestamp time;
    private int oldBalance;
    private int newBalance;

    public int getWithdrawalId() {
        return this.withdrawalId;
    }

    public void setWithdrawalId(int withdrawalId) {
        this.withdrawalId = withdrawalId;
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
        return "\n[Withdrawal ID = " + withdrawalId +
                ", Account ID = " + accountId +
                ", Time = " + time +
                ", Old Balance = " + oldBalance +
                ", New Balance = " + newBalance + ']';
    }
}
