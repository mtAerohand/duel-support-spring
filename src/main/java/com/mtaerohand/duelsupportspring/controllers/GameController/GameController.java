package com.mtaerohand.duelsupportspring.Controllers.GameController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontDeck;
import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontGame;
import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontMode;
import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontModeDetail;
import com.mtaerohand.duelsupportspring.Controllers.GameController.RequestEntities.CreateGameRequest;
import com.mtaerohand.duelsupportspring.Entities.Deck;
import com.mtaerohand.duelsupportspring.Entities.Game;
import com.mtaerohand.duelsupportspring.Entities.Mode;
import com.mtaerohand.duelsupportspring.Entities.ModeDetail;
import com.mtaerohand.duelsupportspring.Services.DeckService;
import com.mtaerohand.duelsupportspring.Services.GameService;
import com.mtaerohand.duelsupportspring.Services.ModeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * GameArea用Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("game")
@Validated
@CrossOrigin("http://localhost:3000")
public class GameController {
    private final DeckService deckService;
    private final ModeService modeService;
    private final GameService gameService;

    /**
     * デッキ一覧の取得
     * 
     * @return デッキ一覧
     */
    @GetMapping("decks")
    public ResponseEntity<List<FrontDeck>> getDecks() {
        List<FrontDeck> res = new ArrayList<FrontDeck>();

        List<Deck> decks = deckService.getDecks();

        for (Deck deck : decks) {
            FrontDeck frontDeck = new FrontDeck(deck);
            res.add(frontDeck);
        }

        return ResponseEntity.ok(res);
    }

    /**
     * モード一覧の取得(モード詳細なし)
     * 
     * @return
     */
    @GetMapping("modes")
    public ResponseEntity<List<FrontMode>> getModes() {
        List<FrontMode> res = new ArrayList<FrontMode>();

        List<Mode> modes = modeService.getModes();

        for (Mode mode : modes) {
            FrontMode frontMode = new FrontMode(mode);
            res.add(frontMode);
        }

        return ResponseEntity.ok(res);
    }

    /**
     * 現在有効なモード一覧の取得(モード詳細あり)
     * 
     * @return
     */
    @GetMapping("ongoing-modes")
    public ResponseEntity<List<FrontMode>> getOngoingModes() {
        List<FrontMode> res = new ArrayList<FrontMode>();

        List<Mode> ongoingModes = modeService.getOngoingModes();

        for (Mode mode : ongoingModes) {
            FrontMode frontMode = new FrontMode(mode);

            List<FrontModeDetail> frontModeDetails = new ArrayList<FrontModeDetail>();
            for (ModeDetail modeDetail : mode.getModeDetails()) {
                FrontModeDetail frontModeDetail = new FrontModeDetail(modeDetail);
                frontModeDetails.add(frontModeDetail);
            }

            frontMode.setModeDetails(frontModeDetails);

            res.add(frontMode);
        }

        return ResponseEntity.ok(res);
    }

    /**
     * 試合の作成
     * 
     * @param req Game作成リクエスト
     * @return 作成したGame
     */
    @PostMapping("/game")
    public ResponseEntity<FrontGame> createGame(@Valid @RequestBody CreateGameRequest req) throws Exception {
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

        FrontGame res = new FrontGame(createdGame);

        return ResponseEntity.ok(res);
    }

    @PostMapping("games")
    public ResponseEntity<List<FrontGame>> createGames(@Valid @RequestBody List<CreateGameRequest> req)
            throws Exception {
        List<Game> gamesForCreate = new ArrayList<Game>();

        for (CreateGameRequest reqItem : req) {
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

        List<Game> createdGames = gameService.createGames(gamesForCreate);

        List<FrontGame> frontGames = new ArrayList<FrontGame>();

        for (Game createdGame : createdGames) {
            FrontGame frontGame = new FrontGame(createdGame);
            frontGames.add(frontGame);
        }

        return ResponseEntity.ok(frontGames);
    }

    /**
     * 初期化データの一括取得
     * 
     * @return デッキ一覧、モード一覧、現在有効なモード、モード詳細セット一覧
     */
    // @GetMapping("/initialize-data")
    // public ResponseEntity<GetInitializeDataResponse> getInitializeData() {
    // GetInitializeDataResponse res = new GetInitializeDataResponse();

    // List<Deck> decks = deckService.getDecks();
    // List<Mode> modes = modeService.getModes();
    // List<Mode> modesOngoing = modeService.getModesOngoing();

    // List<FrontDeck> resDecks = new ArrayList<FrontDeck>();
    // List<FrontMode> resModes = new ArrayList<FrontMode>();
    // List<ModeOngoing> resModesOngoing = new ArrayList<ModeOngoing>();

    // for (Deck deck : decks) {
    // FrontDeck frontDeck = new FrontDeck();

    // frontDeck.setId(deck.getId());
    // frontDeck.setName(deck.getName());
    // frontDeck.setPronounce(deck.getPronounce());
    // frontDeck.setRemarks(deck.getRemarks());

    // resDecks.add(frontDeck);
    // }

    // for (Mode mode : modes) {
    // FrontMode frontMode = new FrontMode();

    // frontMode.setId(mode.getId());
    // frontMode.setName(mode.getName());
    // frontMode.setPronounce(mode.getPronounce());
    // frontMode.setIsPermanent(mode.getIsPermanent());
    // frontMode.setRemarks(mode.getRemarks());

    // resModes.add(frontMode);
    // }

    // for (Mode modeOngoing : modesOngoing) {
    // ModeOngoing resModeOngoing = new ModeOngoing();

    // FrontMode frontMode = new FrontMode();
    // FrontModeDetail frontModeDetail = new FrontModeDetail();

    // ModeDetail modeDetail = modeOngoing.getModeDetails().get(0);

    // frontMode.setId(modeOngoing.getId());
    // frontMode.setName(modeOngoing.getName());
    // frontMode.setPronounce(modeOngoing.getPronounce());
    // frontMode.setIsPermanent(modeOngoing.getIsPermanent());
    // frontMode.setRemarks(modeOngoing.getRemarks());

    // frontModeDetail.setId(modeDetail.getId());
    // frontModeDetail.setModeId(modeDetail.getMode().getId());
    // frontModeDetail.setName(modeDetail.getName());
    // frontModeDetail.setStartDatetime(modeDetail.getStartDatetime());
    // frontModeDetail.setEndDatetime(modeDetail.getEndDatetime());
    // frontModeDetail.setRemarks(modeDetail.getRemarks());

    // resModeOngoing.setModeOngoing(frontMode);
    // resModeOngoing.setModeDetailOngoing(frontModeDetail);

    // resModesOngoing.add(resModeOngoing);
    // }

    // res.setDecks(resDecks);
    // res.setModes(resModes);
    // res.setModesOngoing(resModesOngoing);

    // return ResponseEntity.ok(res);
    // }

}
