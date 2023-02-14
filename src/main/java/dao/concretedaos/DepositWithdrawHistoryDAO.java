package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IDepositWithdrawHistoryDAO;
import datamodels.Account;
import datamodels.DepositWithdraw;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class DepositWithdrawHistoryDAO extends AbstractDAO<DepositWithdraw> implements IDepositWithdrawHistoryDAO {
    private static final String TABLE_NAME = "deposit_withdraw_history";
    private static final String ID_COLUMN_NAME = "id_history";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_account");
        COLUMN_NAMES.add("time");
        COLUMN_NAMES.add("old_balance");
        COLUMN_NAMES.add("new_balance");
        COLUMN_NAMES.add("type");
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
        deposit.setType(rs.getString("type"));
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
        ps.setString(5, entity.getType());
    }

    @Override
    public void logDepositOrWithdrawal(Account account, BigDecimal oldBalance, BigDecimal newBalance, String type) throws SQLException {
        DepositWithdraw depositWithdraw = new DepositWithdraw();
        depositWithdraw.setIdForeignKey(account.getId());
        depositWithdraw.setTime(new Timestamp(System.currentTimeMillis()));
        depositWithdraw.setOldBalance(oldBalance);
        depositWithdraw.setNewBalance(newBalance);
        depositWithdraw.setType(type);

        create(depositWithdraw);
    }
}
