package com.mtaerohand.duelsupportspring.Controllers.GameController.RequestEntities;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 試合作成リクエスト
 */
// TODO: エラーメッセージを別ファイルにまとめたい
@Data
public class CreateGameRequest {
    /**
     * モードID
     */
    @NotNull(message = "モードが未入力です。")
    private Integer modeId;

    /**
     * モード詳細ID
     */
    @NotNull(message = "モード詳細が未入力です。")
    private Integer modeDetailId;

    /**
     * 試合日時
     */
    @NotNull(message = "試合日時が未入力です。")
    private LocalDateTime datetime;

    /**
     * 使用デッキ
     */
    @NotNull(message = "使用デッキが未入力です。")
    private Integer myDeckId;

    /**
     * 先後
     */
    @NotNull(message = "先後が未入力です。")
    private Boolean isFirstAttack;

    /**
     * 対面デッキ
     */
    private Integer opDeckId;

    /**
     * 結果
     */
    @NotNull(message = "結果が未入力です。")
    @Min(value = 0, message = "結果の値が不正です。")
    @Max(value = 2, message = "結果の値が不正です。")
    private Integer result;

    /**
     * 備考
     */
    @Size(max = 100, message = "文字数は100文字を超過できません。")
    private String remarks;
}
