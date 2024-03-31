package com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesResponse;

import java.util.List;

import lombok.Data;

/**
 * 試合情報の一括作成レスポンス
 */
@Data
public class CreateGamesResponse {
    private List<CreateGamesResponseGame> games;
}
