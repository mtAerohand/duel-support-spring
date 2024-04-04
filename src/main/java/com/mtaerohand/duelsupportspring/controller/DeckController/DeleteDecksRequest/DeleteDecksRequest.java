package com.mtaerohand.duelsupportspring.controller.DeckController.DeleteDecksRequest;

import java.util.List;

import lombok.Data;

/**
 * デッキ情報の一括削除リクエスト
 */
@Data
public class DeleteDecksRequest {
    private List<Integer> deckIds;
}
