package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IDebitCardTransactions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class DebitCardTransactionsDAO extends AbstractDAO<DebitCardTransaction> implements IDebitCardTransactions {
    private static final String TABLE_NAME = "debit_card_transactions";
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
    protected DebitCardTransaction createEntityFromRow(ResultSet rs) throws SQLException {
        DebitCardTransaction debitCardTransaction = new DebitCardTransaction();
        debitCardTransaction.setId(rs.getLong(ID_COLUMN_NAME));
        debitCardTransaction.setCardId(rs.getLong("id_card"));
        debitCardTransaction.setTime(rs.getTimestamp("time"));
        debitCardTransaction.setMerchantName(rs.getString("merchant_name"));
        debitCardTransaction.setCost(rs.getInt("cost"));
        return debitCardTransaction;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, DebitCardTransaction entity) throws SQLException {
        ps.setLong(1, entity.getCardId());
        ps.setTimestamp(2, entity.getTime());
        ps.setString(3, entity.getMerchantName());
        ps.setInt(4, entity.getCost());
    }
}
