package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IAccountAccessHistoryDAO;
import datamodels.Account;
import datamodels.AccountAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Moussa
 */
public class AccountAccessHistoryDAO extends AbstractDAO<AccountAccess> implements IAccountAccessHistoryDAO {
    private static final String TABLE_NAME = "account_access_history";
    private static final String ID_COLUMN_NAME = "id_history";
    private static final List<String> COLUMN_NAMES;
    private static final Random RANDOM = new Random();

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

    @Override
    public void logAccountAccess(Account account) {
        AccountAccess accountAccess = new AccountAccess();
        accountAccess.setIdForeignKey(account.getId());
        accountAccess.setTime(new Timestamp(System.currentTimeMillis()));
        accountAccess.setMacAddress(getMacAddress());

        create(accountAccess);
    }

    private static String getMacAddress() {
        StringBuilder sb = new StringBuilder();
        int numPairs = 6;
        for (int i = 0; i < numPairs; i++) {
            sb.append(String.format("%02X%s", RANDOM.nextInt(256), (i < 5) ? ":" : ""));
        }
        
        return sb.toString();
    }
}