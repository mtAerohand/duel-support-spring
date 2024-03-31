package com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse;

import java.util.List;

import lombok.Data;

/**
 * デッキ一覧情報の取得レスポンス
 */
@Data
public class GetDecksResponse {
    List<GetDecksResponseDeck> decks;
}
