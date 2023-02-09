package datamodels;

public class DebitCard extends AccountIdInfo {
    private String number;
    private String expirationDate;
    private int cvc;
    private int block;

    public DebitCard(int id, int foreignIdAccount, String number, String expirationDate, int cvc, int block) {
        super(id, foreignIdAccount);
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.block = block;
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
                "number='" + number + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvc=" + cvc +
                ", block=" + block +
                ", foreignIdAccount=" + foreignIdAccount +
                ", id=" + id +
                '}';
    }
}
