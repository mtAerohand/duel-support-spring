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
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetWinningRatesRequest;
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetDeckDistributionsResponse.GetDeckDistributionsResponse;
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetDeckDistributionsResponse.GetDeckDistributionsResponseDeckDistribution;
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetWinningRatesResponse.GetWinningRatesResponse;
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetWinningRatesResponse.GetWinningRatesResponseWinningRate;
import com.mtaerohand.duelsupportspring.controller.AnalyticsController.GetWinningRatesResponse.GetWinningRatesResponseWinningRateForDeck;
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

        res.setDeckDistributions(resDeckDistributions);

        return res;
    }

    /**
     * 上位デッキの対面勝率一覧情報の取得
     * 
     * @param req 上位デッキの対面勝率一覧情報の取得リクエスト
     * @return 上位デッキの対面勝率一覧情報の取得レスポンス
     */
    public GetWinningRatesResponse getWinningRates(GetWinningRatesRequest req) {
        // デッキ分布の取得
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

        GetWinningRatesResponse res = new GetWinningRatesResponse();
        List<GetWinningRatesResponseWinningRate> winningRates = new ArrayList<GetWinningRatesResponseWinningRate>();
        for (int i = 0; i < deckDistributions.size(); i++) {
            Integer myDeckId = deckDistributions.get(i).getDeckId();
            GetWinningRatesResponseWinningRate winningRate = new GetWinningRatesResponseWinningRate();
            List<GetWinningRatesResponseWinningRateForDeck> winningRateForDecks = new ArrayList<GetWinningRatesResponseWinningRateForDeck>();
            for (int j = 0; j < deckDistributions.size(); j++) {
                Integer opDeckId = deckDistributions.get(j).getDeckId();
                GetWinningRatesResponseWinningRateForDeck winningRateForDeck = new GetWinningRatesResponseWinningRateForDeck();
                winningRateForDeck.setDeckId(opDeckId);
                Float winningRateValue = 0f;
                if (req.getDatetimeFrom() == null && req.getDatetimeTo() == null) {
                    // 時間指定なし
                    winningRateValue = gameRepository.getWinningRateByDeckIds(req.getModeId(),
                            req.getModeDetailId(), myDeckId, opDeckId);
                } else {
                    // 時間指定あり
                    LocalDateTime datetimeFrom = req.getDatetimeFrom() == null ? LocalDateTime.now()
                            : req.getDatetimeFrom();
                    LocalDateTime datetimeTo = req.getDatetimeTo() == null ? LocalDateTime.now()
                            : req.getDatetimeTo();
                    winningRateValue = gameRepository.getWinningRateByDeckIdsByDatetime(req.getModeId(),
                            req.getModeDetailId(), myDeckId, opDeckId, datetimeFrom, datetimeTo);
                }
                winningRateForDeck.setWinningRate(winningRateValue);
                winningRateForDecks.add(winningRateForDeck);
            }
            winningRate.setDeckId(myDeckId);
            winningRate.setWinningRateForDecks(winningRateForDecks);
            winningRates.add(winningRate);
        }
        res.setWinningRates(winningRates);
        return res;
    }
}
