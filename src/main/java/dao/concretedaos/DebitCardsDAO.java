package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IDebitCardsDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class DebitCardsDAO extends AbstractDAO<DebitCard> implements IDebitCardsDAO {
    private static final String TABLE_NAME = "debit_cards";
    private static final String ID_COLUMN_NAME = "id_card";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_account");
        COLUMN_NAMES.add("number");
        COLUMN_NAMES.add("expiration_date");
        COLUMN_NAMES.add("cvc");
        COLUMN_NAMES.add("block");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected DebitCard createEntityFromRow(ResultSet rs) throws SQLException {
        DebitCard debitCard = new DebitCard();
        debitCard.setId(rs.getLong(ID_COLUMN_NAME));
        debitCard.setIdAccount(rs.getLong("id_account"));
        debitCard.setNumber(rs.getString("number"));
        debitCard.setExpirationDate(rs.getString("expiration_date"));
        debitCard.setCvc(rs.getInt("cvc"));
        debitCard.setBlock(rs.getBoolean("block"));
        return debitCard;
    }

    @Override
    protected String getIdColumnName() {
        return null;
    }

    @Override
    protected List<String> getColumnNames() {
        return null;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, DebitCard entity) throws SQLException {

    }
}
