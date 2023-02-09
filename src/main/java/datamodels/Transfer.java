package datamodels;

import java.sql.Timestamp;

public class Transfer {
    private int transferId;
    private int account1Id;
    private int account2Id;
    private int oldBalanceAcc1;
    private int newBalanceAcc1;
    private int oldBalanceAcc2;
    private int newBalanceAcc2;
    private Timestamp time;

    public int getTransferId() {
        return this.transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getAccount1Id() {
        return this.account1Id;
    }

    public void setAccount1Id(int account1Id) {
        this.account1Id = account1Id;
    }

    public int getAccount2Id() {
        return this.account2Id;
    }

    public void setAccount2Id(int account2Id) {
        this.account2Id = account2Id;
    }

    public int getOldBalanceAcc1() {
        return this.oldBalanceAcc1;
    }

    public void setOldBalanceAcc1(int oldBalanceAcc1) {
        this.oldBalanceAcc1 = oldBalanceAcc1;
    }

    public int getNewBalanceAcc1() {
        return this.newBalanceAcc1;
    }

    public void setNewBalanceAcc1(int newBalanceAcc1) {
        this.newBalanceAcc1 = newBalanceAcc1;
    }

    public int getOldBalanceAcc2() {
        return this.oldBalanceAcc2;
    }

    public void setOldBalanceAcc2(int oldBalanceAcc2) {
        this.oldBalanceAcc2 = oldBalanceAcc2;
    }

    public int getNewBalanceAcc2() {
        return this.newBalanceAcc2;
    }

    public void setNewBalanceAcc2(int newBalanceAcc2) {
        this.newBalanceAcc2 = newBalanceAcc2;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "\n[Transfer ID = " + transferId +
                ", Account 1 ID = " + account1Id +
                ", Account 2 ID = " + account2Id +
                ", Account 1 Old Balance = " + oldBalanceAcc1 +
                ", Account 1 New Balance = " + newBalanceAcc1 +
                ", Account 2 Old Balance = " + oldBalanceAcc2 +
                ", Account 2 New Balance = " + newBalanceAcc2 +
                ", Time = " + time + ']';
    }
}
