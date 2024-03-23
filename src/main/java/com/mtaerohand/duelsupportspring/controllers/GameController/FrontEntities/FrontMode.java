package com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities;

import java.util.List;

import com.mtaerohand.duelsupportspring.Entities.Mode;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * デフォルト返却用モードエンティティ
 */
@Data
@NoArgsConstructor
public class FrontMode {
    private Integer id;
    private String name;
    private String pronounce;
    private Boolean isPermanent;
    private String remarks;
    private List<FrontModeDetail> modeDetails;

    public FrontMode(Mode mode) {
        this.id = mode.getId();
        this.name = mode.getName();
        this.pronounce = mode.getPronounce();
        this.isPermanent = mode.getIsPermanent();
        this.remarks = mode.getRemarks();
    }
}
