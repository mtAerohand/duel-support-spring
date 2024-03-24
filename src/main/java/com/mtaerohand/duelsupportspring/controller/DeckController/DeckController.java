package com.mtaerohand.duelsupportspring.controller.DeckController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<GetDecksResponse>> getDecks() {
        List<GetDecksResponse> res = deckService.getDecks();
        return ResponseEntity.ok(res);
    }
}
