package com.mtaerohand.duelsupportspring.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesRequest.CreateGamesRequest;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesResponse.CreateGamesResponse;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesResponse.CreateGamesResponseGame;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantRequest.CreateGamesTorelantRequest;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantResponse.CreateGamesTorelantResponse;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesTorelantResponse.CreateGamesTorelantResponseGame;
import com.mtaerohand.duelsupportspring.repository.GameRepository.Game;
import com.mtaerohand.duelsupportspring.repository.GameRepository.GameRepository;
import com.mtaerohand.duelsupportspring.repository.UserRepository.User;
import com.mtaerohand.duelsupportspring.repository.UserRepository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 試合情報サービス
 */
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
    public CreateGamesResponse createGames(CreateGamesRequest req) {
        List<Game> games = modelMapper.map(req.getGames(), new TypeToken<List<Game>>() {
        }.getType());
        if (games == null) {
            games = new ArrayList<Game>();
        }

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

        List<Game> createdGames = gameRepository.saveAll(games);

        List<CreateGamesResponseGame> resGames = modelMapper.map(createdGames,
                new TypeToken<List<CreateGamesResponseGame>>() {
                }.getType());
        CreateGamesResponse res = new CreateGamesResponse();
        res.setGames(resGames);

        return res;
    }

    /**
     * 試合情報の一括作成(寛容)
     * 
     * @param req 試合情報の一括作成(寛容)リクエスト
     */
    public CreateGamesTorelantResponse createGamesTorelant(CreateGamesTorelantRequest req) {
        // 入力データを試合エンティティのリストにマッピング
        List<Game> games = modelMapper.map(req.getGames(), new TypeToken<List<Game>>() {
        }.getType());
        // 保存データがnullの場合、空のlistを設定
        if (games == null) {
            games = new ArrayList<Game>();
        }

        // ユーザIDが入力されている場合は保持
        String userId = req.getUserId() == null ? "" : req.getUserId().trim();

        // ユーザIDに紐づく試合データを全件削除
        if (!userId.isBlank()) {
            gameRepository.deleteByUserId(userId);
        }

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

        // データを全件保存
        List<Game> createdGames = gameRepository.saveAll(games);

        List<CreateGamesTorelantResponseGame> resGames = modelMapper.map(createdGames,
                new TypeToken<List<CreateGamesTorelantResponseGame>>() {
                }.getType());
        CreateGamesTorelantResponse res = new CreateGamesTorelantResponse();
        res.setGames(resGames);
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
