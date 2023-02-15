package datamodels;

import java.util.List;

public class ATM {
    private int id;
    private String name;
    private List<Account> accountList;
    private List<AccountAccess> accountAccessList;
    private List<Card> cardList;
    private List<DepositWithdraw> depositWithdrawList;
    private List<Transaction> transactionList;
    private List<Transfer> transferList;
    private List<User> userList;

    public ATM(){

    }

    public ATM(int id, String name, List<Account> accountList, List<AccountAccess> accountAccessList, List<Card> cardList,
               List<DepositWithdraw> depositWithdrawList, List<Transaction> transactionList, List<Transfer> transferList,
               List<User> userList) {
        this.id = id;
        this.name = name;
        this.accountList = accountList;
        this.accountAccessList = accountAccessList;
        this.cardList = cardList;
        this.depositWithdrawList = depositWithdrawList;
        this.transactionList = transactionList;
        this.transferList = transferList;
        this.userList = userList;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccountList() {
        return this.accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<AccountAccess> getAccountAccessList() {
        return this.accountAccessList;
    }

    public void setAccountAccessList(List<AccountAccess> accountAccessList) {
        this.accountAccessList = accountAccessList;
    }

    public List<Card> getCardList() {
        return this.cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<DepositWithdraw> getDepositWithdrawList() {
        return this.depositWithdrawList;
    }

    public void setDepositWithdrawList(List<DepositWithdraw> depositWithdrawList) {
        this.depositWithdrawList = depositWithdrawList;
    }

    public List<Transaction> getTransactionList() {
        return this.transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transfer> getTransferList() {
        return this.transferList;
    }

    public void setTransferList(List<Transfer> transferList) {
        this.transferList = transferList;
    }

    public List<User> getUserList() {
        return this.userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ",\naccountList=" + accountList +
                ",\naccountAccessList=" + accountAccessList +
                ",\ncardList=" + cardList +
                ",\ndepositWithdrawList=" + depositWithdrawList +
                ",\ntransactionList=" + transactionList +
                ",\ntransferList=" + transferList +
                ",\nuserList=" + userList +
                '}';
    }
}
