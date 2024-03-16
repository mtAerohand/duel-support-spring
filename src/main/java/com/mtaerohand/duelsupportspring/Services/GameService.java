package com.mtaerohand.duelsupportspring.Services;

import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.Entities.Game;
import com.mtaerohand.duelsupportspring.Repositories.GameRepository;

import lombok.RequiredArgsConstructor;

// TODO: Transactional周りを整理する

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    @SuppressWarnings("null")
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }
}
