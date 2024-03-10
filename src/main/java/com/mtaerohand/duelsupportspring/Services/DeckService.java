package com.mtaerohand.duelsupportspring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.Entities.Deck;
import com.mtaerohand.duelsupportspring.Repositories.DeckRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeckService {
    private final DeckRepository deckRepository;

    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }
}
