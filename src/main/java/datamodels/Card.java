package datamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Card extends TypeInfo {
    @JsonProperty
    private String cardNumber;
    @JsonProperty
    private String expirationDate;
    @JsonProperty
    private int cvc;
    @JsonProperty
    private boolean block;

    public Card(int id, int idForeignKey, String type, String cardNumber, String expirationDate, int cvc,
                boolean block) {
        super(id, idForeignKey, type);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.block = block;
    }

    public Card(int idForeignKey, String type, String cardNumber, String expirationDate, int cvc, boolean block) {
        super(idForeignKey, type);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.block = block;
    }

    public Card() {
        super();
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
                ", type='" + this.getType() + '\'' +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
