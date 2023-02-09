package datamodels;

public class Card extends TypeInfo {
    private String cardNumber;
    private String expirationDate;
    private int cvc;
    private boolean block;

    public Card(int id, int idForeignKey, String type, String cardNumber, String expirationDate, int cvc,
                boolean block) {
        super(id, idForeignKey, type);
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

    public boolean isBlock() {
        return this.block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvc=" + cvc +
                ", block=" + block +
                ", type='" + type + '\'' +
                ", idForeignKey=" + idForeignKey +
                ", id=" + id +
                '}';
    }
}
