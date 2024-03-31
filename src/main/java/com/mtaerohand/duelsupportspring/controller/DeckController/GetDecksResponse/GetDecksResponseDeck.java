package com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse;

import lombok.Data;

/**
 * デッキ一覧情報の取得レスポンス/Deck
 */
@Data
public class GetDecksResponseDeck {
    private Integer id;
    private String name;
    private String pronounce;
    private String remarks;
}
