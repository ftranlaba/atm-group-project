package terminallayer;

import dao.concretedaos.AccountsDAO;
import dao.concretedaos.CardsDAO;
import dao.concretedaos.UsersDAO;
import dao.util.DBFactoryGenerator;
import dao.util.IDAOFactory;
import dao.util.enums.DBConnectionType;
import dao.util.exceptions.DAOException;
import datamodels.Account;
import datamodels.Card;
import datamodels.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IService;
import service.JDBCService;
import terminallayer.exceptions.InvalidTypeException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static terminallayer.util.TerminalUtil.authUser;
import static terminallayer.util.TerminalUtil.makeTransfer;


public class TerminalMain {

    private static final Logger LOGGER = LogManager.getLogger("TESTLOGGER");
    private static final Scanner scan = new Scanner(System.in);
    private static final IService service = JDBCService.getInstance();

    public static void main(String[] args) throws ExecutionException, InterruptedException, InvalidTypeException, SQLException, DAOException {
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
                    LOGGER.info("Have A Good Rest of Your Day!");
                    scan.close();
                    break;
                default:
                    throw new InvalidTypeException("Invalid Option");
            }
            break;
        }
    }

    public static void userUI(Account a) throws InvalidTypeException, SQLException, DAOException {
        LOGGER.info("1) Check Account ");
        LOGGER.info("2) Deposit Money");
        LOGGER.info("3) Transfer Money");
        LOGGER.info("4) Withdraw money");
        LOGGER.info("5) Report Card Stolen");
        Card c = service.getByIdCard(a.getId());
        switch (scan.nextInt()) {
            case 1:
                LOGGER.info("account ");
                LOGGER.info(a);
                break;
            case 2:
                LOGGER.info("How Much money would you like to deposit: ");
                BigDecimal deposit = scan.nextBigDecimal();
                service.makeDepositAccount(a, deposit);
                break;
            case 3:
                try {
                    makeTransfer(a);
                } catch (DAOException e) {
                    LOGGER.error(e.getMessage());
                }
                break;
            case 4:
                LOGGER.info("Amount to Withdraw");
                BigDecimal withdraw = scan.nextBigDecimal();
                service.makeWithdrawalAccount(a, withdraw);
                break;
            case 5:
                LOGGER.info("Card Blocked");
                service.toggleBlockStatus(c);
                break;
            default:
                throw new InvalidTypeException("Invalid Option");
        }
    }

    public static void adminUI() throws InvalidTypeException, SQLException {
        AccountsDAO accountsDAO = new AccountsDAO();
        CardsDAO cardsDAO = new CardsDAO();
        LOGGER.info("1) See Account");
        LOGGER.info("2) See Accounts");
        LOGGER.info("3) Block Card");
        LOGGER.info("4) Unblock Card");
        switch (scan.nextInt()) {
            case 1:
                LOGGER.info("Enter account id: ");
                int id = scan.nextInt();
                Account account = accountsDAO.getById(id);
                LOGGER.info(account);
                break;
            case 2:
                List<Account> accountList = accountsDAO.getAll();
                for (Account account1 : accountList) {
                    LOGGER.info(account1 + "\n");
                }
                break;
            case 3:
                LOGGER.info("Enter Account Id of Card to Block: ");
                int blockId = scan.nextInt();
                Card c = cardsDAO.getById(blockId);
                cardsDAO.toggleBlockStatus(c);
                LOGGER.info("Card Blocked");
                break;
            case 4:
                LOGGER.info("Enter Account Id of Card to Unblock: ");
                int unBlockId = scan.nextInt();
                Card c1 = cardsDAO.getById(unBlockId);
                cardsDAO.toggleBlockStatus(c1);
                LOGGER.info("Card UnBlocked");
                break;
            default:
                throw new InvalidTypeException("Invalid Option");
        }

    }

    public static void createAccount() throws ExecutionException, InterruptedException, InvalidTypeException, SQLException {
        UsersDAO usersDAO = new UsersDAO();
        CardsDAO cardsDAO = new CardsDAO();
        AccountsDAO accountsDAO = new AccountsDAO();
        User u = createUser();
        usersDAO.create(u);
        accountsDAO.createAccount(u, makeAccount(), new Card());
        LOGGER.info(usersDAO.getById(u.getId()));
        LOGGER.info(cardsDAO.getById(u.getId()));
        LOGGER.info(accountsDAO.getById(u.getId()));
    }

    public static Account makeAccount() throws InvalidTypeException {
        LOGGER.info("Please Enter Four Digit pin");
        int pin = scan.nextInt();
        LOGGER.info("Please enter account type: ");
        LOGGER.info("1) Credit");
        LOGGER.info("2) Debit");
        LOGGER.info("3) Savings");
        int type = scan.nextInt();
        if (!String.valueOf(pin).matches("[0-9]{4}")) {
            throw new InvalidTypeException("Invalid pin pattern");
        }
        switch (type) {
            case 1:
                return new Account("Credit", pin);
            case 2:
                return new Account("Debit", pin);
            case 3:
                return new Account("Savings", pin);
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
        c.get();
        return u;
    }
}
