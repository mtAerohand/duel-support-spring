package com.mtaerohand.duelsupportspring.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.controller.DeckController.GetDeckResponse;
import com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksRequest.CreateDecksRequest;
import com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksResponse.CreateDecksResponse;
import com.mtaerohand.duelsupportspring.controller.DeckController.CreateDecksResponse.CreateDecksResponseDeck;
import com.mtaerohand.duelsupportspring.controller.DeckController.DeleteDecksRequest.DeleteDecksRequest;
import com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse.GetDecksResponse;
import com.mtaerohand.duelsupportspring.controller.DeckController.GetDecksResponse.GetDecksResponseDeck;
import com.mtaerohand.duelsupportspring.controller.DeckController.UpdateDecksRequest.UpdateDecksRequest;
import com.mtaerohand.duelsupportspring.controller.DeckController.UpdateDecksRequest.UpdateDecksRequestDeck;
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
            Deck deck = deckRepository.findById(id).orElseThrow(() -> new RuntimeException("指定されたIDのデッキが存在しません。"));
            res = modelMapper.map(deck, GetDeckResponse.class);
            return res;
        }
    }

    /**
     * デッキ情報の一括作成
     * 
     * @param req デッキ情報の一括作成リクエスト
     * @return デッキ情報の一括作成レスポンス
     */
    public CreateDecksResponse createDecks(CreateDecksRequest req) {
        List<Deck> decksForCreate = modelMapper.map(req.getDecks(), new TypeToken<List<Deck>>() {
        }.getType());
        // nullチェック
        if (decksForCreate == null) {
            return new CreateDecksResponse();
        }
        // リクエストデータをデッキテーブルの保存
        List<Deck> decksCreated = deckRepository.saveAll(decksForCreate);

        // レスポンスデータを生成し返却
        List<CreateDecksResponseDeck> resDecks = modelMapper.map(decksCreated,
                new TypeToken<List<CreateDecksResponseDeck>>() {
                }.getType());
        CreateDecksResponse res = new CreateDecksResponse();
        res.setDecks(resDecks);
        return res;
    }

    /**
     * デッキ情報の一括更新
     * 
     * @param req デッキ情報の一括更新リクエスト
     * @return デッキ情報の一括更新レスポンス
     */
    public void updateDecks(UpdateDecksRequest req) {
        // 更新対象のレコードを取得し入力値をマッピング
        List<Deck> decksForUpdate = new ArrayList<Deck>();
        for (UpdateDecksRequestDeck deck : req.getDecks()) {
            // TODO: エラーメッセージをまとめる
            // TODO: 入力を丸ごとマッピングするのではなく、入力があった項目のみアップデートするほうがよさそう。
            Deck deckForUpdate = deckRepository.findById(deck.getId())
                    .orElseThrow(() -> new RuntimeException("指定されたIDのデッキが存在しません。"));
            deckForUpdate = modelMapper.map(deck, Deck.class);
            decksForUpdate.add(deckForUpdate);
        }
        // 対象レコードの更新処理
        deckRepository.saveAll(decksForUpdate);
    }

    /**
     * デッキ情報の一括削除
     * 
     * @param req デッキ情報の一括削除リクエスト
     */
    public void deleteDecks(DeleteDecksRequest req) {
        deckRepository.deleteAllById(req.getDeckIds());
    }
}
