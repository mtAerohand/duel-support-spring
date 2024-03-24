package com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * モード一覧の取得レスポンス/モード詳細
 */
@Data
public class ModeDetail {
    private Integer id;
    private String name;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String remarks;
}
