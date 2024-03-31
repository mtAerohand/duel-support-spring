package com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse;

import java.util.List;

import lombok.Data;

/**
 * モード一覧情報の取得レスポンス
 */
@Data
public class GetModesResponse {
    private List<GetModesResponseMode> modes;
}
