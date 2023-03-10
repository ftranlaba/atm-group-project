package terminallayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.util.exceptions.DAOException;
import datamodels.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IService;
import service.JDBCService;
import terminallayer.exceptions.InvalidNumber;
import terminallayer.exceptions.TooManyAttempts;
import terminallayer.util.AccountType;

import java.io.File;
import java.io.IOException;
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
    private static final IService service = JDBCService.getInstance();

    public static void main(String[] args) throws ExecutionException, InterruptedException, InvalidNumber, SQLException, TooManyAttempts {
        infiniteloop:
        while (true) {
            LOGGER.info("ATM Terminal \n");
            LOGGER.info("1) User");
            LOGGER.info("2) Admin");
            LOGGER.info("3) Create Account");
            LOGGER.info("4) Exit");
            switch (scan.nextInt()) {
                case 1:
                    Account a = authUser(16);
                    userUI(a);
                    break;
                case 2:
                    authUser(16);
                    adminUI();
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    LOGGER.info("Have A Good Rest of Your Day!");
                    scan.close();
                    break infiniteloop;
                default:
                    throw new InvalidNumber("Invalid Option");
            }
        }
    }

    public static void userUI(Account a) throws TooManyAttempts {
        infiniteloop:
        while (true) {
            LOGGER.info("1) Check Account ");
            LOGGER.info("2) Deposit Money");
            LOGGER.info("3) Transfer Money");
            LOGGER.info("4) Withdraw money");
            LOGGER.info("5) Block Card");
            LOGGER.info("6) Exit to Main Menu");
            Card c = service.getByIdCard(a.getId());
            User u = service.getByIdUser(a.getId());
            switch (scan.nextInt()) {
                case 1:
                    LOGGER.info("account ");
                    LOGGER.info(printAccount(4, u, a, c));
                    break;
                case 2:
                    String message = "How Much money would you like to deposit: ";
                    BigDecimal deposit = transferValidator(message);
                    service.makeDepositAccount(a, deposit);
                    break;
                case 3:
                    try {
                        makeTransfer(a);
                    } catch (DAOException e) {
                        LOGGER.error(e.getMessage());
                    } catch (TooManyAttempts | SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    String m = "Amount to Withdraw";
                    BigDecimal withdraw = transferValidator(m);
                    service.makeWithdrawalAccount(a, withdraw);
                    break;
                case 5:
                    LOGGER.info("Card Blocked");
                    service.toggleBlockStatus(c);
                    uesrUIBlocked(a, u, a1, c);
                    break infiniteloop;
                case 6:
                    break infiniteloop;
                default:
                    LOGGER.error("Invalid Option");
                    userUI(a);
            }
        }
    }

    public static void uesrUIBlocked(Account a, User u, Account a1, Card c){
        infiniteloop:
        while (true) {
            LOGGER.info("1) Check Account ");
            LOGGER.info("2) Exit to Main Menu");
            switch (scan.nextInt()) {
                case 1:
                    LOGGER.info("account ");
                    LOGGER.info(printAccount(4, u, a1, c));
                    break;
                case 2:
                    break infiniteloop;
            }
        }
    }

    public static void adminUI() {
        infiniteloop:
        while (true) {
            LOGGER.info("1) See Account");
            LOGGER.info("2) See All Accounts");
            LOGGER.info("3) Block Card");
            LOGGER.info("4) Unblock Card");
            LOGGER.info("5) Create Backup");
            LOGGER.info("6) Exit to Main Menu");
            switch (scan.nextInt()) {
                case 1:
                    LOGGER.info("Enter account id: ");
                    int id = scan.nextInt();
                    User u = service.getByIdUser(id);
                    Account a = service.getByIdAccount(id);
                    Card c = service.getByIdCard(id);
                    LOGGER.info(printAccount(2, u, a, c));
                    break;
                case 2:
                    List<Account> accountList = service.getAllAccounts();
                    for (Account a1 : accountList) {
                        User u1 = service.getByIdUser(a1.getId());
                        Card c1 = service.getByIdCard(a1.getId());
                        LOGGER.info(printAccount(3, u1, a1, c1));
                    }
                    break;
                case 3:
                    LOGGER.info("Enter Account Id of Card to Block: ");
                    int blockId = scan.nextInt();
                    service.toggleBlockStatus(service.getByIdCard(blockId));
                    LOGGER.info("Card Blocked");
                    break;
                case 4:
                    LOGGER.info("Enter Account Id of Card to Unblock: ");
                    int unBlockId = scan.nextInt();
                    service.toggleBlockStatus(service.getByIdCard(unBlockId));
                    LOGGER.info("Card Un-Blocked");
                    break;
                case 5:
                    LOGGER.info("Create Backup");
                    createBackup();
                    break;
                case 6:
                    break infiniteloop;
                default:
                    LOGGER.error("Invalid Option");
                    adminUI();
            }
        }
    }

    public static void createAccount() throws ExecutionException, InterruptedException, InvalidNumber, SQLException, TooManyAttempts {
        User u = createUser();
        service.createUser(u);
        service.createAccount(u, makeAccount(), new Card());
        User newUser = service.getByIdUser(u.getId());
        Account newAccount = service.getByIdAccount(u.getId());
        Card newCard = service.getByIdCard(u.getId());
        LOGGER.info(printAccount(1, newUser, newAccount, newCard));
    }

    public static Account makeAccount() throws InvalidNumber, TooManyAttempts {
        String message = ("Please Enter Four Digit pin");
        String output = numberValidator(message, 4);
        int pin = Integer.parseInt(output);
        LOGGER.info("Please enter account type: ");
        LOGGER.info("1) Credit");
        LOGGER.info("2) Debit");
        LOGGER.info("3) Savings");
        switch (scan.nextInt()) {
            case 1:
                return new Account(AccountType.CREDIT.getType(), pin);
            case 2:
                return new Account(AccountType.DEBIT.getType(), pin);
            case 3:
                return new Account(AccountType.SAVINGS.getType(), pin);
            default:
                LOGGER.error("Invalid Option");
                makeAccount();
        }
        return null;
    }

    public static User createUser() throws ExecutionException, InterruptedException {
        User u = new User();
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            String message = "Enter First Name: ";
            scan.nextLine();
            String firstName;
            try {
                firstName = notNullValidator(message);
            } catch (TooManyAttempts e) {
                throw new RuntimeException(e);
            }
            u.setFirstName(firstName);
        }).thenRunAsync(() -> {
            String message = "Enter Last Name: ";
            String lastName;
            try {
                lastName = notNullValidator(message);
            } catch (TooManyAttempts e) {
                throw new RuntimeException(e);
            }
            u.setLastName(lastName);
        }).thenRunAsync(() -> {
            String message = "Enter Address";
            String address;
            try {
                address = notNullValidator(message);
            } catch (TooManyAttempts e) {
                throw new RuntimeException(e);
            }
            u.setAddress(address);
        }).thenRunAsync(() -> {
            String message = "Enter Phone Number: ";
            String phoneNumber;
            try {
                phoneNumber = notNullValidator(message);
            } catch (TooManyAttempts e) {
                throw new RuntimeException(e);
            }
            u.setPhoneNumber(phoneNumber);
        });
        c.get();
        return u;
    }

    public static void createBackup(){
        List<Account> accountList = service.getAllAccounts();
        List<AccountAccess> accountAccessList = service.getAllAccountAccessHistory();
        List<Card> cardList = service.getAllCards();
        List<DepositWithdraw> depositWithdrawList = service.getAllDepositWithdrawHistory();
        List<Transaction> transactionList = service.getAllTransactions();
        List<Transfer> transferList = service.getAllTransfers();
        List<User> userList = service.getAllUsers();

        ATM atm = new ATM(1, "atm - atm", accountList, accountAccessList, cardList, depositWithdrawList,
                transactionList, transferList, userList);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            LOGGER.info("Creating backup in JSON file");

            objectMapper.writeValue(new File(System.getProperty("user.dir") + "/src/main/resources/atm_output.json"),
                    atm);

            LOGGER.info("Backup in JSON file has been created");
        } catch (IOException e) {
            LOGGER.error(e);
        }

        ATM atmFromFile;

        try {
            atmFromFile = objectMapper.readValue(new File(System.getProperty("user.dir") +
                    "/src/main/resources/atm_output.json"), ATM.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("Jackson from file output");
        LOGGER.info(atmFromFile);

    }
}
