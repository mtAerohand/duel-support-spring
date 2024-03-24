package com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse;

import java.util.List;

import lombok.Data;

/**
 * モード一覧情報の取得レスポンス
 */
@Data
public class GetModesResponse {
    private Integer id;
    private String name;
    private String pronounce;
    private Boolean isPermanent;
    private String remarks;
    private List<ModeDetail> modeDetails;
}
