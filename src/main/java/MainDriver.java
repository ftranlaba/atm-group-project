import com.fasterxml.jackson.databind.ObjectMapper;
import datamodels.*;
import datamodels.builders.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MainDriver {
    private static final Logger LOGGER = LogManager.getLogger("TESTLOGGER");

    public static void main(String[] args) {
        Account tomAccount = new AccountBuilder(1, 1, "Credit").setBalance(BigDecimal.valueOf(12.12))
                .setPin(1223).build();

        AccountAccess tomAccountAccess = new AccountAccessBuilder(1, 1,
                Timestamp.valueOf("2022-02-19 03:14:07")).setMacAddress("Apple").build();

        Card tomCard = new CardBuilder(1, 1, "Debit").setCardNumber("112341").setExpirationDate("12/25")
                .setCvc(123).setBlock(true).build();
        DepositWithdraw tomDeposit = new DepositWithdrawBuilder(1, 1, Timestamp.valueOf("2022-02-19 03:14:07"),
                BigDecimal.valueOf(20.50), BigDecimal.valueOf(12354.57)).setType("Checking").build();

        Transaction tomTransaction = new TransactionBuilder(1, 1, Timestamp.valueOf("2022-02-19 03:14:07"))
                .setMerchantName("Sam").setCost(BigDecimal.valueOf(12.50)).build();

        Transfer tomTransfer = new TransferBuilder(1, 1, Timestamp.valueOf("2022-02-19 03:14:07"),
                BigDecimal.valueOf(12354.57), BigDecimal.valueOf(12353.57)).setIdAccount2(2).
                setOldBalance2(BigDecimal.valueOf(1234254.57)).setNewBalance2(BigDecimal.valueOf(1234255.57)).build();

        User tom = new UserBuilder(1).setFirstName("Tom").setLastName("Jordan").setAddress("12 Main Street")
                .setPhoneNumber("1232123323").build();

//        LOGGER.info(tomAccount);
        LOGGER.info(tomAccountAccess);
        LOGGER.info(tomCard);
        LOGGER.info(tomDeposit);
        LOGGER.info(tomTransaction);
        LOGGER.info(tomTransfer);
//        LOGGER.info(tom);

        List<Account> accountList = new ArrayList<>();
        accountList.add(tomAccount);
        List<AccountAccess> accountAccessList = new ArrayList<>();
        accountAccessList.add(tomAccountAccess);
        List<Card> cardList = new ArrayList<>();
        cardList.add(tomCard);
        List<DepositWithdraw> depositWithdrawList = new ArrayList<>();
        depositWithdrawList.add(tomDeposit);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(tomTransaction);
        List<Transfer> transferList = new ArrayList<>();
        transferList.add(tomTransfer);
        List<User> userList = new ArrayList<>();
        userList.add(tom);

        ATM atm = new ATM(1, "atm - atm", accountList, accountAccessList, cardList, depositWithdrawList,
                transactionList, transferList, userList);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(System.getProperty("user.dir") + "/src/main/resources/atm_output.json"),
                    atm);
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