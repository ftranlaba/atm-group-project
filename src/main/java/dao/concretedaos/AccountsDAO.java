package dao.concretedaos;

import com.sun.istack.Nullable;
import dao.AbstractDAO;
import dao.interfaces.IAccountsDAO;
import datamodels.Account;
import datamodels.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        COLUMN_NAMES.add("id_primary_account");
        COLUMN_NAMES.add("balance");
        COLUMN_NAMES.add("type");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Account createEntityFromRow(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt(ID_COLUMN_NAME));
        account.setIdForeignKey(rs.getInt("id_primary_account"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setType(rs.getString("type"));
        return account;
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
        ps.setBigDecimal(2, entity.getBalance());
        ps.setString(3, entity.getType());
    }

    /**
     * @param card The card to get the account of. The only fields that need to be set are the number, expiration date, and cvc.
     *             If it is valid then the corresponding account will be returned.
     *
     * @return The account corresponding to the card. If no account is found then null is returned.
     * @throws SQLException
     */
    @Override
    public @Nullable Account getAccountByCard(Card card) throws SQLException {
        String query = "SELECT * FROM accounts WHERE id_account = (SELECT id_account FROM cards WHERE number = (?) AND expiration_date = (?) AND cvc = (?))";
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, card.getCardNumber());
            ps.setString(2, card.getExpirationDate());
            ps.setInt(3, card.getCvc());

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            Account account = createEntityFromRow(rs);

            if (rs.next()) {
                throw new SQLException("More than one account found for card " + card);
            }

            return account;
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
