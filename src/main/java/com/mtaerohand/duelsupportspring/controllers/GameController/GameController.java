package com.mtaerohand.duelsupportspring.Controllers.GameController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontDeck;
import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontGame;
import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontMode;
import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontModeDetail;
import com.mtaerohand.duelsupportspring.Controllers.GameController.RequestEntities.CreateGameRequest;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.GetInitializeDataResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.ModeOngoing;
import com.mtaerohand.duelsupportspring.Entities.Deck;
import com.mtaerohand.duelsupportspring.Entities.Game;
import com.mtaerohand.duelsupportspring.Entities.Mode;
import com.mtaerohand.duelsupportspring.Entities.ModeDetail;
import com.mtaerohand.duelsupportspring.Services.DeckService;
import com.mtaerohand.duelsupportspring.Services.GameService;
import com.mtaerohand.duelsupportspring.Services.ModeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// TODO: レスポンスエンティティにマッピング用のコンストラクタ作っても良いかもね
@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
@CrossOrigin("http://localhost:3000")
public class GameController {
    private final DeckService deckService;
    private final ModeService modeService;
    private final GameService gameService;

    @GetMapping("/initialize-data")
    public ResponseEntity<GetInitializeDataResponse> getInitializeData() {
        GetInitializeDataResponse res = new GetInitializeDataResponse();

        List<Deck> decks = deckService.getDecks();
        List<Mode> modes = modeService.getModes();
        List<Mode> modesOngoing = modeService.getModesOngoing();

        List<FrontDeck> resDecks = new ArrayList<FrontDeck>();
        List<FrontMode> resModes = new ArrayList<FrontMode>();
        List<ModeOngoing> resModesOngoing = new ArrayList<ModeOngoing>();

        for (Deck deck : decks) {
            FrontDeck frontDeck = new FrontDeck();

            frontDeck.setId(deck.getId());
            frontDeck.setName(deck.getName());
            frontDeck.setPronounce(deck.getPronounce());
            frontDeck.setRemarks(deck.getRemarks());

            resDecks.add(frontDeck);
        }

        for (Mode mode : modes) {
            FrontMode frontMode = new FrontMode();

            frontMode.setId(mode.getId());
            frontMode.setName(mode.getName());
            frontMode.setPronounce(mode.getPronounce());
            frontMode.setIsPermanent(mode.getIsPermanent());
            frontMode.setRemarks(mode.getRemarks());

            resModes.add(frontMode);
        }

        for (Mode modeOngoing : modesOngoing) {
            ModeOngoing resModeOngoing = new ModeOngoing();

            FrontMode frontMode = new FrontMode();
            FrontModeDetail frontModeDetail = new FrontModeDetail();

            ModeDetail modeDetail = modeOngoing.getModeDetails().get(0);

            frontMode.setId(modeOngoing.getId());
            frontMode.setName(modeOngoing.getName());
            frontMode.setPronounce(modeOngoing.getPronounce());
            frontMode.setIsPermanent(modeOngoing.getIsPermanent());
            frontMode.setRemarks(modeOngoing.getRemarks());

            frontModeDetail.setId(modeDetail.getId());
            frontModeDetail.setModeId(modeDetail.getMode().getId());
            frontModeDetail.setName(modeDetail.getName());
            frontModeDetail.setStartDatetime(modeDetail.getStartDatetime());
            frontModeDetail.setEndDatetime(modeDetail.getEndDatetime());
            frontModeDetail.setRemarks(modeDetail.getRemarks());

            resModeOngoing.setModeOngoing(frontMode);
            resModeOngoing.setModeDetailOngoing(frontModeDetail);

            resModesOngoing.add(resModeOngoing);
        }

        res.setDecks(resDecks);
        res.setModes(resModes);
        res.setModesOngoing(resModesOngoing);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/game")
    public ResponseEntity<FrontGame> createGame(@RequestBody CreateGameRequest req) {
        FrontGame res = new FrontGame();

        Game gameForCreate = new Game();

        gameForCreate.setModeId(req.getModeId());
        gameForCreate.setModeDetailId(req.getModeDetailId());
        gameForCreate.setDatetime(req.getDatetime());
        gameForCreate.setMyDeckId(req.getMyDeckId());
        gameForCreate.setIsFirstAttack(req.getIsFirstAttack());
        gameForCreate.setOpDeckId(req.getOpDeckId());
        gameForCreate.setResult(req.getResult());
        gameForCreate.setRemarks(req.getRemarks());

        Game createdGame = gameService.createGame(gameForCreate);

        res.setId(createdGame.getId());
        res.setModeId(createdGame.getModeId());
        res.setModeDetailId(createdGame.getModeDetailId());
        res.setDatetime(createdGame.getDatetime());
        res.setMyDeckId(createdGame.getMyDeckId());
        res.setIsFirstAttack(createdGame.getIsFirstAttack());
        res.setOpDeckId(createdGame.getOpDeckId());
        res.setResult(createdGame.getResult());
        res.setRemarks(createdGame.getRemarks());

        return ResponseEntity.ok(res);
    }
}
