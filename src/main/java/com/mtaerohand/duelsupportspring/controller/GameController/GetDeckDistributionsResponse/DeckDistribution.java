package com.mtaerohand.duelsupportspring.controller.GameController.GetDeckDistributionsResponse;

import lombok.Data;

/**
 * デッキ分布一覧情報の取得/デッキ分布
 */
@Data
public class DeckDistribution {
    private Integer deckId;
    private float ratio;
}
