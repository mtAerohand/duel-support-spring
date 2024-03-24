package com.mtaerohand.duelsupportspring.repository.GameRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 試合情報リポジトリ
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}