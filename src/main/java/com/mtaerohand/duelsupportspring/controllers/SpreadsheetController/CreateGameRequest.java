package com.mtaerohand.duelsupportspring.Controllers.SpreadsheetController;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 試合作成リクエスト
 */
@Data
public class CreateGameRequest {
    /**
     * モードID
     */
    private Integer modeId;

    /**
     * モード詳細ID
     */
    private Integer modeDetailId;

    /**
     * 試合日時
     */
    private LocalDateTime datetime;

    /**
     * 使用デッキ
     */
    private Integer myDeckId;

    /**
     * 先後
     */
    private Boolean isFirstAttack;

    /**
     * 対面デッキ
     */
    private Integer opDeckId;

    /**
     * 結果
     */
    private Integer result;

    /**
     * 備考
     */
    private String remarks;
}
