package datamodels;

public class DebitCard extends IdInfo {
    private int idAccount;
    private String number;
    private String expirationDate;
    private int cvc;
    private int block;

    public DebitCard(int id, int idAccount, String number, String expirationDate, int cvc, int block) {
        super(id);
        this.idAccount = idAccount;
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.block = block;
    }
    public int getIdAccount() {
        return this.idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvc() {
        return this.cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public int getBlock() {
        return this.block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "idAccount=" + idAccount +
                ", number='" + number + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvc=" + cvc +
                ", block=" + block +
                ", id=" + id +
                '}';
    }
}
