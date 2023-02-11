package dao.concretedaos;

import com.sun.istack.Nullable;
import dao.AbstractDAO;
import dao.interfaces.IAccountsDAO;
import dao.interfaces.IDepositWithdrawHistoryDAO;
import datamodels.Account;
import datamodels.AccountAccess;
import datamodels.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
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
        ps.setBigDecimal(2, entity.getBalance());
        ps.setString(3, entity.getType());
    }

    @Override
    public @Nullable Account getAccount(Card card, int pin) throws SQLException {
        String query = "SELECT * " +
                "FROM accounts " +
                "WHERE pin = (?) AND id_account = " +
                "(SELECT id_account " +
                "FROM cards " +
                "WHERE number = (?) AND expiration_date = (?) AND cvc = (?))";
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, card.getCardNumber());
            ps.setString(2, card.getExpirationDate());
            ps.setInt(3, card.getCvc());
            ps.setInt(4, pin);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            Account account = createEntityFromRow(rs);

            AccountAccess accountAccess = new AccountAccess();
            accountAccess.setIdForeignKey(account.getId());
            accountAccess.setTime(new Timestamp(System.currentTimeMillis()));
            accountAccess.setMacAddress("00:00:00:00:00:00");

            AccountAccessHistoryDAO accountAccessDAO = new AccountAccessHistoryDAO();
            accountAccessDAO.create(accountAccess);

            return account;
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
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
    public void makeDeposit(Account account, BigDecimal amount) throws SQLException {
        String query = "UPDATE accounts " +
                "SET balance = balance + (?) " +
                "WHERE id_account = (?)";
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBigDecimal(1, amount);
            ps.setInt(2, account.getId());
            ps.executeUpdate();

            BigDecimal oldBalance = account.getBalance();
            BigDecimal newBalance = oldBalance.add(amount);

            IDepositWithdrawHistoryDAO depositWithdrawHistoryDAO = new DepositWithdrawHistoryDAO();
            depositWithdrawHistoryDAO.logDepositOrWithdrawal(account, oldBalance, newBalance, "deposit");
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public boolean makeWithdrawal(Account account, BigDecimal amount) throws SQLException {
        if (amount.compareTo(account.getBalance()) > 0) {
            return false;
        }

        String query = "UPDATE accounts " +
                "SET balance = balance - (?) " +
                "WHERE id_account = (?)";
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBigDecimal(1, amount);
            ps.setInt(2, account.getId());
            ps.executeUpdate();

            BigDecimal oldBalance = account.getBalance();
            BigDecimal newBalance = oldBalance.subtract(amount);

            IDepositWithdrawHistoryDAO depositWithdrawHistoryDAO = new DepositWithdrawHistoryDAO();
            depositWithdrawHistoryDAO.logDepositOrWithdrawal(account, oldBalance, newBalance, "withdrawal");
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return false;
    }
}
