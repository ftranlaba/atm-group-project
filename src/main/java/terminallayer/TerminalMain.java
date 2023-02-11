package terminallayer;

import dao.concretedaos.AccountsDAO;
import dao.concretedaos.CardsDAO;
import dao.concretedaos.TransfersDAO;
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
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static terminallayer.util.TerminalUtil.*;


public class TerminalMain {

    private static final Logger LOGGER = LogManager.getLogger("TESTLOGGER");
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws ExecutionException, InterruptedException, InvalidTypeException, SQLException {
        while (true) {
            LOGGER.info("ATM Terminal \n");
            LOGGER.info("1) User");
            LOGGER.info("2) Admin");
            LOGGER.info("3) Create Account");
            LOGGER.info("4) Exit");
            switch (scan.nextInt()) {
                case 1:
                    Account a = authUser();
                    userUI(a);
                    break;
                case 2:
                    authUser();
                    adminUI();
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    LOGGER.info("Have A Good Rest of Your Day");
                    scan.close();
                    break;
                default:
                    throw new InvalidTypeException("Invalid Option");
            }
        }
    }

    public static void userUI(Account a) throws ExecutionException, InterruptedException, InvalidTypeException, SQLException {
        AccountsDAO accountsDAO = new AccountsDAO();
        CardsDAO cardsDAO = new CardsDAO();
        TransfersDAO transfersDAO = new TransfersDAO();
        LOGGER.info("1) Check Account ");
        LOGGER.info("2) Deposit Money");
        LOGGER.info("3) Transfer Money");
        LOGGER.info("4) Report Card Stolen");
        Card c = cardsDAO.getById(a.getId());
        LOGGER.info(a);
        switch (scan.nextInt()) {
            case 1:
                LOGGER.info("account ");
                LOGGER.info(a);
                break;
            case 2:
                // test to make sure it works
                LOGGER.info("How Much money would you like to deposit: ");
                BigDecimal deposit = scan.nextBigDecimal();
                LOGGER.info(deposit);
                a.setBalance(deposit);
                accountsDAO.update(a);
                break;
            case 3:
                //finish transfer implementation
                Transfer t = makeTransfer(a);
                transfersDAO.create(t);
                LOGGER.info(t);
                break;
            case 4:
                LOGGER.info("Card Blocked");
                c.setBlock(false);
                cardsDAO.update(c);
                break;
            default:
                throw new InvalidTypeException("Invalid Option");
        }
    }

    public static void adminUI() throws InvalidTypeException {
        LOGGER.info("1) See Account");
        LOGGER.info("2) See Accounts");
        LOGGER.info("3) Block Card");
        LOGGER.info("4) Unblock Card");

        switch (scan.nextInt()) {
            case 1:
                LOGGER.info("Enter account id: ");
                int id = scan.nextInt();
                LOGGER.info(id);
                break;
            case 2:
                LOGGER.info("Accounts info");
                break;
            case 3:
                LOGGER.info("Enter Account Id of Card to Block: ");
                int blockId = scan.nextInt();
                LOGGER.info(blockId);
                break;
            case 4:
                LOGGER.info("Enter Account Id of Card to Unblock: ");
                int unBlockId = scan.nextInt();
                LOGGER.info(unBlockId);
                break;
            default:
                throw new InvalidTypeException("Invalid Option");
        }

    }

    public static void createAccount() throws ExecutionException, InterruptedException, InvalidTypeException, SQLException {
        CardsDAO cardsDAO = new CardsDAO();
        AccountsDAO accountsDAO = new AccountsDAO();
        User u = createUser();
        LOGGER.info(u);
        Account a = makeAccount(u.getId());
        LOGGER.info(a);
        accountsDAO.create(a);
        Card c = createCard(a.getId(), a.getType());
        cardsDAO.create(c);
        LOGGER.info(c);

    }

    public static Account makeAccount(int id) throws InvalidTypeException, SQLException {
        LOGGER.info("Please Enter Four Digit pin");
        int pin = scan.nextInt();
        LOGGER.info("Please enter account type: ");
        LOGGER.info("1) Credit");
        LOGGER.info("2) Debit");
        LOGGER.info("3) Savings");
        int type = scan.nextInt();
        switch (type) {
            case 1:
                return new Account(accountCounter(), id, "Credit", BigDecimal.valueOf(0), pin);
            case 2:
                return new Account(accountCounter(), id, "Debit", BigDecimal.valueOf(0), pin);
            case 3:
                return new Account(accountCounter(), id, "Savings", BigDecimal.valueOf(0), pin);
            default:
                throw new InvalidTypeException("Invalid type");
        }
    }

    public static User createUser() throws ExecutionException, InterruptedException, SQLException {
        User u = new User();
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            LOGGER.info("Enter First Name: ");
            scan.nextLine();
            String firstName = scan.nextLine();
            u.setFirstName(firstName);
        }).thenRunAsync(() -> {
            LOGGER.info("Enter Last Name: ");
            String lastName = scan.nextLine();
            u.setLastName(lastName);
        }).thenRunAsync(() -> {
            LOGGER.info("Enter Address");
            String address = scan.nextLine();
            u.setAddress(address);
        }).thenRunAsync(() -> {
            LOGGER.info("Enter Phone Number: ");
            String phoneNumber = scan.nextLine();
            u.setPhoneNumber(phoneNumber);
        });
        u.setId(userIdCounter());
        c.get();
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.create(u);
        return u;
    }
}
