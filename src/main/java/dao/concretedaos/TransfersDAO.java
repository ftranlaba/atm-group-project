package dao.concretedaos;

import dao.AbstractDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class TransfersDAO extends AbstractDAO<Transfer> implements ITransferDAO {
    private static final String TABLE_NAME = "transfers";
    private static final String ID_COLUMN_NAME = "id_transfer";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_account1");
        COLUMN_NAMES.add("id_account2");
        COLUMN_NAMES.add("old_balance_acc1");
        COLUMN_NAMES.add("new_balance_acc1");
        COLUMN_NAMES.add("old_balance_acc2");
        COLUMN_NAMES.add("new_balance_acc2");
        COLUMN_NAMES.add("time");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Transfer createEntityFromRow(ResultSet rs) throws SQLException {
        Transfer transfer = new Transfer();
        transfer.setId(rs.getLong(ID_COLUMN_NAME));
        transfer.setAccount1Id(rs.getLong("id_account1"));
        transfer.setAccount2Id(rs.getLong("id_account2"));
        transfer.setOldBalanceAcc1(rs.getInt("old_balance_acc1"));
        transfer.setNewBalanceAcc1(rs.getInt("new_balance_acc1"));
        transfer.setOldBalanceAcc2(rs.getInt("old_balance_acc2"));
        transfer.setNewBalanceAcc2(rs.getInt("new_balance_acc2"));
        transfer.setTime(rs.getTimestamp("time"));
        return transfer;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, Transfer entity) throws SQLException {
        ps.setLong(1, entity.getAccount1Id());
        ps.setLong(2, entity.getAccount2Id());
        ps.setInt(3, entity.getOldBalanceAcc1());
        ps.setInt(4, entity.getNewBalanceAcc1());
        ps.setInt(5, entity.getOldBalanceAcc2());
        ps.setInt(6, entity.getNewBalanceAcc2());
        ps.setTimestamp(7, entity.getTime());
    }
}
