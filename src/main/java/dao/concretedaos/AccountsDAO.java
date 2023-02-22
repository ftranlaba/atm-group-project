package dao.concretedaos;

import com.sun.istack.Nullable;
import dao.AbstractDAO;
import dao.interfaces.IAccountAccessHistoryDAO;
import dao.interfaces.IAccountsDAO;
import dao.interfaces.IDepositWithdrawHistoryDAO;
import dao.interfaces.ITransfersDAO;
import datamodels.Account;
import datamodels.Card;
import datamodels.Transfer;
import datamodels.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
@SuppressWarnings("java:S1192")
public class AccountsDAO extends AbstractDAO<Account> implements IAccountsDAO {
    private static final String TABLE_NAME = "accounts";
    private static final String ID_COLUMN_NAME = "id_account";
    private static final List<String> COLUMN_NAMES;
    private static final Logger LOGGER = LogManager.getLogger(AccountsDAO.class);

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_user");
        COLUMN_NAMES.add("pin");
        COLUMN_NAMES.add("balance");
        COLUMN_NAMES.add("type");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getIdColumnName() {
        return ID_COLUMN_NAME;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Account entity) throws SQLException {
        ps.setInt(1, entity.getIdForeignKey());
        ps.setInt(2, entity.getPin());
        ps.setBigDecimal(3, entity.getBalance());
        ps.setString(4, entity.getType());
    }

    @Override
    public @Nullable Account getAccount(Card card, int pin) {
        String query = "SELECT * " +
                "FROM accounts " +
                "WHERE pin = (?) AND id_account = " +
                "(SELECT id_account " +
                "FROM cards " +
                "WHERE number = (?) AND expiration_date = (?) AND cvc = (?))";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, pin);
            ps.setString(2, card.getCardNumber());
            ps.setString(3, card.getExpirationDate());
            ps.setInt(4, card.getCvc());

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            Account account = createEntityFromRow(rs);

            IAccountAccessHistoryDAO accountAccessHistoryDAO = new AccountAccessHistoryDAO();
            accountAccessHistoryDAO.logAccountAccess(account);

            return account;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    @Override
    protected Account createEntityFromRow(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt(ID_COLUMN_NAME));
        account.setIdForeignKey(rs.getInt("id_user"));
        account.setPin(rs.getInt("pin"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setType(rs.getString("type"));
        return account;
    }

    @Override
    public void makeDeposit(Account account, BigDecimal amount) {
        BigDecimal oldBalance = account.getBalance();
        BigDecimal newBalance = oldBalance.add(amount);

        makeDepositOrWithdrawal(account, newBalance, "Deposit");
    }

    @Override
    public void makeWithdrawal(Account account, BigDecimal amount) {
        BigDecimal oldBalance = account.getBalance();
        BigDecimal newBalance = oldBalance.subtract(amount);

        makeDepositOrWithdrawal(account, newBalance, "Withdrawal");
    }

    private static void makeDepositOrWithdrawal(Account account, BigDecimal newBalance, String type) {
        String query = "UPDATE accounts " +
                "SET balance = (?) " +
                "WHERE id_account = (?)";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBigDecimal(1, newBalance);
            ps.setInt(2, account.getId());
            ps.executeUpdate();
            IDepositWithdrawHistoryDAO depositWithdrawHistoryDAO = new DepositWithdrawHistoryDAO();
            depositWithdrawHistoryDAO.logDepositOrWithdrawal(account, account.getBalance(), newBalance, type);
            account.setBalance(newBalance);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public boolean makeTransfer(Account from, Account to, BigDecimal amount) {
        BigDecimal fromAccOldBalance = from.getBalance();
        BigDecimal fromAccNewBalance = fromAccOldBalance.subtract(amount);

        BigDecimal toAccOldBalance = to.getBalance();
        BigDecimal toAccNewBalance = toAccOldBalance.add(amount);

        if (!updateBalanceAfterTransfer(from, fromAccNewBalance, to, toAccNewBalance)) {
            return false;
        }

        Transfer transfer = new Transfer();
        transfer.setIdForeignKey(from.getId());
        transfer.setIdAccount2(to.getId());
        transfer.setOldBalance(fromAccOldBalance);
        transfer.setNewBalance(fromAccNewBalance);
        transfer.setOldBalance2(toAccOldBalance);
        transfer.setNewBalance2(toAccNewBalance);
        transfer.setTime(new Timestamp(System.currentTimeMillis()));

        ITransfersDAO transfersDAO = new TransfersDAO();
        transfersDAO.create(transfer);
        return true;
    }

    /**
     * This method is equivalent to calling update on the accounts individually.
     * However, it updates transactionally.
     *
     * @param fromAccount       The account to transfer from.
     * @param fromAccNewBalance
     * @param toAccount         The account to transfer to.
     * @param toAccNewBalance
     * @return True if the transfer was successful, false otherwise.
     */
    private static boolean updateBalanceAfterTransfer(Account fromAccount, BigDecimal fromAccNewBalance, Account toAccount, BigDecimal toAccNewBalance) {
        String query = "UPDATE accounts " +
                "SET balance = (?) " +
                "WHERE id_account = (?)";

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps1 = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            ps1.setBigDecimal(1, fromAccount.getBalance());
            ps1.setInt(2, fromAccount.getId());
            ps1.executeUpdate();

            ps1.setBigDecimal(1, toAccount.getBalance());
            ps1.setInt(2, toAccount.getId());
            ps1.executeUpdate();

            connection.commit();
            fromAccount.setBalance(fromAccNewBalance);
            toAccount.setBalance(toAccNewBalance);
            return true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }

            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public void createAccount(User user, Account account, Card card) {
        account.setIdForeignKey(user.getId());
        account.setBalance(BigDecimal.ZERO);
        create(account);

        card.setIdForeignKey(account.getId());
        card.setType(account.getType());
        card.setBlock(false);

        CardsDAO cardDAO = new CardsDAO();
        cardDAO.setCardInfo(card);
        cardDAO.create(card);
    }
}
