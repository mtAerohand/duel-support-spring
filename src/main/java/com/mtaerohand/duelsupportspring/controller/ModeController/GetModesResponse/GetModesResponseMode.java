package com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse;

import java.util.List;

import lombok.Data;

/**
 * モード一覧情報の取得/Mode
 */
@Data
public class GetModesResponseMode {
    private Integer id;
    private String name;
    private String pronounce;
    private Boolean isPermanent;
    private String remarks;
    private List<GetModesResponseModeModeDetail> modeDetails;
}
