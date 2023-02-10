package datamodels;

import java.math.BigDecimal;

public class Account extends TypeInfo {
    private BigDecimal balance;
    private int pin;

    public Account(int id, int idForeignKey, String type, BigDecimal balance, int pin) {
        super(id, idForeignKey, type);
        this.balance = balance;
        this.pin = pin;
    }

    public Account() {
        super();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", pin='" + pin + '\'' +
                ", type='" + this.getType() + '\'' +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }

    @java.lang.Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Account account = (Account) object;
        return pin == account.pin && balance.equals(account.balance);
    }

    @java.lang.Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), balance, pin);
    }
}
