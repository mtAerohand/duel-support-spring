package com.mtaerohand.duelsupportspring.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesResponse;
import com.mtaerohand.duelsupportspring.controller.GameController.CreateGamesRequest.CreateGamesRequest;
import com.mtaerohand.duelsupportspring.repository.GameRepository.Game;
import com.mtaerohand.duelsupportspring.repository.GameRepository.GameRepository;

import lombok.RequiredArgsConstructor;

// TODO: Transactional周りを整理する
// TODO: エラーメッセージを外部ファイルにまとめたい
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class GameService {

    private final GameRepository gameRepository;

    private final ModelMapper modelMapper;

    /**
     * 試合情報の一括作成
     * 
     * @param req 試合情報の一括作成リクエスト
     */
    @SuppressWarnings("null")
    public List<CreateGamesResponse> createGames(CreateGamesRequest req) {
        List<Game> games = modelMapper.map(req.getGames(), new TypeToken<List<Game>>() {
        }.getType());

        List<Game> createdGames = gameRepository.saveAll(games);

        List<CreateGamesResponse> res = modelMapper.map(createdGames, new TypeToken<List<CreateGamesResponse>>() {
        }.getType());

        return res;
    }

    private List<Game> torerantFilter(List<Game> games) {
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
