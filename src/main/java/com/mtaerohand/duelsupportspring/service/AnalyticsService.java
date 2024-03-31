package com.mtaerohand.duelsupportspring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetDeckDistributionsRequest;
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetDeckDistributionsResponse.GetDeckDistributionsResponse;
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetDeckDistributionsResponse.GetDeckDistributionsResponseDeckDistribution;
import com.mtaerohand.duelsupportspring.repository.GameRepository.DeckDistribution;
import com.mtaerohand.duelsupportspring.repository.GameRepository.GameRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分析データサービス
 */
@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final GameRepository gameRepository;

    private final ModelMapper modelMapper;

    /**
     * デッキ分布一覧情報の取得
     * 
     * @param req デッキ分布一覧情報の取得リクエスト
     * @return デッキ分布一覧情報の取得レスポンス
     */
    public GetDeckDistributionsResponse getDeckDistributions(GetDeckDistributionsRequest req) {
        List<DeckDistribution> deckDistributions = new ArrayList<DeckDistribution>();

        if (req.getDatetimeFrom() == null && req.getDatetimeTo() == null) {
            // 時間指定なし
            deckDistributions = gameRepository.getDeckDistributions(req.getModeId(),
                    req.getModeDetailId(), PageRequest.of(0, req.getLimit(), Sort.by(Sort.Direction.DESC, "ratio")));
        } else {
            // 時間指定あり
            LocalDateTime datetimeFrom = req.getDatetimeFrom() == null ? LocalDateTime.now() : req.getDatetimeFrom();
            LocalDateTime datetimeTo = req.getDatetimeTo() == null ? LocalDateTime.now() : req.getDatetimeTo();
            deckDistributions = gameRepository.getDeckDistributionsByDatetime(req.getModeId(),
                    req.getModeDetailId(), datetimeFrom, datetimeTo,
                    PageRequest.of(0, req.getLimit(), Sort.by(Sort.Direction.DESC, "ratio")));
        }

        List<GetDeckDistributionsResponseDeckDistribution> resDeckDistributions = modelMapper
                .map(deckDistributions,
                        new TypeToken<List<GetDeckDistributionsResponseDeckDistribution>>() {
                        }.getType());

        GetDeckDistributionsResponse res = new GetDeckDistributionsResponse();

        res.setModeId(req.getModeId());
        res.setModeDetailId(req.getModeDetailId());
        res.setDeckDistributions(resDeckDistributions);

        return res;
    }
}
