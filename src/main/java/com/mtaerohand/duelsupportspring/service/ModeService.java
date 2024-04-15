package com.mtaerohand.duelsupportspring.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtaerohand.duelsupportspring.controller.ModeController.GetModesRequest;
import com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse.GetModesResponse;
import com.mtaerohand.duelsupportspring.controller.ModeController.GetModesResponse.GetModesResponseMode;
import com.mtaerohand.duelsupportspring.repository.ModeDetailRepository.ModeDetail;
import com.mtaerohand.duelsupportspring.repository.ModeDetailRepository.ModeDetailRepository;
import com.mtaerohand.duelsupportspring.repository.ModeDetailRepository.ModeDetailSpecifications;
import com.mtaerohand.duelsupportspring.repository.ModeRepository.Mode;
import com.mtaerohand.duelsupportspring.repository.ModeRepository.ModeRepository;

import lombok.RequiredArgsConstructor;

/**
 * モード情報サービス
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModeService {
    private final ModeRepository modeRepository;

    private final ModeDetailRepository modeDetailRepository;

    private final ModeDetailSpecifications modeDetailSpecifications;

    private final ModelMapper modelMapper;

    /**
     * モード一覧情報の取得
     * 
     * @param req モード一覧情報の取得リクエスト
     * @return モード一覧情報の取得レスポンス
     */
    public GetModesResponse getModes(GetModesRequest req) {
        List<Mode> modes = new ArrayList<>();
        if (req.getIsOngoing()) {
            // 現在有効なモード一覧情報を取得する
            Specification<ModeDetail> spec = modeDetailSpecifications.isOngoing();
            List<ModeDetail> ongoingModeDetails = spec == null
                    ? modeDetailRepository.findAll()
                    : modeDetailRepository.findAll(spec);

            for (ModeDetail modeDetail : ongoingModeDetails) {
                Mode ongoingMode = modeDetail.getMode();
                List<ModeDetail> modeDetails = new ArrayList<ModeDetail>();
                modeDetails.add(modeDetail);
                ongoingMode.setModeDetails(modeDetails);

                modes.add(ongoingMode);
            }
        } else {
            // モード一覧情報を取得する
            modes = modeRepository.findAll(Sort.by(Sort.Direction.DESC, "isPermanent"));
        }

        List<GetModesResponseMode> resModes = modelMapper.map(modes, new TypeToken<List<GetModesResponseMode>>() {
        }.getType());
        GetModesResponse res = new GetModesResponse();
        res.setModes(resModes);
        return res;
    }
}
