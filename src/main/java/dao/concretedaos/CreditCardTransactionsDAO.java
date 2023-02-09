package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.ICreditCardTransactions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class CreditCardTransactionsDAO extends AbstractDAO<CreditCardTransaction> implements ICreditCardTransactions {
    private static final String TABLE_NAME = "credit_card_transactions";
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
    protected CreditCardTransaction createEntityFromRow(ResultSet rs) throws SQLException {
        CreditCardTransaction creditCardTransaction = new CreditCardTransaction();
        creditCardTransaction.setId(rs.getLong(ID_COLUMN_NAME));
        creditCardTransaction.setCardId(rs.getLong("id_card"));
        creditCardTransaction.setTime(rs.getTimestamp("time"));
        creditCardTransaction.setMerchantName(rs.getString("merchant_name"));
        creditCardTransaction.setCost(rs.getInt("cost"));
        return creditCardTransaction;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, CreditCardTransaction entity) throws SQLException {
        ps.setLong(1, entity.getCardId());
        ps.setTimestamp(2, entity.getTime());
        ps.setString(3, entity.getMerchantName());
        ps.setInt(4, entity.getCost());
    }
}
