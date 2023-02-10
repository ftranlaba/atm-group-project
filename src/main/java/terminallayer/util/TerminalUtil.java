package terminallayer.util;

import datamodels.Account;
import datamodels.Card;
import datamodels.Transfer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terminallayer.exceptions.InvalidTypeException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

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

    public final static Card createCard(int id, String type) {
        return new Card(id, type, cardNumGen(), date(), cvcGen(), false);
    }

    public final static Account authUser() throws InvalidTypeException {
        LOGGER.info("Enter Account Id");
        int id = scan.nextInt();
        LOGGER.info(id);
        //get account from db
        Account account = new Account();
        if (account != null) {
            return account;
        }
        throw new InvalidTypeException("Invalid Account id");

    }

    public final static Transfer makeTransfer(Account a) throws InvalidTypeException {
        LOGGER.info("Amount For Transfer");
        BigDecimal transferAmount = scan.nextBigDecimal();
//        BigDecimal initBalance = a.getBalance();
        BigDecimal initBalance = BigDecimal.valueOf(234500, 4);
        BigDecimal newBalance = initBalance.subtract(transferAmount);
        LOGGER.info("Id for receiving account");
        int id2 = scan.nextInt();
        //get account by id
        Account account = new Account();
//        BigDecimal oldBalance2 = account.getBalance();
        BigDecimal oldBalance2 = BigDecimal.valueOf(34567, 4);
        BigDecimal newBalance2 = oldBalance2.add(transferAmount);
        return  new Transfer(a.getId(), new Timestamp(System.currentTimeMillis()), initBalance, newBalance, id2, oldBalance2, newBalance2);
    }

}