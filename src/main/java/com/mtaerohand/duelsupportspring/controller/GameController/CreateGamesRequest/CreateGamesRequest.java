package com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesRequest;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * 試合情報の一括作成リクエスト
 */
@Data
public class CreateGamesRequest {
    @Valid
    private List<Game> games;
}
