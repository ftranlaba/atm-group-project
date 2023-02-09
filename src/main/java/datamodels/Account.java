package datamodels;

public class Account extends AccountIdInfo {
    private int balance;
    private String type;

    public Account(int id, int idPrimaryAccount, int balance, String type) {
        super(id, idPrimaryAccount);
        this.balance = balance;
        this.type = type;
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
        return "Account{" +
                "balance=" + balance +
                ", type='" + type + '\'' +
                ", foreignIdAccount=" + foreignIdAccount +
                ", id=" + id +
                '}';
    }
}
