package terminallayer.util;

public enum AccountType {
    CREDIT("Credit"),
    DEBIT("Debit"),
    SAVINGS("Savings");

    private String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
