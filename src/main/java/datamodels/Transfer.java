package datamodels;

import java.sql.Timestamp;

public class Transfer extends AccountIdInfo {
    private int idAccount2;
    private int oldBalanceAcc1;
    private int newBalanceAcc1;
    private int oldBalanceAcc2;
    private int newBalanceAcc2;
    private Timestamp time;

    public Transfer(int id, int idAccount1, int idAccount2, int oldBalanceAcc1, int newBalanceAcc1,
                    int oldBalanceAcc2, int newBalanceAcc2, Timestamp time) {
        super(id, idAccount1);
        this.idAccount2 = idAccount2;
        this.oldBalanceAcc1 = oldBalanceAcc1;
        this.newBalanceAcc1 = newBalanceAcc1;
        this.oldBalanceAcc2 = oldBalanceAcc2;
        this.newBalanceAcc2 = newBalanceAcc2;
        this.time = time;
    }

    public int getIdAccount2() {
        return this.idAccount2;
    }

    public void setIdAccount2(int idAccount2) {
        this.idAccount2 = idAccount2;
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
        return "Transfer{" +
                "idAccount2=" + idAccount2 +
                ", oldBalanceAcc1=" + oldBalanceAcc1 +
                ", newBalanceAcc1=" + newBalanceAcc1 +
                ", oldBalanceAcc2=" + oldBalanceAcc2 +
                ", newBalanceAcc2=" + newBalanceAcc2 +
                ", time=" + time +
                ", foreignIdAccount=" + foreignIdAccount +
                ", id=" + id +
                '}';
    }
}
