package com.mtaerohand.duelsupportspring.controller.GameController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesRequest.CreateGamesRequest;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesResponse.CreateGamesResponse;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantRequest.CreateGamesTorelantRequest;
import com.mtaerohand.duelsupportspring.controller.GameController.GetDeckDistributionsResponse.GetDeckDistributionsResponse;
import com.mtaerohand.duelsupportspring.service.GameService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 試合情報コントローラ
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Validated
public class GameController {
    private final GameService gameService;

    /**
     * 試合情報の一括作成
     * 
     * @param req 試合情報の一括作成リクエスト
     * @return 試合情報の一括作成レスポンス
     */
    @PostMapping("games")
    public ResponseEntity<CreateGamesResponse> createGames(@Valid @RequestBody CreateGamesRequest req)
            throws Exception {
        CreateGamesResponse res = gameService.createGames(req);
        return ResponseEntity.ok(res);
    }

    /**
     * 試合情報の一括作成(寛容)
     * 
     * @param req 試合情報の一括作成(寛容)リクエスト
     * @return 試合情報の一括作成(寛容)レスポンス
     */
    @PostMapping("games/torelant")
    public ResponseEntity<List<CreateGamesTorelantResponse>> createGamesTorelant(
            @RequestBody CreateGamesTorelantRequest req) throws Exception {
        List<CreateGamesTorelantResponse> res = gameService.createGamesTorelant(req);
        return ResponseEntity.ok(res);
    }

    /**
     * デッキ分布一覧情報の取得
     * 
     * @param req デッキ分布一覧情報の取得リクエスト
     * @return デッキ分布一覧情報の取得レスポンス
     */
    @GetMapping("deck-distributions")
    public ResponseEntity<GetDeckDistributionsResponse> getDeckDistributions(@Valid GetDeckDistributionsRequest req) {
        GetDeckDistributionsResponse res = gameService.getDeckDistributions(req);
        return ResponseEntity.ok(res);
    }

}
