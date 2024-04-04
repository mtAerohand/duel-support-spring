package com.mtaerohand.duelsupportspring.controller.DeckController;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksRequest.CreateDecksRequest;
import com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksResponse.CreateDecksResponse;
import com.mtaerohand.duelsupportspring.controller.DeckController.DeleteDecksRequest.DeleteDecksRequest;
import com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse.GetDecksResponse;
import com.mtaerohand.duelsupportspring.controller.DeckController.UpdateDecksRequest.UpdateDecksRequest;
import com.mtaerohand.duelsupportspring.service.DeckService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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

    /**
     * デッキ情報の一括作成
     * 
     * @param req デッキ情報の一括作成リクエスト
     * @return デッキ情報の一括作成レスポンス
     */
    @PostMapping("decks")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDecksResponse createDecks(@Valid @RequestBody CreateDecksRequest req) {
        CreateDecksResponse res = deckService.createDecks(req);
        return res;
    }

    /**
     * デッキ情報の一括更新
     * 
     * @param req デッキ情報の一括更新リクエスト
     * @return デッキ情報の一括更新レスポンス
     */
    @PutMapping("decks")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateDecks(@Valid @RequestBody UpdateDecksRequest req) {
        deckService.updateDecks(req);
    }

    /**
     * デッキ情報の一括削除
     * 
     */
    @DeleteMapping("decks")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDecks(@Valid @RequestBody DeleteDecksRequest req) {
        deckService.deleteDecks(req);
    }
}
