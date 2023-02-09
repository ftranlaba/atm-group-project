package datamodels;

public class CreditCard {
    private int creditCardId;
    private int primaryAccountId;
    private String creditCardNumber;
    private String expirationDate;
    private int cvc;
    private int block;

    public int getCreditCardId() {
        return this.creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public int getPrimaryAccountId() {
        return this.primaryAccountId;
    }

    public void setPrimaryAccountId(int primaryAccountId) {
        this.primaryAccountId = primaryAccountId;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
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
        return "\n[Credit Card ID = " + creditCardId +
                ", Primary Account ID = " + primaryAccountId +
                ", Credit Card Number = '" + creditCardNumber + '\'' +
                ", Expiration Date = '" + expirationDate + '\'' +
                ", CVC = " + cvc +
                ", Block = " + block + ']';
    }
}
