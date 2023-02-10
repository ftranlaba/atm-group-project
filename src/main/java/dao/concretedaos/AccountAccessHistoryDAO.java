package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IAccountAccessHistory;
import datamodels.AccountAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class AccountAccessHistoryDAO extends AbstractDAO<AccountAccess> implements IAccountAccessHistory {
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
    protected AccountAccess createEntityFromRow(ResultSet rs) throws SQLException {
        AccountAccess accountAccess = new AccountAccess();
        accountAccess.setId(rs.getInt(ID_COLUMN_NAME));
        accountAccess.setIdForeignKey(rs.getInt("id_account"));
        accountAccess.setTime(rs.getTimestamp("time"));
        accountAccess.setMacAddress(rs.getString("mac_address"));
        return accountAccess;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, AccountAccess entity) throws SQLException {
        ps.setLong(1, entity.getIdForeignKey());
        ps.setTimestamp(2, entity.getTime());
        ps.setString(3, entity.getMacAddress());
    }
}
