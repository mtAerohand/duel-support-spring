package com.mtaerohand.duelsupportspring.controller.GameController.GetDeckDistributionsResponse;

import java.util.List;

import lombok.Data;

/**
 * デッキ分布一覧情報の取得レスポンス
 */
@Data
public class GetDeckDistributionsResponse {
    private Integer modeId;
    private Integer modeDetailId;
    private List<DeckDistribution> deckDistributions;
}
