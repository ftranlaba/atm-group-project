package dao.interfaces;

import dao.IBaseDAO;
import datamodels.Card;

import java.sql.SQLException;

/**
 * @author Moussa
 */

public interface ICardsDAO extends IBaseDAO<Card> {
    /**
     * @param card The card to toggle the block status of. It will be modified inplace.
     */
    void toggleBlockStatus(Card card);

    /**
     * @param card The card to set the info of. It will be modified inplace. The Number, PIN and CVC fields will be set.
     */
    void setCardInfo(Card card);
}
