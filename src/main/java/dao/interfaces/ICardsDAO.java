package dao.interfaces;

import datamodels.Card;

import java.sql.SQLException;

/**
 * @author Moussa
 */
<<<<<<< HEAD
public interface ICardsDAO extends IBaseDAO<Card>{
}
=======
public interface ICardsDAO extends IBaseDAO<Card> {
    /**
     * @param card The card to toggle the block status of. It will be modified inplace.
     */
    void toggleBlockStatus(Card card) throws SQLException;

    /**
     * @param card The card to set the info of. It will be modified inplace. The Number, PIN and CVC fields will be set.
     */
    void setCardInfo(Card card);
}
>>>>>>> 8c0cfe6aa541b68c9ba34970652db7ac3ea71762
