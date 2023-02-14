package dao.concretedaos;

import com.sun.istack.Nullable;
import dao.AbstractDAO;
import dao.interfaces.IAccountsDAO;
import dao.interfaces.ITransfersDAO;
import dao.util.exceptions.DAOException;
import datamodels.*;
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

    /**
     * This method is equivalent to calling update on the accounts individually.
     * However, it updates transactionally.
     *
     * @param fromAccount The account to transfer from.
     * @param toAccount   The account to transfer to.
     * @return True if the transfer was successful, false otherwise.
     * @throws SQLException If a database access error occurs.
     *                      If an error occurs while committing the transaction then neither account will be updated.
     */
    private static void updateBalanceForTransfer(Account fromAccount, Account toAccount) throws SQLException {
        String query = "UPDATE accounts " +
                "SET balance = (?) " +
                "WHERE id_account = (?)";

        Connection connection = CONNECTION_POOL.getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement ps1 = connection.prepareStatement(query)) {
            ps1.setBigDecimal(1, fromAccount.getBalance());
            ps1.setInt(2, fromAccount.getId());
            ps1.executeUpdate();

            ps1.setBigDecimal(1, toAccount.getBalance());
            ps1.setInt(2, toAccount.getId());
            ps1.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
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
    public @Nullable
    Account getAccount(Card card, int pin) throws SQLException {
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

            AccountAccess accountAccess = new AccountAccess();
            accountAccess.setIdForeignKey(account.getId());
            accountAccess.setTime(new Timestamp(System.currentTimeMillis()));
            accountAccess.setMacAddress("00:00:00:00:00:00");

            AccountAccessHistoryDAO accountAccessDAO = new AccountAccessHistoryDAO();
            accountAccessDAO.create(accountAccess);

            return account;
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
    public void makeDeposit(Account account, BigDecimal amount) {
        String query = "UPDATE accounts " +
                "SET balance = balance + (?) " +
                "WHERE id_account = (?)";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBigDecimal(1, amount);
            ps.setInt(2, account.getId());
            ps.executeUpdate();

            BigDecimal oldBalance = account.getBalance();
            BigDecimal newBalance = oldBalance.add(amount);

            DepositWithdrawHistoryDAO depositWithdrawHistoryDAO = new DepositWithdrawHistoryDAO();
            depositWithdrawHistoryDAO.logDepositOrWithdrawal(account, oldBalance, newBalance, "deposit");
        }
        catch(Exception e){
            LOGGER.error(e);
        }
    }

    @Override
    public void makeWithdrawal(Account account, BigDecimal amount) {
        String query = "UPDATE accounts " +
                "SET balance = balance - (?) " +
                "WHERE id_account = (?)";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBigDecimal(1, amount);
            ps.setInt(2, account.getId());
            ps.executeUpdate();

            BigDecimal oldBalance = account.getBalance();
            BigDecimal newBalance = oldBalance.subtract(amount);

            DepositWithdrawHistoryDAO depositWithdrawHistoryDAO = new DepositWithdrawHistoryDAO();
            depositWithdrawHistoryDAO.logDepositOrWithdrawal(account, oldBalance, newBalance, "withdrawal");
        }
        catch(Exception e){
            LOGGER.error(e);
        }
    }

    @Override
    public void makeTransfer(Account from, Account to, BigDecimal amount) {
        BigDecimal fromAccOldBalance = from.getBalance();

        BigDecimal fromAccNewBalance = fromAccOldBalance.subtract(amount);
        from.setBalance(fromAccNewBalance);

        BigDecimal toAccOldBalance = to.getBalance();
        BigDecimal toAccNewBalance = toAccOldBalance.add(amount);
        to.setBalance(toAccNewBalance);

        try {
            updateBalanceForTransfer(from, to);
        }
        catch(Exception e){
            LOGGER.error(e);
        }

        Transfer transfer = new Transfer();
        transfer.setIdForeignKey(from.getId());
        transfer.setIdAccount2(to.getId());
        transfer.setOldBalance(fromAccOldBalance);
        transfer.setNewBalance(fromAccNewBalance);
        transfer.setOldBalance2(toAccOldBalance);
        transfer.setNewBalance2(toAccNewBalance);
        transfer.setTime(new java.sql.Timestamp(System.currentTimeMillis()));

        ITransfersDAO transfersDAO = new TransfersDAO();
        transfersDAO.create(transfer);
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
