package com.mtaerohand.duelsupportspring.controller.ModeController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse.GetModesResponse;
import com.mtaerohand.duelsupportspring.service.ModeService;

import lombok.RequiredArgsConstructor;

/**
 * モード情報コントローラ
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ModeController {
    private final ModeService modeService;

    /**
     * モード一覧情報の取得
     * 
     * @param req モード一覧情報の取得リクエスト
     * @return モード一覧情報の取得レスポンス
     */
    @GetMapping("modes")
    public ResponseEntity<GetModesResponse> getModes(GetModesRequest req) {
        GetModesResponse res = modeService.getModes(req);
        return ResponseEntity.ok(res);
    }
}
