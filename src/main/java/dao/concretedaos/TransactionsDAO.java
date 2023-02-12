package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.ITransactionsDAO;
import datamodels.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class TransactionsDAO extends AbstractDAO<Transaction> implements ITransactionsDAO {
    private static final String TABLE_NAME = "transactions";
    private static final String ID_COLUMN_NAME = "id_transaction";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_card");
        COLUMN_NAMES.add("time");
        COLUMN_NAMES.add("merchant_name");
        COLUMN_NAMES.add("cost");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Transaction createEntityFromRow(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt(ID_COLUMN_NAME));
        transaction.setIdForeignKey(rs.getInt("id_card"));
        transaction.setTime(rs.getTimestamp("time"));
        transaction.setMerchantName(rs.getString("merchant_name"));
        transaction.setCost(rs.getBigDecimal("cost"));
        return transaction;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, Transaction entity) throws SQLException {
        ps.setInt(1, entity.getIdForeignKey());
        ps.setTimestamp(2, entity.getTime());
        ps.setString(3, entity.getMerchantName());
        ps.setBigDecimal(4, entity.getCost());
    }
}