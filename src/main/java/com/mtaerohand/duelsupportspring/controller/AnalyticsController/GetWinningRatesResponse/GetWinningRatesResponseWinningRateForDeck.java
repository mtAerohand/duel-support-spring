package com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetWinningRatesResponse;

import lombok.Data;

/**
 * 上位デッキの対面勝率一覧情報の取得レスポンス/WinningRate/GetWinningRatesResponseWinningRateForDeck
 */
@Data
public class GetWinningRatesResponseWinningRateForDeck {
    private Integer deckId;
    private Float winningRate;
    private Integer winCount;
    private Integer loseCount;
}
