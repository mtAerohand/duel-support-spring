package com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksRequest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

/**
 * デッキ情報の一括作成リクエスト/Deck
 */
@Value
public class CreateDecksRequestDeck {
    @NotEmpty
    @Size(max = 30)
    private String name;

    @NotEmpty
    @Size(max = 30)
    private String pronounce;

    @Size(max = 100)
    private String remarks;
}
