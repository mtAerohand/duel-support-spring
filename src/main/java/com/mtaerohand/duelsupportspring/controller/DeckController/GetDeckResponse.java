package com.mtaerohand.duelsupportspring.controller.DeckController;

import lombok.Data;

/**
 * デッキ情報の取得レスポンス
 */
@Data
public class GetDeckResponse {
    private Integer id;
    private String name;
    private String pronounce;
    private String remarks;
}
