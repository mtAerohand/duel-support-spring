package com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities;

import java.time.LocalDateTime;

import com.mtaerohand.duelsupportspring.Entities.ModeDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * デフォルト返却用モード詳細エンティティ
 */
@Data
@NoArgsConstructor
public class FrontModeDetail {
    private Integer id;
    private Integer modeId;
    private String name;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String remarks;

    public FrontModeDetail(ModeDetail modeDetail) {
        this.id = modeDetail.getId();
        this.modeId = modeDetail.getMode().getId();
        this.name = modeDetail.getName();
        this.startDatetime = modeDetail.getStartDatetime();
        this.endDatetime = modeDetail.getEndDatetime();
        this.remarks = modeDetail.getRemarks();
    }
}
