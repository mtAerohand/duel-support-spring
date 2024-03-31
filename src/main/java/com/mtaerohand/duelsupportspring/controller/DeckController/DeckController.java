package com.mtaerohand.duelsupportspring.controller.DeckController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse.GetDecksResponse;
import com.mtaerohand.duelsupportspring.service.DeckService;

import lombok.RequiredArgsConstructor;

/**
 * デッキ情報コントローラ
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class DeckController {
    private final DeckService deckService;

    /**
     * デッキ一覧情報の取得
     * 
     * @param req デッキ一覧情報の取得リクエスト
     * @return デッキ一覧情報の取得レスポンス
     */
    @GetMapping("decks")
    public ResponseEntity<GetDecksResponse> getDecks() {
        GetDecksResponse res = deckService.getDecks();
        return ResponseEntity.ok(res);
    }

    /**
     * デッキ情報の取得
     * 
     * @param id デッキID
     * @return デッキ情報の取得レスポンス
     */
    @GetMapping("decks/{id}")
    public ResponseEntity<GetDeckResponse> getDecks(@PathVariable Integer id) {
        GetDeckResponse res = deckService.getDeck(id);
        return ResponseEntity.ok(res);
    }
}
