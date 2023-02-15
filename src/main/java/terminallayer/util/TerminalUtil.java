package terminallayer.util;

import dao.concretedaos.AccountsDAO;
import dao.util.exceptions.DAOException;
import datamodels.Account;
import datamodels.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IService;
import service.JDBCService;
import terminallayer.exceptions.InvalidTypeException;
import terminallayer.exceptions.TooManyAttempts;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TerminalUtil {

    private static final Logger LOGGER = LogManager.getLogger("TESTLOGGER");
    private static final Scanner scan = new Scanner(System.in);
    private static final IService service = JDBCService.getInstance();

    public final static Account authUser() throws InvalidTypeException, ExecutionException, InterruptedException, SQLException {
        Card card = new Card();
        List paramList = new ArrayList(1);
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            String message = "Enter Pin: ";
            int pin = 0;
            try {
                pin = Integer.parseInt(numberValidator(message, 4));
                paramList.add(pin);
            } catch (TooManyAttempts e) {
                LOGGER.error(e);
            }

        }).thenRunAsync(() -> {
            String message = "Please Enter Card Number: ";
            String cardNum;
            try {
                cardNum = numberValidator(message, 16);
                card.setCardNumber(cardNum);
            } catch (TooManyAttempts e) {
                LOGGER.error(e);
            }
        }).thenRunAsync(() -> {
            String message = "Please Enter Expiration Date: ";
            String expirationDate;
            try {
                expirationDate = dateValidator(message);
                card.setExpirationDate(expirationDate);
            } catch (TooManyAttempts e) {
                throw new RuntimeException(e);
            }
        }).thenRunAsync(() -> {
            String message = "Please Enter CVC: ";
            int cvc;
            try {
                cvc = Integer.parseInt(numberValidator(message, 3));
                card.setCvc(cvc);
            } catch (TooManyAttempts e) {
                throw new RuntimeException(e);
            }
        });
        c.get();
        Account account = service.getAccount(card, (Integer) paramList.get(0));
        if (account != null) {
            return account;
        }
        throw new InvalidTypeException("Invalid Account");
    }

    public final static void makeTransfer(Account a) throws DAOException {
        AccountsDAO accountsDAO = new AccountsDAO();
        LOGGER.info("Amount For Transfer");
        BigDecimal transferAmount = scan.nextBigDecimal();
        LOGGER.info("Id for receiving account");
        int id2 = scan.nextInt();
        Account account = accountsDAO.getById(id2);
        accountsDAO.makeTransfer(a, account, transferAmount);
    }

    public final static String numberValidator(String s, int maxNum) throws TooManyAttempts {
        String input;
        int i = 0;
        do {
            LOGGER.info(s);
            input = scan.nextLine();
            if (input.matches("[0-9]{" + maxNum + "}")) {
                return input;
            } else if (i == 3) {
                throw new TooManyAttempts("Attempt Limit Exceeded");
            } else {
                LOGGER.error("Invalid number Pattern");
                i++;
            }
        } while (!input.matches("[0-9]{" + maxNum + "}"));
        return input;
    }

    public final static String dateValidator(String s) throws TooManyAttempts {
        String input;
        int i = 0;
        do {
            LOGGER.info(s);
            input = scan.nextLine();
            if (input.matches("[0-9]{2}-[0-9]{2}")) {
                return input;
            } else if (i == 3) {
                throw new TooManyAttempts("Attempt Limit Exceeded");
            } else {
                LOGGER.error("Invalid Date Pattern");
                i++;
            }
        } while (!input.matches("[0-9]{2}-[0-9]{2}"));
        return input;
    }

}