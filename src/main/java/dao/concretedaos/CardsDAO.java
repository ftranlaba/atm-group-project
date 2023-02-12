package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.ICardsDAO;
import datamodels.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class CardsDAO extends AbstractDAO<Card> implements ICardsDAO {
    private static final String TABLE_NAME = "credit_cards";
    private static final String ID_COLUMN_NAME = "id_card";
    private static final List<String> COLUMN_NAMES;
    private static final Logger LOGGER = LogManager.getLogger(CardsDAO.class);

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_primary_account");
        COLUMN_NAMES.add("card_number");
        COLUMN_NAMES.add("expiration_date");
        COLUMN_NAMES.add("cvc");
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
        creditCard.setIdForeignKey(rs.getInt("id_primary_account"));
        creditCard.setCardNumber(rs.getString("card_number"));
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
        ps.setBoolean(5, entity.isBlock());
    }

    @Override
    public void toggleBlockStatus(Card card) throws SQLException {
        String query = "UPDATE cards " +
                "SET block = (?) " +
                "WHERE id_card = (?)";
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, !card.isBlock());
            ps.setInt(2, card.getId());
            ps.executeUpdate();
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        card.setBlock(!card.isBlock());
    }
}
