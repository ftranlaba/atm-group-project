package datamodels;

public abstract class AccountIdInfo extends IdInfo {

    protected int foreignIdAccount;
    public AccountIdInfo(int id, int foreignIdAccount) {
        super(id);
        this.foreignIdAccount = foreignIdAccount;
    }

    public int getIdAccount() {
        return this.foreignIdAccount;
    }

    public void setIdAccount(int foreignIdAccount) {
        this.foreignIdAccount = foreignIdAccount;
    }
}
