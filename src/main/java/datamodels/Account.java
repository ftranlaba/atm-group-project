package datamodels;

public class Account extends IdInfo {
    private int balance;
    private int idPrimaryAccount;
    private String type;

    public Account(int id, int idPrimaryAccount, int balance, String type) {
        super(id);
        this.idPrimaryAccount = idPrimaryAccount;
        this.balance = balance;
        this.type = type;
    }

    public int getIdPrimaryAccount() {
        return this.idPrimaryAccount;
    }

    public void setIdPrimaryAccount(int idPrimaryAccount) {
        this.idPrimaryAccount = idPrimaryAccount;
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
        return "AccountIdInfo{" +
                "balance=" + balance +
                ", idPrimaryAccount=" + idPrimaryAccount +
                ", type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
