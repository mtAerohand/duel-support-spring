package com.mtaerohand.duelsupportspring.controller.AnalyticsController;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * デッキ分布一覧情報の取得リクエスト
 */
@Data
public class GetDeckDistributionsRequest {
    @NotNull
    private Integer modeId;

    @NotNull
    private Integer modeDetailId;

    private LocalDateTime datetimeFrom;

    private LocalDateTime datetimeTo;

    private Integer limit = 8;
}
