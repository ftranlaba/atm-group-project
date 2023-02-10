package datamodels;

import java.math.BigDecimal;

public class Account extends TypeInfo {
    private BigDecimal balance;

    public Account(int id, int idForeignKey, String type, BigDecimal balance) {
        super(id, idForeignKey, type);
        this.balance = balance;
    }

    public Account(int id, int idForeignKey, String type) {
        super(id, idForeignKey, type);
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", type='" + this.getType() + '\'' +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
