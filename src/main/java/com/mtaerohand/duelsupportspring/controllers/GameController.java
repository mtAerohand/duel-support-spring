package com.mtaerohand.duelsupportspring.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtaerohand.duelsupportspring.Entities.Deck;
import com.mtaerohand.duelsupportspring.Services.DeckService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final DeckService deckService;

    @GetMapping("/decks")
    public List<Deck> getAllDecks() {
        return deckService.getAllDecks();
    }
}
