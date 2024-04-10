package com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantRequest;

import java.util.List;

import lombok.Data;

/**
 * 試合情報の一括作成リクエスト
 */
@Data
public class CreateGamesTorelantRequest {
    private String userId;

    private List<CreateGamesTorelantRequestGame> games;
}
