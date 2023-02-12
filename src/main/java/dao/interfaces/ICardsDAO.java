package dao.interfaces;

import datamodels.Card;

import java.sql.SQLException;

/**
 * @author Moussa
 */
public interface ICardsDAO extends IBaseDAO<Card> {
    /**
     * @param card The card to toggle the block status of. It will be modified inplace.
     */
    void toggleBlockStatus(Card card) throws SQLException;
}
