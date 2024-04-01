package com.mtaerohand.duelsupportspring.controller.AnalyticsController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetDeckDistributionsResponse.GetDeckDistributionsResponse;
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetWinningRatesResponse.GetWinningRatesResponse;
import com.mtaerohand.duelsupportspring.service.AnalyticsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 分析データコントローラ
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    /**
     * デッキ分布一覧情報の取得
     * 
     * @param req デッキ分布一覧情報の取得リクエスト
     * @return デッキ分布一覧情報の取得レスポンス
     */
    @GetMapping("deck-distributions")
    public ResponseEntity<GetDeckDistributionsResponse> getDeckDistributions(@Valid GetDeckDistributionsRequest req) {
        GetDeckDistributionsResponse res = analyticsService.getDeckDistributions(req);
        return ResponseEntity.ok(res);
    }

    /**
     * 上位デッキの対面勝率一覧情報の取得
     * 
     * @param param 上位デッキの対面勝率一覧情報の取得リクエスト
     * @return 上位デッキの対面勝率一覧情報の取得レスポンス
     */
    @GetMapping("winning-rates")
    public ResponseEntity<GetWinningRatesResponse> getWinningRates(@Valid GetWinningRatesRequest req) {
        GetWinningRatesResponse res = analyticsService.getWinningRates(req);
        return ResponseEntity.ok(res);
    }

}
