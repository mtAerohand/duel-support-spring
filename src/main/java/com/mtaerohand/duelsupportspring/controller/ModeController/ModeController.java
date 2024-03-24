package com.mtaerohand.duelsupportspring.controller.ModeController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.mtaerohand.duelsupportspring.controller.BaseController;
import com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse.GetModesResponse;
import com.mtaerohand.duelsupportspring.service.ModeService;

import lombok.RequiredArgsConstructor;

/**
 * モード情報コントローラ
 */
@RequiredArgsConstructor
public class ModeController extends BaseController {
    private final ModeService modeService;

    /**
     * モード一覧情報の取得
     * 
     * @param req モード一覧情報の取得リクエスト
     * @return モード一覧情報の取得レスポンス
     */
    @GetMapping("modes")
    public ResponseEntity<List<GetModesResponse>> getModes(GetModesRequest req) {
        List<GetModesResponse> res = modeService.getModes(req);
        return ResponseEntity.ok(res);
    }
}
