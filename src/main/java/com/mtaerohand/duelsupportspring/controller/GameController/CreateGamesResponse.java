package com.mtaerohand.duelsupportspring.controller.GameController;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 試合情報の一括作成レスポンス
 */
@Data
public class CreateGamesResponse {
    private Integer id;
    private Integer modeId;
    private Integer modeDetailId;
    private LocalDateTime datetime;
    private Integer myDeckId;
    private Boolean isFirstAttack;
    private Integer opDeckId;
    private Integer result;
    private String remarks;
}
