package com.mtaerohand.duelsupportspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtaerohand.duelsupportspring.Entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}