package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IAccountAccessHistory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class AccountAccessHistoryDAO extends AbstractDAO<AccountAccessHistory> implements IAccountAccessHistory {
    private static final String TABLE_NAME = "account_access_history";
    private static final String ID_COLUMN_NAME = "id_history";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_account");
        COLUMN_NAMES.add("time");
        COLUMN_NAMES.add("mac_address");
    }


    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected AccountAccessHistory createEntityFromRow(ResultSet rs) throws SQLException {
        AccountAccessHistory accountAccessHistory = new AccountAccessHistory();
        accountAccessHistory.setId(rs.getLong(ID_COLUMN_NAME));
        accountAccessHistory.setAccountId(rs.getLong("id_account"));
        accountAccessHistory.setTime(rs.getTimestamp("time"));
        accountAccessHistory.setMacAddress(rs.getString("mac_address"));
        return accountAccessHistory;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, AccountAccessHistory entity) throws SQLException {
        ps.setLong(1, entity.getAccountId());
        ps.setTimestamp(2, entity.getTime());
        ps.setString(3, entity.getMacAddress());
    }
}
