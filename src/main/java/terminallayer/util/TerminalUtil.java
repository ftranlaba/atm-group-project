package terminallayer.util;

import dao.concretedaos.AccountsDAO;
import dao.util.exceptions.DAOException;
import datamodels.Account;
import datamodels.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terminallayer.exceptions.InvalidTypeException;

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

    public final static Account authUser() throws InvalidTypeException, ExecutionException, InterruptedException, SQLException {
        AccountsDAO accountsDAO = new AccountsDAO();
        Card card = new Card();
        List paramList = new ArrayList(1);
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            LOGGER.info("Enter Pin: ");
            int pin = scan.nextInt();
            paramList.add(pin);
        }).thenRunAsync(() -> {
            LOGGER.info("Please Enter Card Number: ");
            scan.nextLine();
            card.setCardNumber(scan.nextLine());
        }).thenRunAsync(() -> {
            LOGGER.info("Please Enter Expiration Date: ");
            String expirationDate = scan.nextLine();
            if (expirationDate.matches("[0-9][0-9]-[0-9][0-9]")) {
                card.setExpirationDate(expirationDate);
            } else {
                try {
                    throw new InvalidTypeException("Invalid Format");
                } catch (InvalidTypeException e) {
                    LOGGER.error(e);
                }
            }
        }).thenRunAsync(() -> {
            LOGGER.info("Please Enter CVC: ");
            String cvc = scan.nextLine();
            if (cvc.matches("[0-9]{3}")) {
                card.setCvc(Integer.parseInt(cvc));
            } else {
                try {
                    throw new InvalidTypeException("invalid format");
                } catch (InvalidTypeException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });
        c.get();
        Account account = accountsDAO.getAccount(card, (Integer) paramList.get(0));
        if (account != null) {
            return account;
        }
        throw new InvalidTypeException("Invalid Account");
    }

    public final static void makeTransfer(Account a) throws SQLException, DAOException {
        AccountsDAO accountsDAO = new AccountsDAO();
        LOGGER.info("Amount For Transfer");
        BigDecimal transferAmount = scan.nextBigDecimal();
        LOGGER.info("Id for receiving account");
        int id2 = scan.nextInt();
        Account account = accountsDAO.getById(id2);
        accountsDAO.makeTransfer(a, account, transferAmount);
    }

}