package com.mtaerohand.duelsupportspring.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.controller.ModeController.GetModesRequest;
import com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse.GetModesResponse;
import com.mtaerohand.duelsupportspring.repository.ModeRepository.Mode;
import com.mtaerohand.duelsupportspring.repository.ModeRepository.ModeRepository;
import com.mtaerohand.duelsupportspring.repository.ModeRepository.ModeSpecifications;

import lombok.RequiredArgsConstructor;

/**
 * モード情報サービス
 */
@Service
@RequiredArgsConstructor
public class ModeService {
    private final ModeRepository modeRepository;

    private final ModeSpecifications modeSpecifications;

    private final ModelMapper modelMapper;

    /**
     * モード一覧情報の取得
     * 
     * @param req モード一覧情報の取得リクエスト
     * @return モード一覧情報の取得レスポンス
     */
    public List<GetModesResponse> getModes(GetModesRequest req) {
        List<Mode> modes;
        if (req.getIsOngoing()) {
            modes = modeRepository.findAll(modeSpecifications.isOngoing(), Sort.by(Sort.Direction.DESC, "isPermanent"));
        } else {
            modes = modeRepository.findAll(Sort.by(Sort.Direction.DESC, "isPermanent"));
        }

        List<GetModesResponse> res = modelMapper.map(modes, new TypeToken<List<GetModesResponse>>() {
        }.getType());

        return res;
    }
}
