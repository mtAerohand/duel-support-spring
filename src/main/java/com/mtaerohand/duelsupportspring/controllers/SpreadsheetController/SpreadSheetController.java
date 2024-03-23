package com.mtaerohand.duelsupportspring.Controllers.SpreadsheetController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontGame;
import com.mtaerohand.duelsupportspring.Entities.Game;
import com.mtaerohand.duelsupportspring.Services.GameService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("spreadsheet")
@CrossOrigin("*")
public class SpreadSheetController {
    private final GameService gameService;

    /**
     * Spreadsheet用試合一括作成(バリデーション無し)
     * 
     * @param req
     * @return
     */
    @PostMapping("games")
    public ResponseEntity<List<FrontGame>> createGames(@RequestBody CreateGamesRequest req) {
        List<Game> gamesForCreate = new ArrayList<Game>();

        for (CreateGameRequest reqItem : req.getGames()) {
            Game gameForCreate = new Game();

            gameForCreate.setModeId(reqItem.getModeId());
            gameForCreate.setModeDetailId(reqItem.getModeDetailId());
            gameForCreate.setDatetime(reqItem.getDatetime());
            gameForCreate.setMyDeckId(reqItem.getMyDeckId());
            gameForCreate.setIsFirstAttack(reqItem.getIsFirstAttack());
            gameForCreate.setOpDeckId(reqItem.getOpDeckId());
            gameForCreate.setResult(reqItem.getResult());
            gameForCreate.setRemarks(reqItem.getRemarks());

            gamesForCreate.add(gameForCreate);
        }

        List<Game> createdGames = gameService.createGamesTolerant(gamesForCreate);

        List<FrontGame> frontGames = new ArrayList<FrontGame>();

        for (Game createdGame : createdGames) {
            FrontGame frontGame = new FrontGame(createdGame);
            frontGames.add(frontGame);
        }

        return ResponseEntity.ok(frontGames);
    }
}
