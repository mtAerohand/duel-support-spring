package com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetWinningRatesResponse;

import java.util.List;

import lombok.Data;

/**
 * 上位デッキの対面勝率一覧情報の取得レスポンス
 */
@Data
public class GetWinningRatesResponse {
    private List<GetWinningRatesResponseWinningRate> winningRates;
}
