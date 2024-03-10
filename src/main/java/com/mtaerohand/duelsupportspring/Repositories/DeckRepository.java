package com.mtaerohand.duelsupportspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtaerohand.duelsupportspring.Entities.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Integer> {

}
