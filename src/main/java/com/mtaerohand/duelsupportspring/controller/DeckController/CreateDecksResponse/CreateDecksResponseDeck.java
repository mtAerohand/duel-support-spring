package com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksResponse;

import lombok.Data;

/**
 * デッキ情報の一括作成レスポンス/Deck
 */
@Data
public class CreateDecksResponseDeck {
    private Integer id;
    private String name;
    private String pronounce;
    private String remarks;
}
