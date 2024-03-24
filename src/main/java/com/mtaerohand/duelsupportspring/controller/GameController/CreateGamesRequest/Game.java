package com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesRequest;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

/**
 * 試合情報の一括作成リクエスト/試合
 */
// TODO: バリデーションエラーのメッセージを後でまとめる
@Value
public class Game {
    @NotNull(message = "モードIDが空です。")
    private Integer modeId;

    @NotNull(message = "モード詳細IDが空です。")
    private Integer modeDetailId;

    @NotNull(message = "試合日時が空です。")
    private LocalDateTime datetime;

    @NotNull(message = "私用デッキIDが空です。")
    private Integer myDeckId;

    @NotNull(message = "先後が空です。")
    private Boolean isFirstAttack;

    private Integer opDeckId;

    @NotNull(message = "結果が空です。")
    @Min(value = 0, message = "結果の値が不正です。")
    @Max(value = 2, message = "結果の値が不正です。")
    private Integer result;

    @Size(max = 100, message = "備考は100文字以内で入力してください。")
    private String remarks;
}
