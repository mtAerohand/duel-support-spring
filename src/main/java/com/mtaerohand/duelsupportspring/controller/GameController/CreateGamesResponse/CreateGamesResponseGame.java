package com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesResponse;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * デッキ情報の一括作成レスポンス/Game
 */
@Data
public class CreateGamesResponseGame {
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
