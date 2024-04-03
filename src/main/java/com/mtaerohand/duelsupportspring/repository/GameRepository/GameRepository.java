package com.mtaerohand.duelsupportspring.repository.GameRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 試合情報リポジトリ
 */
// TODO: sqlの形を整える
@Repository
public interface GameRepository extends JpaRepository<Game, Integer>, JpaSpecificationExecutor<Game> {
        // デッキ分布一覧情報の取得
        @Query("SELECT NEW com.mtaerohand.duelsupportspring.repository.GameRepository.DeckDistribution(g.myDeckId AS deckId, CAST(COUNT(g) / (SELECT COUNT(g2) FROM Game g2) AS FLOAT) AS ratio) "
                        + "FROM Game g WHERE g.modeId = :modeId AND g.modeDetailId = :modeDetailId "
                        + "GROUP BY g.myDeckId")
        List<DeckDistribution> getDeckDistributions(@Param("modeId") Integer modeId,
                        @Param("modeDetailId") Integer modeDetailId, Pageable pageable);

        // デッキ分布一覧情報の取得(時間指定あり)
        @Query("SELECT NEW com.mtaerohand.duelsupportspring.repository.GameRepository.DeckDistribution(g.myDeckId AS deckId, CAST(COUNT(g) / (SELECT COUNT(g2) FROM Game g2) AS FLOAT) AS ratio) "
                        + "FROM Game g WHERE g.modeId = :modeId AND g.modeDetailId = :modeDetailId AND g.datetime BETWEEN :datetimeFrom AND :datetimeTo "
                        + "GROUP BY g.myDeckId")
        List<DeckDistribution> getDeckDistributionsByDatetime(@Param("modeId") Integer modeId,
                        @Param("modeDetailId") Integer modeDetailId, @Param("datetimeFrom") LocalDateTime datetimeFrom,
                        @Param("datetimeTo") LocalDateTime datetimeTo, Pageable pageable);

        // 上位デッキの対面勝率一覧情報の取得
        @Query(value = "select NEW com.mtaerohand.duelsupportspring.repository.GameRepository.WinningRateForDeck("
                        + "cast(g.winCount / g.totalCount as float) as winningRate, "
                        + "cast(g.winCount as int) as winCount, "
                        + "cast (g.totalCount - g.winCount as int) as loseCount) "
                        + "from "
                        + "(select "
                        + "(select "
                        + "count(g3.id) "
                        + "from "
                        + "Game g3 "
                        + "where "
                        + "g3.myDeckId = :myDeckId "
                        + "AND g3.opDeckId = :opDeckId "
                        + "AND g3.modeId = :modeId "
                        + "AND g3.modeDetailId = :modeDetailId "
                        + ") as totalCount, "
                        + "count(g2.id) as winCount "
                        + "from "
                        + "Game g2 "
                        + "where "
                        + "g2.myDeckId = :myDeckId "
                        + "AND g2.opDeckId = :opDeckId "
                        + "AND g2.result = 1 "
                        + "AND g2.modeId = :modeId "
                        + "AND g2.modeDetailId = :modeDetailId "
                        + ") as g")
        WinningRateForDeck getWinningRateByDeckIds(@Param("modeId") Integer modeId,
                        @Param("modeDetailId") Integer modeDetailId,
                        @Param("myDeckId") Integer myDeckId,
                        @Param("opDeckId") Integer opDeckId);

        // 上位デッキの対面勝率一覧情報の取得(時間指定あり)
        @Query(value = "select NEW com.mtaerohand.duelsupportspring.repository.GameRepository.WinningRateForDeck("
                        + "cast(g.winCount / g.totalCount as float) as winningRate, "
                        + "cast(g.winCount as int) as winCount, "
                        + "cast (g.totalCount - g.winCount as int) as loseCount) "
                        + "from "
                        + "(select "
                        + "(select "
                        + "count(g3.id) "
                        + "from "
                        + "Game g3 "
                        + "where "
                        + "g3.myDeckId = :myDeckId "
                        + "AND g3.opDeckId = :opDeckId "
                        + "AND g3.modeId = :modeId "
                        + "AND g3.modeDetailId = :modeDetailId "
                        + "and g3.datetime between :datetimeFrom and :datetimeTo "
                        + ") as totalCount, "
                        + "count(g2.id) as winCount "
                        + "from "
                        + "Game g2 "
                        + "where "
                        + "g2.myDeckId = :myDeckId "
                        + "AND g2.opDeckId = :opDeckId "
                        + "AND g2.result = 1 "
                        + "AND g2.modeId = :modeId "
                        + "AND g2.modeDetailId = :modeDetailId "
                        + "and g2.datetime between :datetimeFrom and :datetimeTo "
                        + ") as g")
        WinningRateForDeck getWinningRateByDeckIdsByDatetime(@Param("modeId") Integer modeId,
                        @Param("modeDetailId") Integer modeDetailId, @Param("myDeckId") Integer myDeckId,
                        @Param("opDeckId") Integer opDeckId, @Param("datetimeFrom") LocalDateTime datetimeFrom,
                        @Param("datetimeTo") LocalDateTime datetimeTo);
}