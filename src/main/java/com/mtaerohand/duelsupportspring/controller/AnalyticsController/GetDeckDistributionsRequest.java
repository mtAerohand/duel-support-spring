package com.mtaerohand.duelsupportspring.controller.AnalyticsController;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * デッキ分布一覧情報の取得リクエスト
 */
@Data
public class GetDeckDistributionsRequest {
    @NotNull(message = "モードIDが空です。")
    private Integer modeId;

    @NotNull(message = "モード詳細IDが空です。")
    private Integer modeDetailId;

    private LocalDateTime datetimeFrom;

    private LocalDateTime datetimeTo;

    private Integer limit = 8;
}
