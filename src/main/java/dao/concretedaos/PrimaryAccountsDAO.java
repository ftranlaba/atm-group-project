package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IPrimaryAccountsDAO;
import datamodels.PrimaryAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class PrimaryAccountsDAO extends AbstractDAO<PrimaryAccount> implements IPrimaryAccountsDAO {
    private static final String TABLE_NAME = "primary_accounts";
    private static final String ID_COLUMN_NAME = "id_primary_account";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_user");
        COLUMN_NAMES.add("pin");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected PrimaryAccount createEntityFromRow(ResultSet rs) throws SQLException {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setId(rs.getLong(ID_COLUMN_NAME));
        primaryAccount.setUserId(rs.getLong("id_user"));
        primaryAccount.setPin(rs.getInt("pin"));
        return primaryAccount;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, PrimaryAccount entity) throws SQLException {
        ps.setLong(1, entity.getUserId());
        ps.setInt(2, entity.getPin());
    }
}
