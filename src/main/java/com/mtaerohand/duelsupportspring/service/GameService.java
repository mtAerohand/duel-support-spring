package com.mtaerohand.duelsupportspring.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesResponse;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantResponse;
import com.mtaerohand.duelsupportspring.controller.GameController.GetDeckDistributionsRequest;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesRequest.CreateGamesRequest;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantRequest.CreateGamesTorelantRequest;
import com.mtaerohand.duelsupportspring.controller.GameController.GetDeckDistributionsResponse.GetDeckDistributionsResponse;
import com.mtaerohand.duelsupportspring.repository.GameRepository.DeckDistribution;
import com.mtaerohand.duelsupportspring.repository.GameRepository.Game;
import com.mtaerohand.duelsupportspring.repository.GameRepository.GameRepository;
import com.mtaerohand.duelsupportspring.repository.UserRepository.User;
import com.mtaerohand.duelsupportspring.repository.UserRepository.UserRepository;

import lombok.RequiredArgsConstructor;

// TODO: Transactional周りを整理する
// TODO: エラーメッセージを外部ファイルにまとめたい
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class GameService {

    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    /**
     * 試合情報の一括作成
     * 
     * @param req 試合情報の一括作成リクエスト
     */
    @SuppressWarnings("null")
    public List<CreateGamesResponse> createGames(CreateGamesRequest req) throws Exception {
        List<Game> games = modelMapper.map(req.getGames(), new TypeToken<List<Game>>() {
        }.getType());

        String userId = req.getUserId() == null ? "" : req.getUserId().trim();

        // ModelMapperが勝手にIdを設定してしまうのでnullに再設定
        // またユーザIDを設定
        for (Game game : games) {
            game.setId(null);
            game.setUserId(userId);
        }

        // ユーザテーブルに存在しない場合追加する
        if (!userId.isEmpty() && !userRepository.existsById(userId)) {
            User user = new User();
            user.setId(userId);
            userRepository.save(user);
        }

        List<Game> createdGames;

        try {
            createdGames = gameRepository.saveAll(games);
        } catch (Exception e) {
            throw new Exception("試合情報を保存できませんでした。");
        }

        List<CreateGamesResponse> res = modelMapper.map(createdGames, new TypeToken<List<CreateGamesResponse>>() {
        }.getType());

        return res;
    }

    /**
     * 試合情報の一括作成(寛容)
     * 
     * @param req 試合情報の一括作成(寛容)リクエスト
     */
    @SuppressWarnings("null")
    public List<CreateGamesTorelantResponse> createGamesTorelant(CreateGamesTorelantRequest req) throws Exception {
        List<Game> games = modelMapper.map(req.getGames(), new TypeToken<List<Game>>() {
        }.getType());

        String userId = req.getUserId() == null ? "" : req.getUserId().trim();

        // 不正なデータをフィルタリング
        games = torelantFilter(games);

        // ModelMapperが勝手にIdを設定してしまうのでnullに再設定
        for (Game game : games) {
            game.setId(null);
            game.setUserId(userId);
        }

        // userIdがユーザテーブルに存在しない場合追加する
        if (!userId.isEmpty() && !userRepository.existsById(userId)) {
            User user = new User();
            user.setId(userId);
            userRepository.save(user);
        }

        List<Game> createdGames;

        try {
            createdGames = gameRepository.saveAll(games);
        } catch (Exception e) {
            throw new Exception("試合情報を保存できませんでした。");
        }

        List<CreateGamesTorelantResponse> res = modelMapper.map(createdGames,
                new TypeToken<List<CreateGamesTorelantResponse>>() {
                }.getType());

        return res;
    }

    public GetDeckDistributionsResponse getDeckDistributions(GetDeckDistributionsRequest req) {
        List<DeckDistribution> deckDistributions = gameRepository.getDeckDistributions(req.getModeId(),
                req.getModeDetailId(), PageRequest.of(0, req.getLimit(), Sort.by(Sort.Direction.DESC, "ratio")));

        List<com.mtaerohand.duelsupportspring.controller.GameController.GetDeckDistributionsResponse.DeckDistribution> resDeckDistributions = modelMapper
                .map(deckDistributions,
                        new TypeToken<List<com.mtaerohand.duelsupportspring.controller.GameController.GetDeckDistributionsResponse.DeckDistribution>>() {
                        }.getType());

        GetDeckDistributionsResponse res = new GetDeckDistributionsResponse();

        res.setModeId(req.getModeId());
        res.setModeDetailId(req.getModeDetailId());
        res.setDeckDistributions(resDeckDistributions);

        return res;
    }

    /**
     * 寛容デッキデータ保存用のフィルタ
     * 
     * @param games
     * @return
     */
    private List<Game> torelantFilter(List<Game> games) {
        List<Game> filteredGames = new ArrayList<Game>();

        for (Game game : games) {
            if (game.getModeId() == null) {
                continue;
            } else if (game.getModeDetailId() == null) {
                continue;
            } else if (game.getDatetime() == null) {
                continue;
            } else if (game.getMyDeckId() == null) {
                continue;
            } else if (game.getIsFirstAttack() == null) {
                continue;
            } else if (game.getResult() == null || game.getResult() < 0 || game.getResult() > 2) {
                continue;
            } else if (game.getRemarks().length() > 100) {
                continue;
            } else {
                filteredGames.add(game);
            }
        }

        return filteredGames;
    }
}
