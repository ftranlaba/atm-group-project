package datamodels;

public class Account {

    private int accountId;
    private int primaryAccountId;
    private int balance;
    private String type;

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPrimaryAccountId() {
        return this.primaryAccountId;
    }

    public void setPrimaryAccountId(int primaryAccountId) {
        this.primaryAccountId = primaryAccountId;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n[Account ID = " + accountId +
                ", Primary Account ID = " + primaryAccountId +
                ", Balance = " + balance +
                ", Type = '" + type + '\'' + ']';
    }
}
