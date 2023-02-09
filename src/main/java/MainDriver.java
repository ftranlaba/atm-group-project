import datamodels.Account;
import datamodels.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainDriver {
    private static final Logger LOGGER = LogManager.getLogger("TESTLOGGER");

    public static void main(String[] args){
        User mac = new User(1, "Mac", "Jane", "asdfgf", "asd");
        LOGGER.info(mac);

        Account macAccount = new Account(1, 1, 12, "Checking");
        LOGGER.info(macAccount);
    }
}
