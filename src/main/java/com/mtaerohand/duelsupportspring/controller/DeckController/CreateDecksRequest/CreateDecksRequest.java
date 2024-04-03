package com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksRequest;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * デッキ情報の一括作成リクエスト
 */
@Data
public class CreateDecksRequest {
    @Valid
    private List<CreateDecksRequestDeck> decks;
}
