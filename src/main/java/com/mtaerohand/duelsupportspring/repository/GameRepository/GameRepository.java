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
// TODO: sqlの形を整える
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
        // デッキ分布一覧情報の取得
        @Query("SELECT NEW com.mtaerohand.duelsupportspring.repository.GameRepository.DeckDistribution(g.myDeckId AS deckId, CAST(COUNT(g) AS FLOAT) / (SELECT COUNT(g2) FROM Game g2) AS ratio) "
                        + "FROM Game g WHERE g.modeId = :modeId AND g.modeDetailId = :modeDetailId "
                        + "GROUP BY g.myDeckId")
        List<DeckDistribution> getDeckDistributions(@Param("modeId") Integer modeId,
                        @Param("modeDetailId") Integer modeDetailId, Pageable pageable);

        // デッキ分布一覧情報の取得(時間指定あり)
        @Query("SELECT NEW com.mtaerohand.duelsupportspring.repository.GameRepository.DeckDistribution(g.myDeckId AS deckId, CAST(COUNT(g) AS FLOAT) / (SELECT COUNT(g2) FROM Game g2) AS ratio) "
                        + "FROM Game g WHERE g.modeId = :modeId AND g.modeDetailId = :modeDetailId AND g.datetime BETWEEN :datetimeFrom AND :datetimeTo "
                        + "GROUP BY g.myDeckId")
        List<DeckDistribution> getDeckDistributionsByDatetime(@Param("modeId") Integer modeId,
                        @Param("modeDetailId") Integer modeDetailId, @Param("datetimeFrom") LocalDateTime datetimeFrom,
                        @Param("datetimeTo") LocalDateTime datetimeTo, Pageable pageable);

        // 上位デッキの対面勝率一覧情報の取得
        @Query(value = "SELECT (CAST(COUNT(g) AS FLOAT) / "
                        + "(SELECT COUNT(g2) FROM Game g2 "
                        + "WHERE g2.myDeckId = :myDeckId AND g2.opDeckId = :opDeckId "
                        + "AND g2.modeId = :modeId AND g2.modeDetailId = :modeDetailId)) "
                        + "FROM Game g WHERE g.myDeckId = :myDeckId AND g.opDeckId = :opDeckId AND g.result = 1 AND g.modeId = :modeId AND g.modeDetailId = :modeDetailId")
        Float getWinningRateByDeckIds(@Param("modeId") Integer modeId,
                        @Param("modeDetailId") Integer modeDetailId,
                        @Param("myDeckId") Integer myDeckId,
                        @Param("opDeckId") Integer opDeckId);

        // 上位デッキの対面勝率一覧情報の取得(時間指定あり)
        @Query(value = "SELECT (CAST(COUNT(g) AS FLOAT) / "
                        + "(SELECT COUNT(g2) FROM Game g2 "
                        + "WHERE g2.myDeckId = :myDeckId AND g2.opDeckId = :opDeckId "
                        + "AND g2.modeId = :modeId AND g2.modeDetailId = :modeDetailId "
                        + "AND g2.datetime BETWEEN :datetimeFrom AND :datetimeTo)) "
                        + "FROM Game g WHERE g.myDeckId = :myDeckId AND g.opDeckId = :opDeckId AND g.result = 1 AND g.modeId = :modeId AND g.modeDetailId = :modeDetailId AND g.datetime BETWEEN :datetimeFrom AND :datetimeTo")
        Float getWinningRateByDeckIdsByDatetime(@Param("modeId") Integer modeId,
                        @Param("modeDetailId") Integer modeDetailId, @Param("myDeckId") Integer myDeckId,
                        @Param("opDeckId") Integer opDeckId, @Param("datetimeFrom") LocalDateTime datetimeFrom,
                        @Param("datetimeTo") LocalDateTime datetimeTo);
}