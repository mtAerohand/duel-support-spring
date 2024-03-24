package com.mtaerohand.duelsupportspring.controller.ModeController;

import lombok.Data;

/**
 * モード一覧の取得リクエスト
 */
@Data
public class GetModesRequest {
    private Boolean isOngoing = false;
}
