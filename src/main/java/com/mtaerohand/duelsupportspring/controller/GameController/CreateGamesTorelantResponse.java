package com.mtaerohand.duelsupportspring.controller.GameController;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 試合情報の一括作成(寛容)レスポンス
 */
@Data
public class CreateGamesTorelantResponse {
    private Integer id;
    private String userId;
    private Integer modeId;
    private Integer modeDetailId;
    private LocalDateTime datetime;
    private Integer myDeckId;
    private Boolean isFirstAttack;
    private Integer opDeckId;
    private Integer result;
    private String remarks;
}
