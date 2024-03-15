package com.mtaerohand.duelsupportspring.Controllers.GameController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.GetInitializeDataResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.DeckDTO;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.ModeDTO;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetModeDetailsOngoingResponse.GetModeDetailsOngoingResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetModeDetailsOngoingResponse.InternalDTO.ModeDetailDTO;
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
    public ResponseEntity<GetModeDetailsOngoingResponse> getModeDetailsOngoing(@RequestParam("modeId") Integer modeId) {
        GetModeDetailsOngoingResponse res = new GetModeDetailsOngoingResponse();

        List<ModeDetail> modeDetailsOngoing = modeService.getModeDetailsOngoing(modeId);

        List<ModeDetailDTO> resModeDetails = new ArrayList<ModeDetailDTO>();

        res.setModeId(modeId);

        for (ModeDetail modeDetail : modeDetailsOngoing) {
            ModeDetailDTO dto = new ModeDetailDTO();
            dto.setId(modeDetail.getId());
            dto.setName(modeDetail.getName());
            dto.setStartDatetime(modeDetail.getStartDatetime());
            dto.setEndDatetime(modeDetail.getEndDatetime());
            resModeDetails.add(dto);
        }

        res.setModeDetails(resModeDetails);

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
