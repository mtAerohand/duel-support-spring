package com.mtaerohand.duelsupportspring.controller.DeckController.UpdateDecksRequest;

import lombok.Data;
import java.util.List;

import jakarta.validation.Valid;

/**
 * デッキ情報の一括更新リクエスト
 */
@Data
public class UpdateDecksRequest {
    @Valid
    private List<UpdateDecksRequestDeck> decks;
}
