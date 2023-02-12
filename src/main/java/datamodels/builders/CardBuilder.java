package datamodels.builders;

import datamodels.Card;
import datamodels.TypeInfo;

public class CardBuilder extends TypeInfo {
    private String cardNumber;
    private String expirationDate;
    private int cvc;
    private boolean block;

    public CardBuilder(int id, int idForeignKey, String type) {
        super(id, idForeignKey, type);
    }

    public CardBuilder setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
        return this;
    }

    public CardBuilder setExpirationDate(String expirationDate){
        this.expirationDate = expirationDate;
        return this;
    }

    public CardBuilder setCvc(int cvc){
        this.cvc = cvc;
        return this;
    }

    public CardBuilder setBlock(boolean block){
        this.block = block;
        return this;
    }

    public Card build(){
        Card card = new Card();
        card.setId(getId());
        card.setIdForeignKey(getIdForeignKey());
        card.setType(getType());
        card.setCardNumber(cardNumber);
        card.setExpirationDate(expirationDate);
        card.setCvc(cvc);
        card.setBlock(block);
        return card;
    }
}
