package com.mtaerohand.duelsupportspring.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse;
import com.mtaerohand.duelsupportspring.repository.DeckRepository.Deck;
import com.mtaerohand.duelsupportspring.repository.DeckRepository.DeckRepository;

import lombok.RequiredArgsConstructor;

/**
 * デッキ情報サービス
 */
@Service
@RequiredArgsConstructor
public class DeckService {
    private final DeckRepository deckRepository;

    private final ModelMapper modelMapper;

    /**
     * デッキ一覧情報の取得
     * 
     * @return デッキ一覧情報
     */
    public List<GetDecksResponse> getDecks() {
        List<Deck> decks = deckRepository.findAll(Sort.by(Sort.Direction.ASC, "pronounce"));

        List<GetDecksResponse> res = modelMapper.map(decks, new TypeToken<List<GetDecksResponse>>() {
        }.getType());

        return res;
    }
}
