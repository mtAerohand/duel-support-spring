package com.mtaerohand.duelsupportspring.repository.DeckRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * デッキ情報リポジトリ
 */
@Repository
public interface DeckRepository extends JpaRepository<Deck, Integer>, JpaSpecificationExecutor<Deck> {
    @SuppressWarnings("null")
    List<Deck> findAll(Sort sort);
}
