package com.mtaerohand.duelsupportspring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.Entities.Game;
import com.mtaerohand.duelsupportspring.Entities.ModeDetail;
import com.mtaerohand.duelsupportspring.Repositories.DeckRepository;
import com.mtaerohand.duelsupportspring.Repositories.GameRepository;
import com.mtaerohand.duelsupportspring.Repositories.ModeDetailRepository;
import com.mtaerohand.duelsupportspring.Repositories.ModeRepository;

import lombok.RequiredArgsConstructor;

// TODO: Transactional周りを整理する
// TODO: エラーメッセージを外部ファイルにまとめたい
@Service
@RequiredArgsConstructor
public class GameService {
    private final DeckRepository deckRepository;
    private final ModeRepository modeRepository;
    private final ModeDetailRepository modeDetailRepository;
    private final GameRepository gameRepository;

    @SuppressWarnings("null")
    public Game createGame(Game game) throws Exception {
        masterCheck(game);
        return gameRepository.save(game);
    }

    @SuppressWarnings("null")
    public List<Game> createGames(List<Game> games) throws Exception {
        for (Game game : games) {
            masterCheck(game);
        }

        return gameRepository.saveAll(games);
    }

    @SuppressWarnings("null")
    private void masterCheck(Game game) throws Exception {
        if (!modeRepository.existsById(game.getModeId())) {
            throw new Exception("モードが不正です。");
        } else if (!modeDetailRepository.existsById(game.getModeDetailId())) {
            throw new Exception("モード詳細が不正です。");
        } else if (!deckRepository.existsById(game.getMyDeckId())) {
            throw new Exception("使用デッキが不正です。");
        } else if (game.getOpDeckId() != null && !deckRepository.existsById(game.getOpDeckId())) {
            throw new Exception("対面デッキが不正です。");
        } else {
            ModeDetail modeDetail = modeDetailRepository.findById(game.getModeDetailId())
                    .orElseThrow(() -> new Exception("モード詳細が外部セッションにより変更され取得できませんでした。"));
            if (game.getDatetime().compareTo(modeDetail.getStartDatetime()) < 0
                    || game.getDatetime().compareTo(modeDetail.getEndDatetime()) > 0) {
                throw new Exception("試合日時が不正です。");
            }
        }
    }
}
