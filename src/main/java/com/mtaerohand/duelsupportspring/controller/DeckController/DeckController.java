package com.mtaerohand.duelsupportspring.controller.DeckController;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse.GetDecksResponse;
import com.mtaerohand.duelsupportspring.service.DeckService;

import lombok.RequiredArgsConstructor;

/**
 * デッキ情報コントローラ
 */
// TODO: バージョンをpropertiesで管理する
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Validated
public class DeckController {
    private final DeckService deckService;

    /**
     * デッキ一覧情報の取得
     * 
     * @param req デッキ一覧情報の取得リクエスト
     * @return デッキ一覧情報の取得レスポンス
     */
    @GetMapping("decks")
    @ResponseStatus(HttpStatus.OK)
    public GetDecksResponse getDecks() {
        GetDecksResponse res = deckService.getDecks();
        return res;
    }

    /**
     * デッキ情報の取得
     * 
     * @param id デッキID
     * @return デッキ情報の取得レスポンス
     */
    @GetMapping("decks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetDeckResponse getDecks(@PathVariable Integer id) {
        GetDeckResponse res = deckService.getDeck(id);
        return res;
    }
}
