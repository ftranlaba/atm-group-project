package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IDepositWithdrawHistoryDAO;
import datamodels.DepositWithdraw;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class DepositWithdrawHistoryDAO extends AbstractDAO<DepositWithdraw> implements IDepositWithdrawHistoryDAO {
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
    protected DepositWithdraw createEntityFromRow(ResultSet rs) throws SQLException {
        DepositWithdraw deposit = new DepositWithdraw();
        deposit.setId(rs.getInt(ID_COLUMN_NAME));
        deposit.setIdForeignKey(rs.getInt("id_account"));
        deposit.setTime(rs.getTimestamp("time"));
        deposit.setOldBalance(rs.getBigDecimal("old_balance"));
        deposit.setNewBalance(rs.getBigDecimal("new_balance"));
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
    protected void setCreatePreparedStatement(PreparedStatement ps, DepositWithdraw entity) throws SQLException {
        ps.setLong(1, entity.getIdForeignKey());
        ps.setTimestamp(2, entity.getTime());
        ps.setBigDecimal(3, entity.getOldBalance());
        ps.setBigDecimal(4, entity.getNewBalance());
    }
}
