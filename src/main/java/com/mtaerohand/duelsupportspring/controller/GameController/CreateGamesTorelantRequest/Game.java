package com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantRequest;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 試合情報の一括作成(寛容)リクエスト/試合
 */
@Data
public class Game {
    private Integer modeId;

    private Integer modeDetailId;

    private LocalDateTime datetime;

    private Integer myDeckId;

    private Boolean isFirstAttack;

    private Integer opDeckId;

    private Integer result;

    private String remarks;
}
