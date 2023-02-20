package terminallayer.util;

import dao.util.exceptions.DAOException;
import datamodels.Account;
import datamodels.Card;
import datamodels.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IService;
import service.JDBCService;
import terminallayer.exceptions.InvalidNumber;
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

    public final static Account authUser(int cardNumLenght) throws InvalidNumber, ExecutionException, InterruptedException, SQLException, TooManyAttempts {
        Card card = new Card();
        List paramList = new ArrayList(1);
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            String message = "Please Enter Card Number: ";
            String cardNum;
            try {
                cardNum = numberValidator(message, cardNumLenght);
                card.setCardNumber(cardNum);
            } catch (TooManyAttempts e) {
                throw new RuntimeException(e);
            }
        }).thenRunAsync(() -> {
            String message = "Enter Pin: ";
            int pin = 0;
            try {
                pin = Integer.parseInt(numberValidator(message, 4));
                paramList.add(pin);
            } catch (TooManyAttempts e) {
                throw new RuntimeException(e);
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
        throw new InvalidNumber("Invalid Account");
    }

    public final static void makeTransfer(Account a) throws DAOException, TooManyAttempts, SQLException {
        String message = "Amount For Transfer";
        BigDecimal transferAmount = transferValidator(message);
        String m = "Enter cardnumber for receiving to transfer";
        String cardNum = numberValidator(m, 16);
        Card card = service.getCardByCardNumber(cardNum);
        Account account = service.getByIdAccount(card.getId());
        service.makeTransfer(a, account, transferAmount);
    }

    public final static BigDecimal transferValidator(String s) throws TooManyAttempts {
        BigDecimal input;
        int i = 0;
        do {
            LOGGER.info(s);
            input = scan.nextBigDecimal();
            if(input.compareTo(BigDecimal.valueOf(0)) == 1){
                return input;
            } else if (i == 3) {
                throw new TooManyAttempts("Attempt Limit Exceeded");
            }else{
                LOGGER.error("Invalid entry, Number cannot be 0 or Negative");
            }
        }while(input.compareTo(BigDecimal.valueOf(0)) == 0 || input.compareTo(BigDecimal.valueOf(0)) == -1);
        return input;
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

    public final static String notNullValidator(String s) throws TooManyAttempts {
        String input;
        int i = 0;
        do {
            LOGGER.info(s);
            input = scan.nextLine();
            if (input != "") {
                return input;
            } else if (i == 3) {
                throw new TooManyAttempts("Attempt Limit Exceeded");
            } else {
                LOGGER.error("Field Cannot be empty");
                i++;
            }
        } while (input == "");
        return input;
    }

    public final static String printAccount(int option, User u, Account a, Card c) {
        switch (option) {
            case 1:
                return "\n" +
                        "        Account Created    " +
                        "\n" +
                        "    Firstname: " + u.getFirstName() + ", Lastname: " + u.getLastName() +
                        "\n" +
                        "    Address: " + u.getAddress() + ", Phone Number: " + u.getPhoneNumber() +
                        "\n" +
                        "    Account ID: " + a.getId() + ", Account Type: " + a.getType() +
                        "\n" +
                        "    Card Number: " + c.getCardNumber() + ", CVC: " + c.getCvc() +
                        "\n" +
                        "    Expiration Date: " + c.getExpirationDate() + ", Type " + c.getType() +
                        "\n";
            case 2:
                return "\n" +
                        "        Account: " + a.getId() + "    " +
                        "\n" +
                        "    Firstname: " + u.getFirstName() + ", Lastname: " + u.getLastName() +
                        "\n" +
                        "    Account Type: " + a.getType() + ", Balance: " + a.getBalance() +
                        "\n" +
                        "    Pin: " + a.getPin() +
                        "\n" +
                        "    Card Number: " + c.getCardNumber() + ", CVC: " + c.getCvc() +
                        "\n" +
                        "    Expiration Date: " + c.getExpirationDate() + ", Type " + c.getType() +
                        "\n" +
                        "    Blocked: " + c.isBlock();
            case 3:
                return "\n " +
                        "                       [" +
                        "firstName=" + u.getFirstName() +
                        ", lastName=" + u.getLastName() +
                        ", accountId=" + a.getId() + " balance=" + a.getBalance() +
                        ", pin=" + a.getPin() +
                        ", type=" + a.getType() +
                        ", cardNumber=" + c.getCardNumber() +
                        ", expirationDate=" + c.getExpirationDate() +
                        ", cvc=" + c.getCvc() +
                        ", block=" + c.isBlock() +
                        ", type=" + c.getType() +
                        "]";
            case 4:
                return "\n" +
                        "        Account: " + a.getId() + "    " +
                        "\n" +
                        "    Firstname: " + u.getFirstName() + ", Lastname: " + u.getLastName() +
                        "\n" +
                        "    Account Type: " + a.getType() + ", Balance: " + a.getBalance() +
                        "\n" +
                        "    Card Number: " + c.getCardNumber() + ", CVC: " + c.getCvc() +
                        "\n" +
                        "    Expiration Date: " + c.getExpirationDate() + ", Type " + c.getType() +
                        "\n" +
                        "    Blocked: " + c.isBlock();
        }
        return null;
    }

}