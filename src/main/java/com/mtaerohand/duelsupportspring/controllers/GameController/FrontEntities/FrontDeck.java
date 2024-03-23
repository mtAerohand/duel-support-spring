package com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities;

import com.mtaerohand.duelsupportspring.Entities.Deck;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * デフォルト返却用デッキエンティティ
 */
@Data
@NoArgsConstructor
public class FrontDeck {
    private Integer id;
    private String name;
    private String pronounce;
    private String remarks;

    public FrontDeck(Deck deck) {
        this.id = deck.getId();
        this.name = deck.getName();
        this.pronounce = deck.getPronounce();
        this.remarks = deck.getRemarks();
    }
}
