package datamodels;

public class CreditCard extends AccountIdInfo {
    private String cardNumber;
    private String expirationDate;
    private int cvc;
    private int block;

    public CreditCard(int id, int foreignIdAccount, String cardNumber, String expirationDate, int cvc, int block) {
        super(id, foreignIdAccount);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.block = block;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvc=" + cvc +
                ", block=" + block +
                ", foreignIdAccount=" + foreignIdAccount +
                ", id=" + id +
                '}';
    }
}
