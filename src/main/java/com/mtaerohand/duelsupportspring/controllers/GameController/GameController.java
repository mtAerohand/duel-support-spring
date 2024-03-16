package com.mtaerohand.duelsupportspring.Controllers.GameController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.CreateGameRequest;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.CreateGameResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetModeDetailsOngoingResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.GetInitializeDataResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.DeckDTO;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.ModeDTO;
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

        List<Deck> decks = deckService.getAllDecks();
        List<Mode> modes = modeService.getAllModes();

        List<DeckDTO> resDecks = new ArrayList<DeckDTO>();
        List<ModeDTO> resModes = new ArrayList<ModeDTO>();

        for (Deck deck : decks) {
            DeckDTO dto = new DeckDTO();
            dto.setId(deck.getId());
            dto.setName(deck.getName());
            dto.setPronounce(deck.getPronounce());
            resDecks.add(dto);
        }

        for (Mode mode : modes) {
            ModeDTO dto = new ModeDTO();
            dto.setId(mode.getId());
            dto.setName(mode.getName());
            dto.setPronounce(mode.getPronounce());
            resModes.add(dto);
        }

        res.setDecks(resDecks);
        res.setModes(resModes);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/mode-details/ongoing")
    public ResponseEntity<List<GetModeDetailsOngoingResponse>> getModeDetailsOngoing(
            @RequestParam("modeId") Integer modeId) {
        List<GetModeDetailsOngoingResponse> resList = new ArrayList<GetModeDetailsOngoingResponse>();

        List<ModeDetail> modeDetailsOngoing = modeService.getModeDetailsOngoing(modeId);

        for (ModeDetail modeDetail : modeDetailsOngoing) {
            GetModeDetailsOngoingResponse res = new GetModeDetailsOngoingResponse();
            res.setId(modeDetail.getId());
            res.setModeId(modeDetail.getModeId());
            res.setName(modeDetail.getName());
            res.setStartDatetime(modeDetail.getStartDatetime());
            res.setEndDatetime(modeDetail.getEndDatetime());
            resList.add(res);
        }

        return ResponseEntity.ok(resList);
    }

    @PostMapping("/game")
    public ResponseEntity<CreateGameResponse> createGame(@RequestBody CreateGameRequest req) {
        CreateGameResponse res = new CreateGameResponse();

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

    @GetMapping("/decks")
    public List<Deck> getAllDecks() {
        return deckService.getAllDecks();
    }

    // @GetMapping("/modes/ongoing")
    // public List getModesOngoing() {
    // return new String();
    // }

}
