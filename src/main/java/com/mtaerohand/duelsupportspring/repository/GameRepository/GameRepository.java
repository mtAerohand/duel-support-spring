package com.mtaerohand.duelsupportspring.repository.GameRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 試合情報リポジトリ
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("SELECT NEW com.mtaerohand.duelsupportspring.repository.GameRepository.DeckDistribution(g.myDeckId AS deckId, CAST(COUNT(g) AS FLOAT) / (SELECT COUNT(g2) FROM Game g2) AS ratio) "
            + "FROM Game g WHERE g.modeId = :modeId AND g.modeDetailId = :modeDetailId "
            + "GROUP BY g.myDeckId")
    List<DeckDistribution> getDeckDistributions(@Param("modeId") Integer modeId,
            @Param("modeDetailId") Integer modeDetailId, Pageable pageable);

    @Query("SELECT NEW com.mtaerohand.duelsupportspring.repository.GameRepository.DeckDistribution(g.myDeckId AS deckId, CAST(COUNT(g) AS FLOAT) / (SELECT COUNT(g2) FROM Game g2) AS ratio) "
            + "FROM Game g WHERE g.modeId = :modeId AND g.modeDetailId = :modeDetailId AND g.datetime BETWEEN :datetimeFrom AND :datetimeTo "
            + "GROUP BY g.myDeckId")
    List<DeckDistribution> getDeckDistributionsByDatetime(@Param("modeId") Integer modeId,
            @Param("modeDetailId") Integer modeDetailId, @Param("datetimeFrom") LocalDateTime datetimeFrom,
            @Param("datetimeTo") LocalDateTime datetimeTo, Pageable pageable);
}