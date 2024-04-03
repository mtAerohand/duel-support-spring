package com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksResponse;

import java.util.List;

import lombok.Data;

/**
 * デッキ情報の一括作成レスポンス
 */
@Data
public class CreateDecksResponse {
    private List<CreateDecksResponseDeck> decks;
}
