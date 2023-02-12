package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.ITransfersDAO;
import datamodels.Transfer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class TransfersDAO extends AbstractDAO<Transfer> implements ITransfersDAO {
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
        transfer.setId(rs.getInt(ID_COLUMN_NAME));
        transfer.setIdForeignKey(rs.getInt("id_account1"));
        transfer.setIdAccount2(rs.getInt("id_account2"));
        transfer.setOldBalance(rs.getBigDecimal("old_balance_acc1"));
        transfer.setNewBalance(rs.getBigDecimal("new_balance_acc1"));
        transfer.setOldBalance2(rs.getBigDecimal("old_balance_acc2"));
        transfer.setNewBalance2(rs.getBigDecimal("new_balance_acc2"));
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
        ps.setInt(1, entity.getIdForeignKey());
        ps.setInt(2, entity.getIdAccount2());
        ps.setBigDecimal(3, entity.getOldBalance());
        ps.setBigDecimal(4, entity.getNewBalance());
        ps.setBigDecimal(5, entity.getOldBalance2());
        ps.setBigDecimal(6, entity.getNewBalance2());
        ps.setTimestamp(7, entity.getTime());
    }
}
