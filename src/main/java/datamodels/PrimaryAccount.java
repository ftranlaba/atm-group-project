package datamodels;

public class PrimaryAccount {

    private int primaryAccountId;
    private int userId;
    private int pin;

    public int getPrimaryAccountId() {
        return this.primaryAccountId;
    }

    public void setPrimaryAccountId(int primaryAccountId) {
        this.primaryAccountId = primaryAccountId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "\n[Primary Account Id = " + primaryAccountId +
                ", User Id = " + userId +
                ", PIN = " + pin + ']';
    }
}
