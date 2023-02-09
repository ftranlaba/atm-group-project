package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IWithdrawsDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class WithdrawsDAO extends AbstractDAO<Withdraw> implements IWithdrawsDAO {
    private static final String TABLE_NAME = "withdraws";
    private static final String ID_COLUMN_NAME = "id_withdraw";
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
    protected Withdraw createEntityFromRow(ResultSet rs) throws SQLException {
        Withdraw withdraw = new Withdraw();
        withdraw.setId(rs.getLong(ID_COLUMN_NAME));
        withdraw.setAccountId(rs.getLong("id_account"));
        withdraw.setTime(rs.getTimestamp("time"));
        withdraw.setOldBalance(rs.getInt("old_balance"));
        withdraw.setNewBalance(rs.getInt("new_balance"));
        return withdraw;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, Withdraw entity) throws SQLException {
        ps.setLong(1, entity.getAccountId());
        ps.setTimestamp(2, entity.getTime());
        ps.setInt(3, entity.getOldBalance());
        ps.setInt(4, entity.getNewBalance());
    }
}
