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
        printMenu();
        switch (scan.nextInt()) {
            case 1:
                createAccount();
                break;
            case 2:
                userUI();
                break;
            case 3:
                adminUI();
                break;
            default:
                throw new InvalidTypeException("Invalid Option");
        }
        scan.close();
    }


    public static void createAccount() throws ExecutionException, InterruptedException, InvalidTypeException {
        User u = createUser();
        makeAccount(1);

    }

    public static void userUI() {
        LOGGER.info("login user");
    }

    public static void adminUI() {
        LOGGER.info("login admin");
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
        };

    }

    public static User createUser() throws ExecutionException, InterruptedException {
        User u = new User();
        // add user setter methods
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

    public static void printMenu() {
        LOGGER.info("1) Create Account");
        LOGGER.info("2) Login User");
        LOGGER.info("3) Login Admin");
    }

}
