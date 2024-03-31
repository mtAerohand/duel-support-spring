package com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantResponse;

import java.util.List;

import lombok.Data;

/**
 * 試合情報の一括作成(寛容)レスポンス
 */
@Data
public class CreateGamesTorelantResponse {
    private List<CreateGamesTorelantResponseGame> games;
}
