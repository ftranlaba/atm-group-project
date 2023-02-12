import datamodels.Account;
import datamodels.builders.AccountBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainDriver {
    private static final Logger LOGGER = LogManager.getLogger("TESTLOGGER");

    public static void main(String[] args){

        Account tom = new AccountBuilder(1, 1, "Credit").withBalance(BigDecimal.valueOf(12.12))
                .withPin(1223).build();

        LOGGER.info(tom);
    }
}
