package datamodels;

public class DebitCard {
    private int debitCardId;
    private int accountId;
    private String debitCardNumber;
    private String expirationDate;
    private int cvc;
    private int block;

    public int getDebitCardId() {
        return this.debitCardId;
    }

    public void setDebitCardId(int debitCardId) {
        this.debitCardId = debitCardId;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDebitCardNumber() {
        return this.debitCardNumber;
    }

    public void setDebitCardNumber(String debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
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
        return "\n[Debit Card ID = " + debitCardId +
                ", Account ID = " + accountId +
                ", Debit Card Number = '" + debitCardNumber + '\'' +
                ", Expiration Date = '" + expirationDate + '\'' +
                ", CVC = " + cvc +
                ", Block = " + block + ']';
    }
}
