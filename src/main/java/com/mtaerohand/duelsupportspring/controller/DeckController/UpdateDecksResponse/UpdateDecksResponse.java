package com.mtaerohand.duelsupportspring.controller.DeckController.UpdateDecksResponse;

import java.util.List;
import lombok.Data;

/**
 * デッキ情報の一括更新レスポンス
 */
@Data
public class UpdateDecksResponse {
    private List<UpdateDecksResponseDeck> decks;
}
