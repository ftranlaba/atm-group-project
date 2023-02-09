package terminallayer;

import datamodels.PrimaryAccount;
import datamodels.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terminallayer.exceptions.InvalidTypeException;
import terminallayer.util.CardUtil;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static terminallayer.util.CardUtil.*;


public class TerminalMain {
    private static final Logger LOGGER = LogManager.getLogger("TESTLOGGER");
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws ExecutionException, InterruptedException, InvalidTypeException {
        LOGGER.info("ATM Terminal");
        LOGGER.info("1) User");
        LOGGER.info("2) Admin");
        LOGGER.info("3) Create Account");
        switch (scan.nextInt()) {
            case 1:
                userUI();
                break;
            case 2:
                adminUI();
                break;
            case 3:
                createAccount();
                break;
            default:
                throw new InvalidTypeException("Invalid Option");
        }
        scan.close();
    }

    public static void userUI() throws ExecutionException, InterruptedException {
        LOGGER.info("login user");
        LOGGER.info("1) Check account balances");
        LOGGER.info("2) Deposit Money");
        LOGGER.info("3) Transfer Money");
        LOGGER.info("4) Change Account Pin");
        LOGGER.info("5) Report Card Stolen");
        switch (scan.nextInt()) {
            case 1:
                LOGGER.info("account balance");
                break;
            case 2:
                LOGGER.info("How Much money would you like to deposit: ");
                int deposit = scan.nextInt();
                LOGGER.info(deposit);
                // set deposit to account
                break;
            case 3:
                //finish transfer implementation
                transfer();
                break;
            case 4:
                LOGGER.info("Change Account Pin");
                int pin = scan.nextInt();
                LOGGER.info(pin);
                //add setter to change pin
                break;
            case 5:
                LOGGER.info("Card Blocked");
                //set card to blocked
                break;
        }
    }

    public static void adminUI() {
        LOGGER.info("admin");
        LOGGER.info("1) See Account");
        LOGGER.info("2) See Accounts");
        LOGGER.info("3) Block Card");
        LOGGER.info("4) Unblock Card");

    }

    public static void createAccount() throws ExecutionException, InterruptedException, InvalidTypeException {
        User u = createUser();
        makeAccount(1);
    }

    public static void makeAccount(int id) throws InvalidTypeException {
        LOGGER.info("Enter Four digit pin");
        int pin = scan.nextInt();
        LOGGER.info(pin);
        LOGGER.info("Please enter account type: ");
        LOGGER.info("1) Credit");
        LOGGER.info("2) Debit");
        LOGGER.info("3) Savings");
        switch (scan.nextInt()) {
            case 1:
//                CreditCard cc = new CreditCard(cardNumGen, date(), cvcGen());
//                PrimaryAccount pa = new PrimaryAccount(id, pin, "Credit");
                break;
            case 2:
//                DebitCard db = new DebitCard(cardNumGem(), date(), cvcGen());
//                PrimaryAccount pa = new PrimaryAccount(id, pin, "Debit");
                break;
            case 3:
//                PrimaryAccount pa = new PrimaryAccount(id, pin, "Savings");
                break;
            default:
                throw new InvalidTypeException("Invalid type");
        }
    }

    public static User createUser() throws ExecutionException, InterruptedException {
        User u = new User();
        // add User setter methods
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            LOGGER.info("Enter First Name: ");
            scan.nextLine();
            String firstName = scan.nextLine();
            LOGGER.info(firstName);
        }).thenRunAsync(() -> {
            LOGGER.info("Enter Last Name: ");
            String lastName = scan.nextLine();
            LOGGER.info(lastName);
        }).thenRunAsync(() -> {
            LOGGER.info("Enter Address");
            String address = scan.nextLine();
            LOGGER.info(address);
        }).thenRunAsync(() -> {
            LOGGER.info("Enter Phone Number: ");
            String phoneNumber = scan.nextLine();
            LOGGER.info(phoneNumber);
        });
        c.get();
        return u;
    }

    public static void transfer() throws ExecutionException, InterruptedException {
        //finish implementation
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            LOGGER.info("From: ");
            scan.nextLine();
            String from = scan.nextLine();
            LOGGER.info(from);
        }).thenRunAsync(() -> {
            LOGGER.info("Where: ");
            String where = scan.nextLine();
            LOGGER.info(where);
        }).thenRunAsync(() -> {
            LOGGER.info("Amount: ");
            int amount = scan.nextInt();
            LOGGER.info(amount);
        });
        c.get();
    }

}
