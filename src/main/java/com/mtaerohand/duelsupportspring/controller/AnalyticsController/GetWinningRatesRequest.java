package com.mtaerohand.duelsupportspring.controller.AnalyticsController;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 上位デッキの対面勝率一覧情報の取得リクエスト
 */
@Data
public class GetWinningRatesRequest {
    @NotNull
    private Integer modeId;

    @NotNull
    private Integer modeDetailId;

    private LocalDateTime datetimeFrom;

    private LocalDateTime datetimeTo;

    private Integer limit = 8;
}
