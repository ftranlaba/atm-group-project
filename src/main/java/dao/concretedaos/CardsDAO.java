package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.ICardsDAO;
import datamodels.Card;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class CardsDAO extends AbstractDAO<Card> implements ICardsDAO {
    private static final String TABLE_NAME = "cards";
    private static final String ID_COLUMN_NAME = "id_card";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_account");
        COLUMN_NAMES.add("number");
        COLUMN_NAMES.add("expiration_date");
        COLUMN_NAMES.add("cvc");
        COLUMN_NAMES.add("type");
        COLUMN_NAMES.add("block");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Card createEntityFromRow(ResultSet rs) throws SQLException {
        Card creditCard = new Card();
        creditCard.setId(rs.getInt(ID_COLUMN_NAME));
        creditCard.setIdForeignKey(rs.getInt("id_account"));
        creditCard.setCardNumber(rs.getString("number"));
        creditCard.setExpirationDate(rs.getString("expiration_date"));
        creditCard.setCvc(rs.getInt("cvc"));
        creditCard.setBlock(rs.getBoolean("block"));
        return creditCard;
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
    protected void setCreatePreparedStatement(PreparedStatement ps, Card entity) throws SQLException {
        ps.setLong(1, entity.getIdForeignKey());
        ps.setString(2, entity.getCardNumber());
        ps.setString(3, entity.getExpirationDate());
        ps.setInt(4, entity.getCvc());
        ps.setString(5, entity.getType());
        ps.setBoolean(6, entity.isBlock());
    }
}
