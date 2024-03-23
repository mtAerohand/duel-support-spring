package com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities;

import java.time.LocalDateTime;

import com.mtaerohand.duelsupportspring.Entities.Game;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * デフォルト返却用試合エンティティ
 */
@Data
@NoArgsConstructor
public class FrontGame {
    private Integer id;
    private Integer modeId;
    private Integer modeDetailId;
    private LocalDateTime datetime;
    private Integer myDeckId;
    private Boolean isFirstAttack;
    private Integer opDeckId;
    private Integer result;
    private String remarks;

    public FrontGame(Game game) {
        this.id = game.getId();
        this.modeId = game.getModeId();
        this.modeDetailId = game.getModeDetailId();
        this.datetime = game.getDatetime();
        this.myDeckId = game.getMyDeckId();
        this.isFirstAttack = game.getIsFirstAttack();
        this.opDeckId = game.getOpDeckId();
        this.result = game.getResult();
        this.remarks = game.getRemarks();
    }
}
