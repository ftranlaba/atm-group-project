package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IAccountsDAO;

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
        account.setId(rs.getLong(ID_COLUMN_NAME));
        account.setPrimaryAccountId(rs.getLong("id_primary_account"));
        account.setBalance(rs.getInt("balance"));
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
        ps.setLong(1, entity.getPrimaryAccountId());
        ps.setInt(2, entity.getBalance());
        ps.setString(3, entity.getType());
    }
}
