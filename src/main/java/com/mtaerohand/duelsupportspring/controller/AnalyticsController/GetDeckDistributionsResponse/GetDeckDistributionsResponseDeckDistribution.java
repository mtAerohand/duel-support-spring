package com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetDeckDistributionsResponse;

import lombok.Data;

/**
 * デッキ分布一覧情報の取得/デッキ分布
 */
@Data
public class GetDeckDistributionsResponseDeckDistribution {
    private Integer deckId;
    private Float ratio;
}
