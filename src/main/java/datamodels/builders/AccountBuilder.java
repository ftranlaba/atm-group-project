package datamodels.builders;

import datamodels.Account;
import datamodels.TypeInfo;

import java.math.BigDecimal;

public class AccountBuilder extends TypeInfo {
    private BigDecimal balance;
    private int pin;

    public AccountBuilder(int id, int idForeignKey, String type) {
        super(id, idForeignKey, type);
    }

    public AccountBuilder setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public AccountBuilder setPin(int pin) {
        this.pin = pin;
        return this;
    }

    public Account build() {
        Account account = new Account();
        account.setId(getId());
        account.setIdForeignKey(getIdForeignKey());
        account.setType(getType());
        account.setBalance(balance);
        account.setPin(pin);
        return account;
    }

}
