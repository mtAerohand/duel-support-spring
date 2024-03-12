package com.mtaerohand.duelsupportspring.Controllers.GameController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.GetInitializeDataResponse;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.DeckDTO;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.ModeDTO;
import com.mtaerohand.duelsupportspring.Entities.Deck;
import com.mtaerohand.duelsupportspring.Entities.Mode;
import com.mtaerohand.duelsupportspring.Services.DeckService;
import com.mtaerohand.duelsupportspring.Services.ModeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final DeckService deckService;
    private final ModeService modeService;

    @GetMapping("/initialize")
    public GetInitializeDataResponse getInitializeData() {
        GetInitializeDataResponse response = new GetInitializeDataResponse();

        List<Deck> decks = deckService.getAllDecks();
        List<Mode> modes = modeService.getAllModes();

        List<DeckDTO> responseDecks = new ArrayList<DeckDTO>();
        List<ModeDTO> responseModes = new ArrayList<ModeDTO>();

        for (Deck deck : decks) {
            DeckDTO dto = new DeckDTO();
            dto.setId(deck.getId());
            dto.setName(deck.getName());
            dto.setPronounce(deck.getPronounce());
            responseDecks.add(dto);
        }

        for (Mode mode : modes) {
            ModeDTO dto = new ModeDTO();
            dto.setId(mode.getId());
            dto.setName(mode.getName());
            dto.setPronounce(mode.getPronounce());
            responseModes.add(dto);
        }

        response.setDecks(responseDecks);
        response.setModes(responseModes);

        return response;
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
