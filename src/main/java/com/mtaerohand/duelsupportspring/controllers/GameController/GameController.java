package com.mtaerohand.duelsupportspring.Controllers.GameController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetModeDetailsOngoingResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.GetInitializeDataResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.DeckDTO;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.ModeDTO;
import com.mtaerohand.duelsupportspring.Entities.Deck;
import com.mtaerohand.duelsupportspring.Entities.Mode;
import com.mtaerohand.duelsupportspring.Entities.ModeDetail;
import com.mtaerohand.duelsupportspring.Services.DeckService;
import com.mtaerohand.duelsupportspring.Services.ModeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final DeckService deckService;
    private final ModeService modeService;

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

    @GetMapping("/decks")
    public List<Deck> getAllDecks() {
        return deckService.getAllDecks();
    }

    // @GetMapping("/modes/ongoing")
    // public List getModesOngoing() {
    // return new String();
    // }

}
