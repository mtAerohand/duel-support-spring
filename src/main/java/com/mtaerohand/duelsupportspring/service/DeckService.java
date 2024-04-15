package com.mtaerohand.duelsupportspring.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.controller.DeckController.GetDeckResponse;
import com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse.GetDecksResponse;
import com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse.GetDecksResponseDeck;
import com.mtaerohand.duelsupportspring.controller.ExceptionHandler.CustomException;
import com.mtaerohand.duelsupportspring.repository.DeckRepository.Deck;
import com.mtaerohand.duelsupportspring.repository.DeckRepository.DeckRepository;

import lombok.RequiredArgsConstructor;

// TODO: Transactionalつけたい
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
    public GetDecksResponse getDecks() {
        List<Deck> decks = deckRepository.findAll(Sort.by(Sort.Direction.ASC, "pronounce"));

        List<GetDecksResponseDeck> resDecks = modelMapper.map(decks, new TypeToken<List<GetDecksResponseDeck>>() {
        }.getType());

        GetDecksResponse res = new GetDecksResponse();
        res.setDecks(resDecks);
        return res;
    }

    /**
     * デッキ情報の取得
     * 
     * @param id デッキID
     * @return デッキ情報
     */
    public GetDeckResponse getDeck(Integer id) {
        GetDeckResponse res = new GetDeckResponse();
        if (id == null) {
            return res;
        } else {
            // TODO: エラーメッセージまとめる
            Deck deck = deckRepository.findById(id).orElseThrow(() -> new CustomException("errors.e001"));
            res = modelMapper.map(deck, GetDeckResponse.class);
            return res;
        }
    }
}
