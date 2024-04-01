package com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetDeckDistributionsResponse;

import java.util.List;

import lombok.Data;

/**
 * デッキ分布一覧情報の取得レスポンス
 */
@Data
public class GetDeckDistributionsResponse {
    private List<GetDeckDistributionsResponseDeckDistribution> deckDistributions;
}
