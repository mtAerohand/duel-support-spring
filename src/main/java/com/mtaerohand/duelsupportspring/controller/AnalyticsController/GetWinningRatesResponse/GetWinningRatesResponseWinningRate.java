package com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetWinningRatesResponse;

import java.util.List;

import lombok.Data;

/**
 * 上位デッキの対面勝率一覧情報の取得レスポンス/WinningRate
 */
@Data
public class GetWinningRatesResponseWinningRate {
    private Integer deckId;
    private List<GetWinningRatesResponseWinningRateForDeck> winningRateForDecks;
}
