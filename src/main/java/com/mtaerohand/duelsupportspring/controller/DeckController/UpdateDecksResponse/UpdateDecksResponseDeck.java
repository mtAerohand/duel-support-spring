package com.mtaerohand.duelsupportspring.controller.DeckController.UpdateDecksResponse;

import lombok.Data;

/**
 * デッキ情報の一括更新レスポンス/Deck
 */
@Data
public class UpdateDecksResponseDeck {
    private Integer id;
    private String name;
    private String pronounce;
    private String remarks;
}
