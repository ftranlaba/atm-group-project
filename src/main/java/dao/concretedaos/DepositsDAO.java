package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IDepositsDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class DepositsDAO extends AbstractDAO<Deposit> implements IDepositsDAO {
    private static final String TABLE_NAME = "deposits";
    private static final String ID_COLUMN_NAME = "id_deposit";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_account");
        COLUMN_NAMES.add("time");
        COLUMN_NAMES.add("old_balance");
        COLUMN_NAMES.add("new_balance");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Deposit createEntityFromRow(ResultSet rs) throws SQLException {
        Deposit deposit = new Deposit();
        deposit.setId(rs.getLong(ID_COLUMN_NAME));
        deposit.setAccountId(rs.getLong("id_account"));
        deposit.setTime(rs.getTimestamp("time"));
        deposit.setOldBalance(rs.getInt("old_balance"));
        deposit.setNewBalance(rs.getInt("new_balance"));
        return deposit;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, Deposit entity) throws SQLException {
        ps.setLong(1, entity.getAccountId());
        ps.setTimestamp(2, entity.getTime());
        ps.setInt(3, entity.getOldBalance());
        ps.setInt(4, entity.getNewBalance());
    }
}
