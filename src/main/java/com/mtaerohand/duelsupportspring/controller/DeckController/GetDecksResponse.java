package com.mtaerohand.duelsupportspring.controller.DeckController;

import lombok.Data;

/**
 * デッキ一覧情報の取得レスポンス
 */
@Data
public class GetDecksResponse {
    private Integer id;
    private String name;
    private String pronounce;
    private String remarks;
}
