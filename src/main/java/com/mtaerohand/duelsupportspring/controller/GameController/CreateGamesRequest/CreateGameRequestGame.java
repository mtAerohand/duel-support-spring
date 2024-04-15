package com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesRequest;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 試合情報の一括作成リクエスト/試合
 */
@Data
public class CreateGameRequestGame {
    @NotNull
    private Integer modeId;

    @NotNull
    private Integer modeDetailId;

    @NotNull
    private LocalDateTime datetime;

    @NotNull
    private Integer myDeckId;

    @NotNull
    private Boolean isFirstAttack;

    private Integer opDeckId;

    @NotNull
    @Min(value = 0)
    @Max(value = 2)
    private Integer result;

    @Size(max = 100)
    private String remarks;
}
