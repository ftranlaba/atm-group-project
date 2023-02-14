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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Moussa
 */
public class CardsDAO extends AbstractDAO<Card> implements ICardsDAO {
    private static final String TABLE_NAME = "cards";
    private static final String ID_COLUMN_NAME = "id_card";
    private static final List<String> COLUMN_NAMES;
    private static final Logger LOGGER = LogManager.getLogger(CardsDAO.class);
    private static final Random RANDOM = new Random();

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("id_account");
        COLUMN_NAMES.add("number");
        COLUMN_NAMES.add("expiration_date");
        COLUMN_NAMES.add("cvc");
        COLUMN_NAMES.add("type");
        COLUMN_NAMES.add("block");
    }

    private static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        // No leading 0.
        cardNumber.append(RANDOM.nextInt(9) + 1);

        int cardNumberLength = 16;
        for (int i = 1; i < cardNumberLength; i++) {
            cardNumber.append(RANDOM.nextInt(10));
        }
        return cardNumber.toString();
    }

    private static String calculateExpirationDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusYears(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yy");
        return formatter.format(expirationDate);
    }

    private static int generateCvc() {
        int min = 101;
        int max = 999;
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
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
        creditCard.setType(rs.getString("type"));
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

    @Override
    public void toggleBlockStatus(Card card) {
        String query = "UPDATE cards " +
                "SET block = (?) " +
                "WHERE id_card = (?)";
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, !card.isBlock());
            ps.setInt(2, card.getId());
            ps.executeUpdate();
        }
        catch(Exception e){
            LOGGER.error(e);
        }
        card.setBlock(!card.isBlock());
    }

    @Override
    public void setCardInfo(Card card) {
        card.setCardNumber(generateCardNumber());
        card.setExpirationDate(calculateExpirationDate());
        card.setCvc(generateCvc());
    }
}
