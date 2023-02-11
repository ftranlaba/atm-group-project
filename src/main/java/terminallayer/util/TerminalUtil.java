package terminallayer.util;

import dao.concretedaos.AccountsDAO;
import dao.concretedaos.UsersDAO;
import datamodels.Account;
import datamodels.Card;
import datamodels.Transfer;
import datamodels.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terminallayer.exceptions.InvalidTypeException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TerminalUtil {

    private static final Logger LOGGER = LogManager.getLogger("TESTLOGGER");
    private static final Scanner scan = new Scanner(System.in);

    public final static String cardNumGen() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt((int) Math.pow(10, 10)));
    }

    public final static int cvcGen() {
        Random rand = new Random();
        return rand.nextInt((int) Math.pow(4, 4));
    }

    public final static String date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/YY");
        return formatter.format(LocalDate.now().plusYears(5));

    }

    public final static Card createCard(int id, String type) throws SQLException {
        return new Card(id, type, cardNumGen(), date(), cvcGen(), false);
    }

    public final static Account authUser() throws InvalidTypeException, ExecutionException, InterruptedException, SQLException {
        AccountsDAO accountsDAO = new AccountsDAO();
        List paramList = new ArrayList<>();
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            LOGGER.info("Enter Account Id: ");
//            scan.nextLine();
            int id = scan.nextInt();
            paramList.add(id);});
//        }).thenRunAsync(() -> {
//            LOGGER.info("Enter Pin: ");
//            int pin = scan.nextInt();
//            paramList.add(pin);
//        }).thenRunAsync(() -> {
//          LOGGER.info("Please Enter Card Number: ");
//          scan.nextLine();
//          String cardNum = scan.nextLine();
//          paramList.add(cardNum);
//        }).thenRunAsync(() ->{
//            LOGGER.info("Please Enter Expiration Date: ");
//            String expirationDate = scan.nextLine();
//            paramList.add(expirationDate);
//        }).thenRunAsync(() -> {
//            LOGGER.info("Please Enter CVC: ");
//            int cvc = scan.nextInt();
//            paramList.add(cvc);
//        });
        c.get();
        //get account from db
//        LOGGER.info(paramList);
        Account  account = accountsDAO.getById((Integer) paramList.get(0));
        if (account != null) {
            return account;
        }
        throw new InvalidTypeException("Invalid Account id");

    }

    public final static Transfer makeTransfer(Account a) throws InvalidTypeException {
        LOGGER.info("Amount For Transfer");
        BigDecimal transferAmount = scan.nextBigDecimal();
        BigDecimal initBalance = a.getBalance();
        BigDecimal newBalance = initBalance.subtract(transferAmount);
        LOGGER.info("Id for receiving account");
        int id2 = scan.nextInt();
        //get account by id
        Account account = new Account();
        BigDecimal oldBalance2 = account.getBalance();
        BigDecimal newBalance2 = oldBalance2.add(transferAmount);
        return new Transfer(a.getId(), new Timestamp(System.currentTimeMillis()), initBalance, newBalance, id2, oldBalance2, newBalance2);
    }

    public final static int userIdCounter() throws SQLException {
        UsersDAO usersDAO = new UsersDAO();
        List<User> userList = usersDAO.getAll();
        if (userList == null) {
            return 1;
        }
        return userList.size() + 1;
    }

    public final static int accountCounter() throws SQLException {
        AccountsDAO accountsDAO = new AccountsDAO();
        List<Account> accountList = accountsDAO.getAll();
        if (accountList == null) {
            return 1;
        }
        return accountList.size() + 1;
    }

}